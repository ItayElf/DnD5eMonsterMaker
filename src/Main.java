import Gui.Root;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = null;
        if (args.length == 1) {
            file = new File(args[0]);
        }
        Root root = new Root(file);
    }
}
