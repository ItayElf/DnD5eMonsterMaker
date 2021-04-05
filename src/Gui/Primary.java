package Gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Primary extends JPanel {
    private final JTextField strField = new JTextField();
    private final JTextField dexField = new JTextField();
    private final JTextField conField = new JTextField();
    private final JTextField intField = new JTextField();
    private final JTextField wisField = new JTextField();
    private final JTextField chaField = new JTextField();
    private final JTextField diceCountField = new JTextField();
    private final JTextField diceSizeField = new JTextField();
    private final JTextField diceBonusField = new JTextField();
    private final JTextField acField = new JTextField();
    private final JTextField acDescriptionField = new JTextField();
    private final JTextField vulnerabilitiesField = new JTextField();
    private final JTextField immunitiesField = new JTextField();
    private final JTextField resistancesField = new JTextField();
    private final JTextField conditionImmunitiesField = new JTextField();

    public Primary() {
        super();
        setLayout(new MigLayout("fill"));
        strField.setFont(Defaults.getTextFieldFont());
        strField.setText("10");
        dexField.setFont(Defaults.getTextFieldFont());
        dexField.setText("10");
        conField.setFont(Defaults.getTextFieldFont());
        conField.setText("10");
        intField.setFont(Defaults.getTextFieldFont());
        intField.setText("10");
        wisField.setFont(Defaults.getTextFieldFont());
        wisField.setText("10");
        chaField.setFont(Defaults.getTextFieldFont());
        chaField.setText("10");
        diceCountField.setFont(Defaults.getTextFieldFont());
        diceSizeField.setFont(Defaults.getTextFieldFont());
        diceBonusField.setFont(Defaults.getTextFieldFont());
        acField.setFont(Defaults.getTextFieldFont());
        acDescriptionField.setFont(Defaults.getTextFieldFont());
        vulnerabilitiesField.setFont(Defaults.getTextFieldFont());
        immunitiesField.setFont(Defaults.getTextFieldFont());
        resistancesField.setFont(Defaults.getTextFieldFont());
        conditionImmunitiesField.setFont(Defaults.getTextFieldFont());

        JPanel top = new JPanel(new MigLayout("fillx"));

        JPanel abilities = new JPanel(new GridLayout(2, 3));
        abilities.setBorder(BorderFactory.createTitledBorder("Ability Scores"));
        addAbility(abilities, strField, "STR");
        addAbility(abilities, dexField, "DEX");
        addAbility(abilities, conField, "CON");
        addAbility(abilities, intField, "INT");
        addAbility(abilities, wisField, "WIS");
        addAbility(abilities, chaField, "CHA");

        JPanel container = new JPanel(new GridLayout(2, 1));
        JPanel hitDice = new JPanel(new MigLayout("fillx"));
        hitDice.setBorder(BorderFactory.createTitledBorder("Hit Dice"));
        hitDice.add(diceCountField, "width 25%");
        JLabel d = new JLabel("d");
        d.setFont(Defaults.getTextFieldFont());
        hitDice.add(d);
        hitDice.add(diceSizeField, "width 25%");
        JLabel plus = new JLabel("+");
        plus.setFont(Defaults.getTextFieldFont());
        hitDice.add(plus);
        hitDice.add(diceBonusField, "width 25%");
        container.add(hitDice);

        JPanel acPanel = new JPanel(new MigLayout("fillx"));
        JPanel panel = new JPanel(new MigLayout("fillx"));
        panel.setBorder(BorderFactory.createTitledBorder("Armor Class"));
        panel.add(acField, "width 100%");
        JPanel panel1 = new JPanel(new MigLayout("fillx"));
        panel1.setBorder(BorderFactory.createTitledBorder("Armor Class Description"));
        panel1.add(acDescriptionField, "width 100%");
        acPanel.add(panel, "width 33%");
        acPanel.add(panel1, "width 66%");
        container.add(acPanel);

        top.add(abilities, "width 40%");
        top.add(container, "width 60%");

        JPanel bottom = new JPanel(new MigLayout("fillx"));
        JPanel vulnerability = new JPanel(new MigLayout("fillx"));
        vulnerability.setBorder(BorderFactory.createTitledBorder("Vulnerabilities"));
        vulnerability.add(vulnerabilitiesField, "width 100%");
        JPanel immunity = new JPanel(new MigLayout("fillx"));
        immunity.setBorder(BorderFactory.createTitledBorder("Immunities"));
        immunity.add(immunitiesField, "width 100%");
        JPanel resistance = new JPanel(new MigLayout("fillx"));
        resistance.setBorder(BorderFactory.createTitledBorder("Resistances"));
        resistance.add(resistancesField, "width 100%");
        JPanel condition = new JPanel(new MigLayout("fillx"));
        condition.setBorder(BorderFactory.createTitledBorder("Condition Immunities"));
        condition.add(conditionImmunitiesField, "width 100%");
        bottom.add(vulnerability, "width 25%");
        bottom.add(immunity, "width 25%");
        bottom.add(resistance, "width 25%");
        bottom.add(condition, "width 25%");

        add(top, "wrap, width 100%, height 66%");
        add(bottom, "wrap, width 100%, height 33%");
    }

    private void addAbility(JPanel container, JTextField field, String border) {
        JPanel panel = new JPanel(new MigLayout("fillx"));
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createEmptyBorder(), border);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(titledBorder);
        panel.add(field, "align 50% 50%, width 100%");
        container.add(panel, "width 100%");
    }

    public void reset() {
        strField.setText("10");
        dexField.setText("10");
        conField.setText("10");
        intField.setText("10");
        wisField.setText("10");
        chaField.setText("10");
        diceCountField.setText("");
        diceSizeField.setText("");
        diceBonusField.setText("");
        acField.setText("");
        acDescriptionField.setText("");
        vulnerabilitiesField.setText("");
        immunitiesField.setText("");
        resistancesField.setText("");
        conditionImmunitiesField.setText("");
    }

    public JTextField getStrField() {
        return strField;
    }

    public JTextField getDexField() {
        return dexField;
    }

    public JTextField getConField() {
        return conField;
    }

    public JTextField getIntField() {
        return intField;
    }

    public JTextField getWisField() {
        return wisField;
    }

    public JTextField getChaField() {
        return chaField;
    }

    public JTextField getDiceCountField() {
        return diceCountField;
    }

    public JTextField getDiceSizeField() {
        return diceSizeField;
    }

    public JTextField getDiceBonusField() {
        return diceBonusField;
    }

    public JTextField getAcField() {
        return acField;
    }

    public JTextField getAcDescriptionField() {
        return acDescriptionField;
    }

    public JTextField getVulnerabilitiesField() {
        return vulnerabilitiesField;
    }

    public JTextField getImmunitiesField() {
        return immunitiesField;
    }

    public JTextField getResistancesField() {
        return resistancesField;
    }

    public JTextField getConditionImmunitiesField() {
        return conditionImmunitiesField;
    }
}
