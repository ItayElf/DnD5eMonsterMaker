package Gui;

import MonsterMakerPackage.Dice;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Tools extends JMenu {
    private static final String[] crs = new String[]{"0.0", "0.125", "0.25", "0.5", "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0", "11.0", "12.0", "13.0", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0"};
    private static final HashMap<String, Integer> profs = makeMap(new Integer[]{2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9});
    private static final HashMap<String, Integer> ac = makeMap(new Integer[]{13, 13, 13, 13, 13, 13, 13, 14, 15, 15, 15, 16, 16, 17, 17, 17, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19});
    private static final HashMap<String, String> hp = makeMap(new String[]{"1-6", "7-35", "36-49", "50-70", "71-85", "86-100", "101-115", "116-130", "131-145", "146-160", "161-175", "176-190", "191-205", "206-220", "221-235", "236-250", "251-265", "266-280", "281-295", "296-310", "311-325", "326-340", "341-355", "356-400", "401-445", "446-490", "491-535", "536-580", "581-625", "626-670", "671-715", "716-760", "760-805", "805-850"});
    private static final HashMap<String, Integer> attackBonus = makeMap(new Integer[]{3, 3, 3, 3, 3, 3, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 13, 13, 13, 14});
    private static final HashMap<String, String> damage = makeMap(new String[]{"0-1", "2-3", "4-5", "6-8", "9-14", "15-20", "21-26", "27-32", "33-28", "29-44", "45-50", "51-56", "57-62", "63-68", "69-74", "75-80", "81-86", "87-92", "93-98", "99-104", "105-110", "111-116", "117-122", "123-140", "141-158", "159-176", "177-194", "195-212", "213-230", "231-248", "249-266", "267-284", "285-302", "303-320"});
    private static final HashMap<String, Integer> save = makeMap(new Integer[]{13, 13, 13, 13, 13, 13, 13, 14, 15, 15, 15, 16, 16, 16, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22, 23});

    private final JTextField challengeField;
    private final JTextField[] diceFields;
    private final JComboBox<String> sizeBox;
    private final JTextField[] abilitiesField;
    private final JTextField skillsField;
    private final boolean[] skills = new boolean[18];

    public Tools(JTextField challengeField, JTextField[] diceFields, JComboBox<String> sizeBox, JTextField[] abilitiesField, JTextField skillsField) {
        super("Tools");
        this.challengeField = challengeField;
        this.diceFields = diceFields;
        this.sizeBox = sizeBox;
        this.abilitiesField = abilitiesField;
        this.skillsField = skillsField;
        JMenuItem statsSuggestions = new JMenuItem("Stats Suggestions");
        statsSuggestions.addActionListener(e -> statsSuggestionsFunction());
        add(statsSuggestions);

        JMenuItem calcHp = new JMenuItem("Calculate Hit Dice");
        calcHp.addActionListener(e -> calculateHitDice());
        add(calcHp);

        JMenuItem calcDamage = new JMenuItem("Calculate Damage Dice");
        calcDamage.addActionListener(e -> calculateDamageDice());
        add(calcDamage);

        JMenuItem setSkills = new JMenuItem("Set Skills");
        setSkills.addActionListener(e -> setMonsterSkills());
        add(setSkills);
    }

    private void statsSuggestionsFunction() {
        double challenge;
        try {
            challenge = getChallenge(challengeField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid challenge rating", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame topLevel = new JFrame("Suggested stats");
        topLevel.setLayout(new MigLayout("filly"));
        topLevel.setSize(Defaults.getScreenWidth() / 2, Defaults.getScreenHeight());
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel panel = new JPanel(new MigLayout("fill"));
        JLabel prof = new JLabel("Proficiency Bonus: ");
        prof.setFont(Defaults.getTextFieldFontSmall());
        panel.add(prof, "width 70%, align 5%");
        JLabel prof1 = new JLabel("+" + profs.get(challenge + ""));
        prof1.setFont(Defaults.getTextFieldFontSmall());
        prof1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(prof1, "width 30%, align 95%, wrap");

        JLabel acl = new JLabel("Armor Class: ");
        acl.setFont(Defaults.getTextFieldFontSmall());
        panel.add(acl, "width 70%, align 5%");
        JLabel acl1 = new JLabel("" + ac.get(challenge + ""));
        acl1.setFont(Defaults.getTextFieldFontSmall());
        acl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(acl1, "width 30%, align 95%, wrap");

        JLabel hit = new JLabel("Hit Points: ");
        hit.setFont(Defaults.getTextFieldFontSmall());
        panel.add(hit, "width 70%, align 5%");
        JLabel hit1 = new JLabel("" + hp.get(challenge + ""));
        hit1.setFont(Defaults.getTextFieldFontSmall());
        hit1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(hit1, "width 30%, align 95%, wrap");

        JLabel attack = new JLabel("Attack Bonus: ");
        attack.setFont(Defaults.getTextFieldFontSmall());
        panel.add(attack, "width 70%, align 5%");
        JLabel attack1 = new JLabel("+" + attackBonus.get(challenge + ""));
        attack1.setFont(Defaults.getTextFieldFontSmall());
        attack1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(attack1, "width 30%, align 95%, wrap");

        JLabel dmg = new JLabel("Damage per Round: ");
        dmg.setFont(Defaults.getTextFieldFontSmall());
        panel.add(dmg, "width 70%, align 5%");
        JLabel dmg1 = new JLabel("" + damage.get(challenge + ""));
        dmg1.setFont(Defaults.getTextFieldFontSmall());
        dmg1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(dmg1, "width 30%, align 95%, wrap");

        JLabel sv = new JLabel("Save DC: ");
        sv.setFont(Defaults.getTextFieldFontSmall());
        panel.add(sv, "width 70%, align 5%");
        JLabel sv1 = new JLabel("" + save.get(challenge + ""));
        sv1.setFont(Defaults.getTextFieldFontSmall());
        sv1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(sv1, "width 30%, align 95%, wrap");

        wrapper.add(panel, "width 100%, height 100%, wrap");
        JButton recalculate = new JButton("Recalculate");
        recalculate.addActionListener(e -> {
            double c;
            try {
                c = getChallenge(challengeField.getText());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Please enter a valid challenge rating", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            prof1.setText("+" + profs.get(c + ""));
            acl1.setText("" + ac.get(c + ""));
            hit1.setText("" + hp.get(c + ""));
            attack1.setText("+" + attackBonus.get(c + ""));
            dmg1.setText("" + damage.get(c + ""));
            sv1.setText("" + save.get(c + ""));
        });
        wrapper.add(recalculate, "width 100%");
        topLevel.add(wrapper, "height 100%, width 100%");
        topLevel.setVisible(true);
    }

    private void calculateHitDice() {
        double challenge;
        try {
            challenge = getChallenge(challengeField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid challenge rating", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int con;
        try {
            con = Integer.parseInt(abilitiesField[2].getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid constitution", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int modifier = (con - 10) / 2;
        int[] dices = {4, 6, 8, 10, 12, 20, 100};
        int min = Integer.parseInt(hp.get(challenge + "").split("-")[0]), max = Integer.parseInt(hp.get(challenge + "").split("-")[1]);
        int hitpoints;
        if (modifier <= -1) {
            hitpoints = min;
        } else if (modifier >= 7) {
            hitpoints = max;
        } else {
            hitpoints = (min * (6 - modifier) + max * modifier) / 6;
        }
        int avg = (int) ((double) (dices[sizeBox.getSelectedIndex()]) / 2 + 0.5 + Math.min(modifier, 6));

        Dice dice = new Dice(hitpoints / avg, dices[sizeBox.getSelectedIndex()], modifier * (hitpoints / avg));
        diceFields[0].setText(dice.getDiceCount() + "");
        diceFields[1].setText(dice.getDiceSize() + "");
        diceFields[2].setText(dice.getBonus() + "");
    }

    private void calculateDamageDice() {
        double challenge;
        try {
            challenge = getChallenge(challengeField.getText());
        } catch (Exception e) {
            challenge = -1;
        }
        JFrame topLevel = new JFrame("Calculate Damage Dice");
        topLevel.setSize(new Dimension(Defaults.getScreenWidth() / 3 * 2, Defaults.getScreenHeight() / 8 * 7));
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JLabel[][] labels = new JLabel[5][4];

        JPanel damagePanel = new JPanel(new MigLayout("fillx"));
        JTextField damageField = new JTextField();
        damageField.setFont(Defaults.getTextFieldFont());
        damageField.setText(challenge == -1 ? "" : "" + ((Integer.parseInt(damage.get(challenge + "").split("-")[0]) + Integer.parseInt(damage.get(challenge + "").split("-")[1])) / 2));
        JButton calc = new JButton("Calculate Avg");
        calc.setFont(Defaults.getTextFieldFontSmall());
        calc.addActionListener(e -> {
            int dmg;
            try {
                dmg = Integer.parseInt(damageField.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(topLevel, "Please enter a valid damage", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Dice[] dices = numberToDice(dmg, true);
            for (int i = 0; i < labels.length; i++) {
                labels[i][0].setText(dices[i] + "");
                labels[i][1].setText(dices[i].diceMin() + "");
                labels[i][2].setText(dices[i].diceAverage() + "");
                labels[i][3].setText(dices[i].diceMax() + "");
            }
        });
        JButton calc1 = new JButton("Calculate Max");
        calc1.setFont(Defaults.getTextFieldFontSmall());
        calc1.addActionListener(e -> {
            int dmg;
            try {
                dmg = Integer.parseInt(damageField.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(topLevel, "Please enter a valid damage", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Dice[] dices = numberToDice(dmg, false);
            for (int i = 0; i < labels.length; i++) {
                labels[i][0].setText(dices[i] + "");
                labels[i][1].setText(dices[i].diceMin() + "");
                labels[i][2].setText(dices[i].diceAverage() + "");
                labels[i][3].setText(dices[i].diceMax() + "");
            }
        });
        damagePanel.add(damageField, "width 40%");
        damagePanel.add(calc, "width 30%");
        damagePanel.add(calc1, "width 30%");
        wrapper.add(damagePanel, "width 100%, wrap");

        JPanel grid = new JPanel(new MigLayout("fillx"));
        for (int i = 0; i < labels.length; i++) {
            labels[i][0] = new JLabel(new Dice(0, 4 + i * 2, 0) + "", SwingConstants.CENTER);
            labels[i][0].setBorder(BorderFactory.createTitledBorder("d" + (4 + i * 2)));
            grid.add(labels[i][0], "width 25%");

            labels[i][1] = new JLabel("0", SwingConstants.CENTER);
            labels[i][1].setBorder(BorderFactory.createTitledBorder("d" + (4 + i * 2) + " min"));
            grid.add(labels[i][1], "width 25%");

            labels[i][2] = new JLabel("0", SwingConstants.CENTER);
            labels[i][2].setBorder(BorderFactory.createTitledBorder("d" + (4 + i * 2) + " avg"));
            grid.add(labels[i][2], "width 25%");

            labels[i][3] = new JLabel("0", SwingConstants.CENTER);
            labels[i][3].setBorder(BorderFactory.createTitledBorder("d" + (4 + i * 2) + " max"));
            grid.add(labels[i][3], "width 25%, wrap");
        }

        wrapper.add(grid, "width 100%");
        if (challenge != -1) {
            calc.doClick();
        }
        topLevel.add(wrapper);
        topLevel.setVisible(true);
    }

    private void setMonsterSkills() {
        final String[] names = new String[]{"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand", "Stealth", "Survival"};
        final int[] indexes = new int[]{1, 4, 3, 0, 5, 3, 4, 5, 3, 4, 3, 4, 5, 5, 3, 1, 1, 4};
        final JCheckBox[] checkBoxes = new JCheckBox[18];
        JFrame topLevel = new JFrame("Set Skills");

        int[] abilityScores = new int[6];
        for (int i = 0; i < abilityScores.length; i++) {
            try {
                abilityScores[i] = Integer.parseInt(abilitiesField[i].getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(topLevel, "Please enter valid ability scores", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        double challenge;
        try {
            challenge = getChallenge(challengeField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid challenge rating", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        topLevel.setSize(new Dimension(Defaults.getScreenWidth() / 5 * 3, Defaults.getScreenHeight() / 8 * 9));
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel left = new JPanel(new MigLayout("filly"));
        JPanel right = new JPanel(new MigLayout("filly"));
        for (int i = 0; i < names.length; i++) {
            checkBoxes[i] = new JCheckBox(names[i]);
            checkBoxes[i].setFont(Defaults.getTextFieldFontSmall());
            checkBoxes[i].setSelected(skills[i]);
            if ((i < names.length / 2)) {
                left.add(checkBoxes[i], "wrap, width 100%");
            } else {
                right.add(checkBoxes[i], "wrap, width 100%");
            }
        }
        JPanel panel = new JPanel(new MigLayout("fillx"));
        panel.add(left, "width 50%");
        panel.add(right, "width 50%");
        wrapper.add(panel, "width 100%, wrap");
        JButton set = new JButton("Set");
        set.setFont(Defaults.getTextFieldFontSmall());
        set.addActionListener(e -> {
            LinkedList<String> strings = new LinkedList<>();
            for (int i = 0; i < checkBoxes.length; i++) {
                skills[i] = checkBoxes[i].isSelected();
                if (skills[i]) {
                    int modifier = (abilityScores[indexes[i]] - 10) / 2;
                    int profBonus = profs.get(challenge + "");
                    String sep = (modifier + profBonus >= 0) ? "+" : "-";
                    strings.add("%s %s%s".formatted(names[i], sep, Math.abs(modifier + profBonus)));
                }
            }
            skillsField.setText(String.join(", ", strings));
        });
        wrapper.add(set, "align 50%");

        topLevel.add(wrapper);
        topLevel.setVisible(true);
    }

    private double getChallenge(String s) throws Exception {
        s = s.replace(" ", "");
        switch (s) {
            case "1/8":
                return 0.125;
            case "1/4":
                return 0.25;
            case "1/2":
                return 0.5;
        }
        try {
            double c = Double.parseDouble(s);
            if (c > 30 || c < 0) {
                throw new Exception("Invalid challenge rating.");
            } else return c;
        } catch (NumberFormatException e) {
            throw new Exception("Challenge rating can only be a number.");
        }
    }

    private static <T> HashMap<String, T> makeMap(T[] vals) {
        HashMap<String, T> map = new HashMap<>();
        for (int i = 0; i < crs.length; i++) {
            map.put(crs[i], vals[i]);
        }
        return map;
    }

    private Dice[] numberToDice(int number, boolean average) {
        Dice[] dices = new Dice[7];
        double[] nums = new double[]{4, 6, 8, 10, 12, 20, 100};
        int div = average ? 2 : 1;
        for (int i = 0; i < nums.length; i++) {
            if (number >= nums[i] / div) {
                dices[i] = new Dice((int) (number / (nums[i] / div)), (int) nums[i], (int) (number % (nums[i] / div)));
            } else {
                dices[i] = new Dice(1, (int) nums[i], (int) ((number % nums[i]) - (nums[i] / div)));
            }
        }
        return dices;
    }
}
