package MonsterMakerPackage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class HTMLHelper {
    private static final String htmlTemplate = loadFile();

    private static String loadFile() {
        File template = new File(HTMLHelper.class.getResource("/static/index.html").getFile());
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(template);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void open(String filepath) {
        File htmlFile = new File(filepath);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String monsterToHtmlRaw(MonsterMaker monster) {
        String html = htmlTemplate;
        assert html != null;
        html = html.replace("NAME", monster.getName());
        html = html.replace("SUBTITLE", monster.getSubtitle().substring(0,1).toUpperCase() + monster.getSubtitle().substring(1).toLowerCase());

        html = html.replace("ARMORCLASS", monster.getArmorClass() + "");
        html = html.replace("ARMOR_CLASS_DESC", !monster.getArmorClassDesc().equals("") ? "(%s)".formatted(monster.getArmorClassDesc()) : "");
        html = html.replace("DICEAVG", monster.getHitpointsAvg() + "");
        html = html.replace("HITDICE", monster.getHitDice().toString());
        html = html.replace("SPEED", monster.getSpeed() + "");

        for (int i = 0; i < monster.getAbilityScores().length; i++) {
            html = html.replace("ATR" + (i+1), monster.getAbilityScores()[i] + "");
            int mod = (monster.getAbilityScores()[i] - 10) / 2;
            html = html.replace("MOD" + (i+1), mod >= 0 ? "+" + mod : "-" + Math.abs(mod));
        }

        if (!Arrays.equals(monster.getSavingThrows(), new int[]{0, 0, 0, 0, 0, 0})) {
            String[] abilities = new String[]{"Str", "Dex", "Con", "Int", "Wis", "Cha"};
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < monster.getSavingThrows().length; i++) {
                if (monster.getSavingThrows()[i] != 0) {
                    a.append(abilities[i]).append(" +").append(monster.getSavingThrows()[i]).append(",");
                }
            }
            String b = a.toString();
            html = html.replace("SAVINGTHROWS", b.substring(0, b.length()-1));
        } else {
            html = html.replace("<li><span>Saving Throws </span>SAVINGTHROWS</li>", "");
        }
        if (monster.getSkills() != null) {
            html = html.replace("SKILLS", String.join(", ", monster.getSkills()));
        } else {
            html = html.replace("<li><span>Skills </span>SKILLS</li>", "");
        }
        if (monster.getDamageImmunities() != null) {
            html = html.replace("DAMAGEIMMUNITIES", String.join(", ", monster.getDamageImmunities()));
        } else {
            html = html.replace("<li><span>Damage Immunities </span>DAMAGEIMMUNITIES</li>", "");
        }
        if (monster.getDamageResistances() != null) {
            html = html.replace("DAMAGERESISTANCE", String.join(", ", monster.getDamageResistances()));
        } else {
            html = html.replace("<li><span>Damage Resistance </span>DAMAGERESISTANCE</li>", "");
        }
        if (monster.getConditionImmunities() != null) {
            html = html.replace("CONDITIONIMMUNITIES", String.join(", ", monster.getConditionImmunities()));
        } else {
            html = html.replace("<li><span>Condition Immunities </span>CONDITIONIMMUNITIES</li>", "");
        }
        if (monster.getSenses() != null) {
            html = html.replace("SENSES", String.join(", ", monster.getSenses()));
        } else {
            html = html.replace("<li><span>Senses </span>SENSES</li>", "");
        }
        if (monster.getLanguages() != null) {
            html = html.replace("LANGUAGES", String.join(", ", monster.getLanguages()));
        } else {
            html = html.replace("<li><span>Languages </span>LANGUAGES</li>", "");
        }
        String challenge = monster.getChallenge() + "";
        if (challenge.split("\\.")[1].equals("0")) {
            challenge = challenge.split("\\.")[0];
        } else if (challenge.equals("0.5")) {
            challenge = "1/2";
        } else if (challenge.equals("0.25")) {
            challenge = "1/4";
        } else if (challenge.equals("0.125")) {
            challenge = "1/8";
        }
        html = html.replace("CHALLENGE", challenge);
        html = html.replace("TOTALXP", challengeToXp(monster.getChallenge()));

        StringBuilder a = new StringBuilder();
        for (Trait trait : monster.getTraits()) {
            a.append("<li>%s</li>".formatted(traitHtml(trait)));
        }
        html = html.replace("TRAITS", a.toString());

        if (monster.getActions() != null) {
            a = new StringBuilder();
            for (Action action : monster.getActions()) {
                a.append("<li>%s</li>".formatted(actionHtml(action)));
            }
            html = html.replace("AACTIONS", a.toString());
        } else {
            html = html.replace("<a name=\"toc_5\"></a><h2>Actions</h2><hr> <ul>AACTIONS</ul>", "");
        }
        if (monster.getReactions() != null) {
            a = new StringBuilder();
            for (Action reaction : monster.getReactions()) {
                a.append("<li>%s</li>".formatted(actionHtml(reaction)));
            }
            html = html.replace("REACTIONS", a.toString());
        } else {
            html = html.replace("<h2>Reactions</h2><hr><ul>REACTIONS</ul>", "");
        }
        return html;
    }

    public static void monsterToHtml(MonsterMaker monster) throws IOException {

        File file = new File(monster.getName() + ".html");
        if (file.createNewFile()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(monsterToHtmlRaw(monster));
            fileWriter.close();
        }
    }

    public static void monsterToHtmlOpen(MonsterMaker monster) throws IOException {
        monsterToHtml(monster);
        open(monster.getName()+".html");
    }

    private static String traitHtml(Trait trait) {
        return "<span>%s</span>. %s".formatted(trait.getName(), trait.getDescription());
    }

    private static String actionHtml(Action action) {
        if (action.getMode() == 0) {
            return "<span>%s.</span>%s".formatted(action.getName(), action.getDescription());
        } else {
            return "<span>%s.</span>%s Weapon Attack: %s to hit, reach %s ft., %s. Hit: %s (%s) %s damage%s.".formatted(
                    action.getName(),
                    action.getMode() == 1 ? "Melee" : "Ranged",
                    action.getBonusToHit() >= 0 ? "+" + action.getBonusToHit() : action.getBonusToHit() + "",
                    action.getMode() == 1 ? action.getReach()[0] + "" : action.getReach()[0] + "/" + action.getReach()[1],
                    action.getTargets() + ((action.getTargets()).equals("one") ? " target" : " targets"),
                    action.getDamageDice().diceAverage() + "",
                    action.getDamageDice().toString(),
                    action.getDamageType(),
                    action.getDescription()
            );
        }
    }

    private static String challengeToXp(double c) {
        return switch (c + "") {
            case "0.0" -> "10";
            case "0.125" -> "25";
            case "0.25" -> "50";
            case "0.5" -> "100";
            case "1.0" -> "200";
            case "2.0" -> "450";
            case "3.0" -> "700";
            case "4.0" -> "1,100";
            case "5.0" -> "1,800";
            case "6.0" -> "2,300";
            case "7.0" -> "2,900";
            case "8.0" -> "3,900";
            case "9.0" -> "5,000";
            case "10.0" -> "5,900";
            case "11.0" -> "7,200";
            case "12.0" -> "8,400";
            case "13.0" -> "10,000";
            case "14.0" -> "11,500";
            case "15.0" -> "13,000";
            case "16.0" -> "15,000";
            case "17.0" -> "18,000";
            case "18.0" -> "20,000";
            case "19.0" -> "22,000";
            case "20.0" -> "25,000";
            case "21.0" -> "33,000";
            case "22.0" -> "41,000";
            case "23.0" -> "50,000";
            case "24.0" -> "62,000";
            case "25.0" -> "75,000";
            case "26.0" -> "90,000";
            case "27.0" -> "105,000";
            case "28.0" -> "120,000";
            case "29.0" -> "135,000";
            case "30.0" -> "155,000";
            default -> "0";
        };
    }
}
