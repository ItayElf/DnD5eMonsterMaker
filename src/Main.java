import Gui.Root;
import MonsterMakerPackage.HTMLHelper;
import MonsterMakerPackage.MonsterMaker;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println(HTMLHelper.monsterToHtmlRaw(MonsterMaker.from5emon(new File(args[0]))));
            return;
        }

        Root root = new Root();
    }
}
