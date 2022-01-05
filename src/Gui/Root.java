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
    private final Primary primary = new Primary();
    private final Secondary secondary = new Secondary();
    private final TraitsPanel traitsPanel = new TraitsPanel();
    private final ActionsPanel actionsPanel = new ActionsPanel();
    private final TraitsPanel reactionsPanel = new TraitsPanel();

    public Root(File openedFile) {
        super("5e Monster Maker");
        setSize(Defaults.getScreenDimension());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/static/monstermaker.png")));
        setIconImage(icon.getImage());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Identity", identity);
        tabbedPane.add("Primary", primary);
        tabbedPane.add("Secondary", secondary);
        tabbedPane.add("Traits", traitsPanel);
        tabbedPane.add("Actions", actionsPanel);
        tabbedPane.add("Reactions", reactionsPanel);
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
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Monsters"));
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
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
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Monsters"));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        }
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
                    if (file.exists()) {
                        int response = JOptionPane.showConfirmDialog(null, file.getAbsolutePath() + " already exists.\nDo you want to replace it?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            monster.saveAs5emon(file);
                        }
                    } else {
                        monster.saveAs5emon(file);
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem exportItem = new JMenuItem("Export");
        exportItem.addActionListener(e -> {
            try {
                MonsterMaker monster = createMonster();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(monster.getName() + ".html"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Monsters"));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        }
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
                    if (file.exists()) {
                        int response = JOptionPane.showConfirmDialog(null, file.getAbsolutePath() + " already exists.\nDo you want to replace it?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            HTMLHelper.monsterToHtml(monster, file);
                        }
                    } else {
                        HTMLHelper.monsterToHtml(monster, file);
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        exportItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(exportItem);
        JMenuItem exportOpenItem = new JMenuItem("Export and Open");
        exportOpenItem.addActionListener(e -> {
            try {
                MonsterMaker monster = createMonster();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(monster.getName() + ".html"));
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Monsters"));
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        }
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
                    if (file.exists()) {
                        int response = JOptionPane.showConfirmDialog(null, file.getAbsolutePath() + " already exists.\nDo you want to replace it?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            HTMLHelper.monsterToHtmlOpen(monster, file);
                        }
                    } else {
                        HTMLHelper.monsterToHtmlOpen(monster, file);
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(tabbedPane, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
        exportOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu.add(exportOpenItem);
        menu.addSeparator();
        JMenuItem importTraitsActions = new JMenuItem("Import traits and actions from another monster");
        importTraitsActions.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        importTraitsActions.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Monsters"));
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
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
                    importTraitsActions(fileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        menu.add(importTraitsActions);
        menuBar.add(new Tools(identity.getChallengeField(),
                new JTextField[]{primary.getDiceCountField(), primary.getDiceSizeField(), primary.getDiceBonusField()},
                identity.getSizeBox(),
                new JTextField[]{primary.getStrField(), primary.getDexField(), primary.getConField(), primary.getIntField(), primary.getWisField(), primary.getChaField()},
                secondary.getSkillsField()));
        setJMenuBar(menuBar);

        if (openedFile != null) {
            try {
                open(openedFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "File couldn't be opened.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
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

        // Primary
        monster.setAbilityScores(new int[6]);
        try {
            monster.setAbilityScores(0, Integer.parseInt(primary.getStrField().getText()));
            monster.setAbilityScores(1, Integer.parseInt(primary.getDexField().getText()));
            monster.setAbilityScores(2, Integer.parseInt(primary.getConField().getText()));
            monster.setAbilityScores(3, Integer.parseInt(primary.getIntField().getText()));
            monster.setAbilityScores(4, Integer.parseInt(primary.getWisField().getText()));
            monster.setAbilityScores(5, Integer.parseInt(primary.getChaField().getText()));
        } catch (NumberFormatException e) {
            throw new Exception("Ability Scores can only be numbers");
        }
        try {
            monster.setHitDice(new Dice(Integer.parseInt(primary.getDiceCountField().getText()),
                    Integer.parseInt(primary.getDiceSizeField().getText()),
                    Integer.parseInt(primary.getDiceBonusField().getText())));
        } catch (NumberFormatException e) {
            throw new Exception("Hit dice parameters can only be numbers");
        }
        try {
            monster.setArmorClass(Integer.parseInt(primary.getAcField().getText()));
        } catch (NumberFormatException e) {
            throw new Exception("Armor class can only be a number");
        }
        monster.setArmorClassDesc(primary.getAcDescriptionField().getText());
        monster.setDamageVulnerabilities(primary.getVulnerabilitiesField().getText());
        monster.setDamageImmunities(primary.getImmunitiesField().getText());
        monster.setDamageResistances(primary.getResistancesField().getText());
        monster.setConditionImmunities(primary.getConditionImmunitiesField().getText());

        // Secondary
        monster.setSavingThrows(secondary.getSavingThrowsField().getText());
        monster.setSkills(secondary.getSkillsField().getText());
        monster.setSenses(secondary.getSensesField().getText());
        monster.setLanguages(secondary.getLanguagesField().getText());
        try {
            monster.setSpeed(Integer.parseInt(secondary.getSpeedField().getText()));
        } catch (NumberFormatException e) {
            throw new Exception("Speed can only be a number");
        }
        monster.setOtherSpeed(secondary.getOtherSpeedField().getText());

        // TraitsPanel
        monster.setTraits(traitsPanel.getTraits());

        // ActionPanel
        monster.setActions(actionsPanel.getActions());

        // ReactionPanel
        monster.setReactions(reactionsPanel.getTraits());

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

        // Identity
        identity.getNameField().setText(monster.getName());
        identity.getTagsField().setText(monster.getTag());
        identity.getChallengeField().setText(monster.challengeString());
        identity.getAlignmentBox().setSelectedItem(monster.getAlignment());
        identity.getSizeBox().setSelectedItem(monster.getSize());
        identity.getTypeBox().setSelectedItem(monster.getType());

        // Primary
        primary.getStrField().setText(monster.getAbilityScores()[0] + "");
        primary.getDexField().setText(monster.getAbilityScores()[1] + "");
        primary.getConField().setText(monster.getAbilityScores()[2] + "");
        primary.getIntField().setText(monster.getAbilityScores()[3] + "");
        primary.getWisField().setText(monster.getAbilityScores()[4] + "");
        primary.getChaField().setText(monster.getAbilityScores()[5] + "");
        primary.getDiceCountField().setText(monster.getHitDice().getDiceCount() + "");
        primary.getDiceSizeField().setText(monster.getHitDice().getDiceSize() + "");
        primary.getDiceBonusField().setText(monster.getHitDice().getBonus() + "");
        primary.getAcField().setText(monster.getArmorClass() + "");
        primary.getAcDescriptionField().setText(monster.getArmorClassDesc());
        primary.getVulnerabilitiesField().setText(monster.getDamageVulnerabilities());
        primary.getImmunitiesField().setText(monster.getDamageImmunities());
        primary.getResistancesField().setText(monster.getDamageResistances());
        primary.getConditionImmunitiesField().setText(monster.getConditionImmunities());

        // Secondary
        secondary.getSavingThrowsField().setText(monster.getSavingThrows());
        secondary.getSkillsField().setText(monster.getSkills());
        secondary.getSensesField().setText(monster.getSenses());
        secondary.getLanguagesField().setText(monster.getLanguages());
        secondary.getSpeedField().setText(monster.getSpeed() + "");
        secondary.getOtherSpeedField().setText(monster.getOtherSpeed());

        // TraitsPanel
        traitsPanel.load(monster.getTraits());

        // ActionPanel
        actionsPanel.load(monster.getActions());

        // ReactionPanel
        reactionsPanel.load(monster.getReactions());
    }

    public void reset() {
        identity.reset();
        primary.reset();
        secondary.reset();
        traitsPanel.load(null);
        actionsPanel.load(null);
        reactionsPanel.load(null);
    }

    public void importTraitsActions(File file) throws IOException {
        MonsterMaker monster = MonsterMaker.from5emon(file);

        JFrame topLevel = new JFrame("Import");
        topLevel.setSize(Defaults.getScreenWidth() / 10 * 7, Defaults.getScreenHeight());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Traits", new ImportTraits(monster.getTraits(), traitsPanel));
        tabbedPane.add("Actions", new ImportActions(monster.getActions(), actionsPanel));
        tabbedPane.add("Reactions", new ImportTraits(monster.getReactions(), reactionsPanel));

        topLevel.add(tabbedPane);
        topLevel.setVisible(true);
    }
}
