import Gui.Root;
import MonsterMakerPackage.HTMLHelper;
import MonsterMakerPackage.MonsterMaker;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println(HTMLHelper.monsterToHtmlRaw(MonsterMaker.from5emon(new File(args[0]))));
            return;
        }

        Root root = new Root();


//        MonsterMaker monsterMaker = new MonsterMaker();
//        monsterMaker.setName("Gunthar");
//        monsterMaker.setSubtitle("large monstrosity, chaotic netural");
//
//        monsterMaker.setArmorClass(16);
//        monsterMaker.setArmorClassDesc("Natural Armor");
//        monsterMaker.setHitDice(new Dice(23, 10, 69));
//        monsterMaker.setSpeed(40);
//
//        monsterMaker.setAbilityScores(new int[]{18, 11, 16, 6, 16, 9});
//        monsterMaker.setSkills(new String[]{"Perception +7"});
//        monsterMaker.setSenses(new String[] {"Darkvision 60 ft.", "Passive Perception 17"});
//        monsterMaker.setLanguages(new String[] {"Abyssal", "Common"});
//        monsterMaker.setChallenge(9);
//
//        monsterMaker.setTraits(new Trait[]{new Trait("Charge", "asdasfafaf agfadg adgagadg adgadgag adgag adfsfhsfvx asdads xxza  asd"),
//        new Trait("Labyrinthine Recall", "laiczjxncvjhasfanfaj kdjs kj kasj d"), new Trait("Reckless", "asdasda sdaasd ada sd asd  aas")});
//
//        Action action = new Action("Greataxe", 1, 7, new int[]{5}, "one", new Dice(4,12,4), "slashing");
//        Action action1 = new Action("Gore", 2, 7, new int[]{80, 320}, "two", new Dice(4,8,4), "piercing", " and the target is knocked prone");
//        Action action2 = new Action("Etherealness", "The hag magically enters the Ethereal Plane from the Material Plane, or vice versa. To do so, the hag must have a heartstone in her possession.");
//        monsterMaker.setActions(new Action[]{action, action1, action2});
//
//        monsterMaker.setReactions(new Action[]{new Action("Shield", "When a creature makes an attack against the wearer of the guardian's amulet, the guardian grants a +2 bonus to the wearer's AC if the guardian is within 5 feet of the wearer.")});
//
//        monsterMaker.saveAs5emon(new File("Gunthar.5emon"));
//        //HTMLHelper.monsterToHtml(monsterMaker);
//        MonsterMaker a = MonsterMaker.from5emon(new File("Gunthar.5emon"));
//        System.out.println(a.getName());
//        System.out.println(a.getSubtitle());
    }
}
