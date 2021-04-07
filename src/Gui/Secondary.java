package Gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Secondary extends JPanel {
    private final JTextField savingThrowsField = new JTextField();
    private final JTextField skillsField = new JTextField();
    private final JTextField sensesField = new JTextField();
    private final JTextField languagesField = new JTextField();
    private final JTextField speedField = new JTextField();
    private final JTextField otherSpeedField = new JTextField();

    public Secondary() {
        super();
        setLayout(new GridLayout(3, 2));
        savingThrowsField.setFont(Defaults.getTextFieldFont());
        skillsField.setFont(Defaults.getTextFieldFont());
        sensesField.setFont(Defaults.getTextFieldFont());
        languagesField.setFont(Defaults.getTextFieldFont());
        speedField.setFont(Defaults.getTextFieldFont());
        otherSpeedField.setFont(Defaults.getTextFieldFont());

        JPanel saving = new JPanel(new MigLayout("fillx"));
        saving.setBorder(BorderFactory.createTitledBorder("Saving Throws"));
        saving.add(savingThrowsField, "width 100%");
        JPanel skills = new JPanel(new MigLayout("fillx"));
        skills.setBorder(BorderFactory.createTitledBorder("Skills"));
        skills.add(skillsField, "width 100%");
        JPanel senses = new JPanel(new MigLayout("fillx"));
        senses.setBorder(BorderFactory.createTitledBorder("Senses"));
        senses.add(sensesField, "width 100%");
        JPanel languages = new JPanel(new MigLayout("fillx"));
        languages.setBorder(BorderFactory.createTitledBorder("Languages"));
        languages.add(languagesField, "width 100%");
        JPanel speed = new JPanel(new MigLayout("fillx"));
        speed.setBorder(BorderFactory.createTitledBorder("Speed"));
        speed.add(speedField, "width 100%");
        JPanel other = new JPanel(new MigLayout("fillx"));
        other.setBorder(BorderFactory.createTitledBorder("Other Speed"));
        other.add(otherSpeedField, "width 100%");

        add(saving);
        add(skills);
        add(senses);
        add(languages);
        add(speed);
        add(other);
    }

    public void reset() {
        savingThrowsField.setText("");
        skillsField.setText("");
        sensesField.setText("");
        languagesField.setText("");
        speedField.setText("");
        otherSpeedField.setText("");
    }

    public JTextField getSavingThrowsField() {
        return savingThrowsField;
    }

    public JTextField getSkillsField() {
        return skillsField;
    }

    public JTextField getSensesField() {
        return sensesField;
    }

    public JTextField getLanguagesField() {
        return languagesField;
    }

    public JTextField getSpeedField() {
        return speedField;
    }

    public JTextField getOtherSpeedField() {
        return otherSpeedField;
    }
}
