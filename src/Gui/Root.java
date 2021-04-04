package Gui;

import MonsterMakerPackage.Dice;
import MonsterMakerPackage.HTMLHelper;
import MonsterMakerPackage.MonsterMaker;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Root extends JFrame {
    private final Identity identity = new Identity();

    public Root() {
        super("5e Monster Maker");
        setSize(Defaults.getScreenDimension());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Identity", identity);
        add(tabbedPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(e -> reset());
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) { return false;}
                    return f.getName().toLowerCase().endsWith(".5emon");
                }

                @Override
                public String getDescription() {
                    return "*.5emon";
                }
            });
            int val = fileChooser.showOpenDialog(null);
            if (val == JFileChooser.APPROVE_OPTION) {
                try {
                    open(fileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        menu.add(openItem);
        menu.addSeparator();
        JMenuItem saveItem = new JMenuItem("Save");
        menu.add(saveItem);
        saveItem.addActionListener(e -> {
            try {
                MonsterMaker monster = createMonster();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(monster.getName() + ".5emon"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) { return false;}
                        return f.getName().toLowerCase().endsWith(".5emon");
                    }

                    @Override
                    public String getDescription() {
                        return "*.5emon";
                    }
                });
                int val = fileChooser.showSaveDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".5emon")) {
                        if (file.getName().contains(".")) {
                            String[] split = file.getAbsolutePath().split("\\.");
                            split[split.length - 1] = "";
                            file = new File(file.getParent(), String.join(".", split) + "5emon");
                        } else {
                            file = new File(file.getParent(), file.getName() + ".5emon");
                        }
                    }
                    monster.saveAs5emon(file);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem exportItem = new JMenuItem("Export");
        exportItem.addActionListener(e-> {
            try {
                MonsterMaker monster = createMonster();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(monster.getName() + ".html"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) { return false;}
                        return f.getName().toLowerCase().endsWith(".html");
                    }

                    @Override
                    public String getDescription() {
                        return "*.html";
                    }
                });
                int val = fileChooser.showSaveDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".html")) {
                        if (file.getName().contains(".")) {
                            String[] split = file.getAbsolutePath().split("\\.");
                            split[split.length - 1] = "";
                            file = new File(file.getParent(), String.join(".", split) + "html");
                        } else {
                            file = new File(file.getParent(), file.getName() + ".html");
                        }
                    }
                    HTMLHelper.monsterToHtml(monster, file);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        exportItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(exportItem);
        JMenuItem exportOpenItem = new JMenuItem("Export and Open");
        exportOpenItem.addActionListener(e-> {
            try {
                MonsterMaker monster = createMonster();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(monster.getName() + ".html"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) { return false;}
                        return f.getName().toLowerCase().endsWith(".html");
                    }

                    @Override
                    public String getDescription() {
                        return "*.html";
                    }
                });
                int val = fileChooser.showSaveDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".html")) {
                        if (file.getName().contains(".")) {
                            String[] split = file.getAbsolutePath().split("\\.");
                            split[split.length - 1] = "";
                            file = new File(file.getParent(), String.join(".", split) + "html");
                        } else {
                            file = new File(file.getParent(), file.getName() + ".html");
                        }
                    }
                    HTMLHelper.monsterToHtmlOpen(monster, file);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        exportOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu.add(exportOpenItem);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private MonsterMaker createMonster() throws Exception {
        MonsterMaker monster = new MonsterMaker();
        // Identity
        monster.setName(identity.getNameField().getText());
        monster.setSize((String) identity.getSizeBox().getSelectedItem());
        monster.setType((String) identity.getTypeBox().getSelectedItem());
        monster.setTag(identity.getTagsField().getText());
        monster.setAlignment((String) identity.getAlignmentBox().getSelectedItem());
        monster.setChallenge(getChallenge(identity.getChallengeField().getText()));

        monster.setHitDice(new Dice(1,1,1));
        monster.setAbilityScores(new int[] {0,0,0,0,0,0});
        return monster;
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

    private void open(File file) throws IOException {
        MonsterMaker monster = MonsterMaker.from5emon(file);

        //Identity
        identity.getNameField().setText(monster.getName());
        identity.getTagsField().setText(monster.getTag());
        identity.getChallengeField().setText(monster.challengeString());
        identity.getAlignmentBox().setSelectedItem(monster.getAlignment());
        identity.getSizeBox().setSelectedItem(monster.getSize());
        identity.getTypeBox().setSelectedItem(monster.getType());
    }

    public void reset() {
        identity.reset();
    }
}
