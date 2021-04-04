package Gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Identity extends JPanel {
    private final JTextField nameField = new JTextField();
    private final JTextField tagsField = new JTextField();
    private final JTextField challengeField = new JTextField();
    private final JComboBox<String> alignmentBox = new JComboBox<>(new String[]{"lawful evil", "netural evil", "chaotic evil", "lawful netural", "netural", "chaotic netural", "lawful good", "netural good", "chaotic good", "unaligned"});
    private final JComboBox<String> sizeBox = new JComboBox<>(new String[]{"Tiny", "Small", "Medium", "Large", "Huge", "Gargantuan"});
    private final JComboBox<String> typeBox = new JComboBox<>(new String[]{"aberration", "beast", "celestial", "construct", "dragon", "elemental", "fey", "fiend", "giant", "humanoid", "monstrosity", "ooze", "plant", "undead"});

    public Identity() {
        super();
        setLayout(new MigLayout("fill"));
        nameField.setFont(Defaults.getTextFieldFont());
        tagsField.setFont(Defaults.getTextFieldFont());
        challengeField.setFont(Defaults.getTextFieldFont());
        alignmentBox.setFont(Defaults.getComboBoxFont());
        sizeBox.setFont(Defaults.getComboBoxFont());
        sizeBox.setSelectedIndex(2);
        typeBox.setFont(Defaults.getComboBoxFont());
        typeBox.setEditable(true);
        typeBox.setSelectedItem("");

        JPanel top = new JPanel(new MigLayout("fillx"));
        JPanel namePanel = new JPanel(new MigLayout("fillx"));
        namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        namePanel.add(nameField, "width 100%");
        JPanel tagsPanel = new JPanel(new MigLayout("fillx"));
        tagsPanel.setBorder(BorderFactory.createTitledBorder("Tags"));
        tagsPanel.add(tagsField, "width 100%");
        top.add(namePanel, "width 60%");
        top.add(tagsPanel, "width 40%");
        add(top, "wrap, width 100%, height 33%");

        JPanel middle = new JPanel(new MigLayout("fillx"));
        JPanel challengePanel = new JPanel(new MigLayout("fillx"));
        challengePanel.setBorder(BorderFactory.createTitledBorder("Challenge Rating"));
        challengePanel.add(challengeField, "width 100%");
        JPanel alignmentPanel = new JPanel(new MigLayout("fillx"));
        alignmentPanel.setBorder(BorderFactory.createTitledBorder("Alignment"));
        alignmentPanel.add(alignmentBox, "width 100%");
        middle.add(challengePanel, "width 40%");
        middle.add(alignmentPanel, "width 60%");
        add(middle, "wrap, width 100%, height 33%");

        JPanel bottom = new JPanel(new MigLayout("fillx"));
        JPanel sizePanel = new JPanel(new MigLayout("fillx"));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        sizePanel.add(sizeBox, "width 100%");
        JPanel typePanel = new JPanel(new MigLayout("fillx"));
        typePanel.setBorder(BorderFactory.createTitledBorder("Type"));
        typePanel.add(typeBox, "width 100%");
        bottom.add(sizePanel, "width 50%");
        bottom.add(typePanel, "width 50%");
        add(bottom, "wrap, width 100%, height 33%");
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getTagsField() {
        return tagsField;
    }

    public JTextField getChallengeField() {
        return challengeField;
    }

    public JComboBox<String> getAlignmentBox() {
        return alignmentBox;
    }

    public JComboBox<String> getSizeBox() {
        return sizeBox;
    }

    public JComboBox<String> getTypeBox() {
        return typeBox;
    }

    public void reset() {
        nameField.setText("");
        tagsField.setText("");
        challengeField.setText("");
        alignmentBox.setSelectedIndex(0);
        sizeBox.setSelectedIndex(2);
        typeBox.setSelectedItem("");
    }
}
