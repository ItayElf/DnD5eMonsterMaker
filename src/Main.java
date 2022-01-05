import Gui.Root;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (!new File("Monsters").isDirectory()) {
            new File("Monsters").mkdir();
        }
        File file = null;
        if (args.length == 1) {
            file = new File(args[0]);
        }
        new Root(file);
    }
}
