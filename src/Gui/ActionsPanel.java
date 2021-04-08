package Gui;

import MonsterMakerPackage.Action;
import MonsterMakerPackage.Dice;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ActionsPanel extends JPanel {
    private Action[] actions = new Action[0];
    private final JList<Action> list;
    private final DefaultListModel<Action> listModel;

    public ActionsPanel() {
        super();
        setLayout(new MigLayout("fill"));
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Actions"));

        listModel = new DefaultListModel<>();
        listModel.addAll(Arrays.asList(actions));
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFont(Defaults.getComboBoxFont());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    editAction(list.getSelectedIndex());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel buttons = new JPanel(new MigLayout("filly"));

        JButton moveUp = new JButton("Move Up");
        moveUp.setFont(Defaults.getComboBoxFont());
        moveUp.addActionListener(e -> moveUpAction(list.getSelectedIndex()));

        JButton moveDown = new JButton("Move Down");
        moveDown.setFont(Defaults.getComboBoxFont());
        moveDown.addActionListener(e -> moveDownAction(list.getSelectedIndex()));

        JButton add = new JButton("Add");
        add.setFont(Defaults.getComboBoxFont());
        add.addActionListener(e -> addAction());

        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> editAction(list.getSelectedIndex()));
        edit.setFont(Defaults.getComboBoxFont());

        JButton remove = new JButton("Remove");
        remove.setFont(Defaults.getComboBoxFont());
        remove.addActionListener(e -> removeAction(list.getSelectedIndex()));

        buttons.add(moveUp, "wrap, width 100%");
        buttons.add(moveDown, "wrap, width 100%");
        buttons.add(add, "wrap, width 100%");
        buttons.add(edit, "wrap, width 100%");
        buttons.add(remove, "wrap, width 100%");

        panel.add(scrollPane, "width 70%, height 100%");
        panel.add(buttons, "width 30%, height 100%");

        add(panel, "width 100%, height 100%");
    }

    private void addAction() {
        JTextField attackBonusField = new JTextField();
        JTextField reachField = new JTextField();
        JTextField targetsField = new JTextField();
        JTextField diceCountField = new JTextField();
        JTextField diceSizeField = new JTextField();
        JTextField diceBonusField = new JTextField();
        JComboBox<String> damageTypeBox = new JComboBox<>(new String[]{"acid", "bludgeoning", "cold", "fire", "force", "lightning", "necrotic", "piercing", "poison", "psychic", "radiant", "slashing", "thunder"});
        damageTypeBox.setEditable(true);
        damageTypeBox.setSelectedItem("");

        JFrame topLevel = new JFrame("New Action");
        topLevel.setSize(Defaults.getScreenWidth() / 2, (int) (Defaults.getScreenHeight() * 1.5));
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel namePanel = new JPanel(new MigLayout("fillx"));
        namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        JTextField nameField = new JTextField();
        nameField.setFont(Defaults.getTextFieldFont());
        namePanel.add(nameField, "width 100%");
        wrapper.add(namePanel, "wrap, width 100%");

        JPanel modifiers = new JPanel(new MigLayout("fillx"));
        JPanel mode = new JPanel(new MigLayout("fillx"));
        mode.setBorder(BorderFactory.createTitledBorder("Mode"));
        JComboBox<String> modeBox = new JComboBox<>(new String[]{"", "Melee", "Ranged"});
        modeBox.setFont(Defaults.getComboBoxFontSmall());
        modeBox.addActionListener(e -> {
            if (modeBox.getSelectedIndex() == 0) {
                attackBonusField.setEnabled(false);
                reachField.setEnabled(false);
                targetsField.setEnabled(false);
                diceCountField.setEnabled(false);
                diceSizeField.setEnabled(false);
                diceBonusField.setEnabled(false);
                damageTypeBox.setEnabled(false);
            } else {
                attackBonusField.setEnabled(true);
                reachField.setEnabled(true);
                targetsField.setEnabled(true);
                diceCountField.setEnabled(true);
                diceSizeField.setEnabled(true);
                diceBonusField.setEnabled(true);
                damageTypeBox.setEnabled(true);
            }
        });
        modeBox.setSelectedIndex(0);
        mode.add(modeBox, "width 100%");
        JPanel attackBonus = new JPanel(new MigLayout("fillx"));
        attackBonus.setBorder(BorderFactory.createTitledBorder("Attack Bonus"));
        attackBonusField.setFont(Defaults.getTextFieldFontSmall());
        attackBonus.add(attackBonusField, "width 100%");
        modifiers.add(mode, "width 50%");
        modifiers.add(attackBonus, "wrap, width 50%");

        JPanel reach = new JPanel(new MigLayout("fillx"));
        reach.setBorder(BorderFactory.createTitledBorder("Reach"));
        reachField.setFont(Defaults.getTextFieldFontSmall());
        reach.add(reachField, "width 100%");
        JPanel targets = new JPanel(new MigLayout("fillx"));
        targets.setBorder(BorderFactory.createTitledBorder("Targets"));
        targetsField.setFont(Defaults.getTextFieldFontSmall());
        targets.add(targetsField, "width 100%");
        modifiers.add(reach, "width 50%");
        modifiers.add(targets, "wrap, width 50%");
        wrapper.add(modifiers, "wrap, width 100%");

        JPanel dice = new JPanel(new MigLayout("fillx"));
        dice.setBorder(BorderFactory.createTitledBorder("Damage Dice"));
        diceCountField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceCountField, "width 25%");
        JLabel d = new JLabel("d");
        d.setFont(Defaults.getTextFieldFontSmall());
        dice.add(d);
        diceSizeField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceSizeField, "width 25%");
        JLabel plus = new JLabel("+");
        plus.setFont(Defaults.getTextFieldFontSmall());
        dice.add(plus);
        diceBonusField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceBonusField, "width 25%");
        wrapper.add(dice, "wrap, width 100%");

        JPanel damageType = new JPanel(new MigLayout("fillx"));
        damageType.setBorder(BorderFactory.createTitledBorder("Damage Type"));
        damageTypeBox.setFont(Defaults.getTextFieldFontSmall());
        damageType.add(damageTypeBox, "width 100%");
        wrapper.add(damageType, "wrap, width 100%");


        JPanel description = new JPanel(new MigLayout("fillx"));
        description.setBorder(BorderFactory.createTitledBorder("Description"));
        JTextArea descArea = new JTextArea(9, 25);
        descArea.setLineWrap(true);
        descArea.setFont(Defaults.getComboBoxFont());
        descArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        description.add(scrollPane, "width 100%");
        wrapper.add(description, "wrap, width 100%");

        JPanel buttons = new JPanel(new MigLayout("fillx"));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> topLevel.dispose());
        buttons.add(cancel);
        JButton accept = new JButton("Accept");
        accept.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                Action action;
                int bonusToHit, count, size, bonus;
                if (modeBox.getSelectedIndex() == 0) {
                    action = new Action(nameField.getText(), descArea.getText());
                } else {
                    try {
                        bonusToHit = Integer.parseInt(attackBonusField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(topLevel, "Attack bonus must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        Integer.parseInt(targetsField.getText());
                        JOptionPane.showMessageDialog(topLevel, "Targets cannot be a number (ex. \"one\" instead of \"1\")", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (NumberFormatException ignored) {
                    }
                    try {
                        count = Integer.parseInt(diceCountField.getText());
                        size = Integer.parseInt(diceSizeField.getText());
                        bonus = Integer.parseInt(diceBonusField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(topLevel, "Damage dice parameters can only be numbers", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    action = new Action(nameField.getText(), modeBox.getSelectedIndex(), bonusToHit, reachField.getText(), targetsField.getText(), new Dice(count, size, bonus), (String) damageTypeBox.getSelectedItem(), descArea.getText());
                }
                addAction(action);
                topLevel.dispose();
            } else {
                JOptionPane.showMessageDialog(topLevel, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttons.add(accept, "align 100%");
        wrapper.add(buttons, "width 100%");

        topLevel.add(wrapper);
        topLevel.setVisible(true);
    }

    private void editAction(int index) {
        Action action = actions[index];
        JTextField attackBonusField = new JTextField();
        attackBonusField.setText(action.getBonusToHit() + "");
        JTextField reachField = new JTextField();
        reachField.setText(action.getReach());
        JTextField targetsField = new JTextField();
        targetsField.setText(action.getTargets());
        JTextField diceCountField = new JTextField();
        diceCountField.setText(action.getDamageDice().getDiceCount() + "");
        JTextField diceSizeField = new JTextField();
        diceSizeField.setText(action.getDamageDice().getDiceSize() + "");
        JTextField diceBonusField = new JTextField();
        diceBonusField.setText(action.getDamageDice().getBonus() + "");
        JComboBox<String> damageTypeBox = new JComboBox<>(new String[]{"acid", "bludgeoning", "cold", "fire", "force", "lightning", "necrotic", "piercing", "poison", "psychic", "radiant", "slashing", "thunder"});
        damageTypeBox.setEditable(true);
        damageTypeBox.setSelectedItem(action.getDamageType());

        JFrame topLevel = new JFrame("Edit Action");
        topLevel.setSize(Defaults.getScreenWidth() / 2, (int) (Defaults.getScreenHeight() * 1.5));
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel namePanel = new JPanel(new MigLayout("fillx"));
        namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        JTextField nameField = new JTextField();
        nameField.setFont(Defaults.getTextFieldFont());
        nameField.setText(action.getName());
        namePanel.add(nameField, "width 100%");
        wrapper.add(namePanel, "wrap, width 100%");

        JPanel modifiers = new JPanel(new MigLayout("fillx"));
        JPanel mode = new JPanel(new MigLayout("fillx"));
        mode.setBorder(BorderFactory.createTitledBorder("Mode"));
        JComboBox<String> modeBox = new JComboBox<>(new String[]{"", "Melee", "Ranged"});
        modeBox.setFont(Defaults.getComboBoxFontSmall());
        modeBox.addActionListener(e -> {
            if (modeBox.getSelectedIndex() == 0) {
                attackBonusField.setEnabled(false);
                reachField.setEnabled(false);
                targetsField.setEnabled(false);
                diceCountField.setEnabled(false);
                diceSizeField.setEnabled(false);
                diceBonusField.setEnabled(false);
                damageTypeBox.setEnabled(false);
            } else {
                attackBonusField.setEnabled(true);
                reachField.setEnabled(true);
                targetsField.setEnabled(true);
                diceCountField.setEnabled(true);
                diceSizeField.setEnabled(true);
                diceBonusField.setEnabled(true);
                damageTypeBox.setEnabled(true);
            }
        });
        modeBox.setSelectedIndex(action.getMode());
        mode.add(modeBox, "width 100%");
        JPanel attackBonus = new JPanel(new MigLayout("fillx"));
        attackBonus.setBorder(BorderFactory.createTitledBorder("Attack Bonus"));
        attackBonusField.setFont(Defaults.getTextFieldFontSmall());
        attackBonus.add(attackBonusField, "width 100%");
        modifiers.add(mode, "width 50%");
        modifiers.add(attackBonus, "wrap, width 50%");

        JPanel reach = new JPanel(new MigLayout("fillx"));
        reach.setBorder(BorderFactory.createTitledBorder("Reach"));
        reachField.setFont(Defaults.getTextFieldFontSmall());
        reach.add(reachField, "width 100%");
        JPanel targets = new JPanel(new MigLayout("fillx"));
        targets.setBorder(BorderFactory.createTitledBorder("Targets"));
        targetsField.setFont(Defaults.getTextFieldFontSmall());
        targets.add(targetsField, "width 100%");
        modifiers.add(reach, "width 50%");
        modifiers.add(targets, "wrap, width 50%");
        wrapper.add(modifiers, "wrap, width 100%");

        JPanel dice = new JPanel(new MigLayout("fillx"));
        dice.setBorder(BorderFactory.createTitledBorder("Damage Dice"));
        diceCountField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceCountField, "width 25%");
        JLabel d = new JLabel("d");
        d.setFont(Defaults.getTextFieldFontSmall());
        dice.add(d);
        diceSizeField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceSizeField, "width 25%");
        JLabel plus = new JLabel("+");
        plus.setFont(Defaults.getTextFieldFontSmall());
        dice.add(plus);
        diceBonusField.setFont(Defaults.getTextFieldFontSmall());
        dice.add(diceBonusField, "width 25%");
        wrapper.add(dice, "wrap, width 100%");

        JPanel damageType = new JPanel(new MigLayout("fillx"));
        damageType.setBorder(BorderFactory.createTitledBorder("Damage Type"));
        damageTypeBox.setFont(Defaults.getTextFieldFontSmall());
        damageType.add(damageTypeBox, "width 100%");
        wrapper.add(damageType, "wrap, width 100%");


        JPanel description = new JPanel(new MigLayout("fillx"));
        description.setBorder(BorderFactory.createTitledBorder("Description"));
        JTextArea descArea = new JTextArea(9, 25);
        descArea.setLineWrap(true);
        descArea.setFont(Defaults.getComboBoxFont());
        descArea.setText(action.getDescription());
        descArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        description.add(scrollPane, "width 100%");
        wrapper.add(description, "wrap, width 100%");

        JPanel buttons = new JPanel(new MigLayout("fillx"));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> topLevel.dispose());
        buttons.add(cancel);
        JButton accept = new JButton("Accept");
        accept.addActionListener(e -> {
            if (!nameField.getText().equals("")) {
                Action action1;
                int bonusToHit, count, size, bonus;
                if (modeBox.getSelectedIndex() == 0) {
                    action1 = new Action(nameField.getText(), descArea.getText());
                } else {
                    try {
                        bonusToHit = Integer.parseInt(attackBonusField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(topLevel, "Attack bonus must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        Integer.parseInt(targetsField.getText());
                        JOptionPane.showMessageDialog(topLevel, "Targets cannot be a number (ex. \"one\" instead of \"1\")", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (NumberFormatException ignored) {
                    }
                    try {
                        count = Integer.parseInt(diceCountField.getText());
                        size = Integer.parseInt(diceSizeField.getText());
                        bonus = Integer.parseInt(diceBonusField.getText());
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(topLevel, "Damage dice parameters can only be numbers", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    action1 = new Action(nameField.getText(), modeBox.getSelectedIndex(), bonusToHit, reachField.getText(), targetsField.getText(), new Dice(count, size, bonus), (String) damageTypeBox.getSelectedItem(), descArea.getText());
                }
                updateAction(action1, index);
                topLevel.dispose();
            } else {
                JOptionPane.showMessageDialog(topLevel, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttons.add(accept, "align 100%");
        wrapper.add(buttons, "width 100%");

        topLevel.add(wrapper);
        topLevel.setVisible(true);
    }

    public void addAction(Action action) {
        if (action != null) {
            Action[] temp = new Action[actions.length + 1];
            System.arraycopy(actions, 0, temp, 0, actions.length);
            temp[actions.length] = action;
            actions = temp;
            listModel.add(listModel.getSize(), action);
        }
    }

    private void updateAction(Action action, int index) {
        actions[index] = action;
        listModel.setElementAt(action, index);
    }

    private void removeAction(int index) {
        if (index > -1 && index < actions.length) {
            Action[] temp = new Action[actions.length - 1];
            for (int i = 0, k = 0; i < actions.length; i++) {
                if (i == index) {
                    continue;
                }
                temp[k++] = actions[i];
            }
            actions = temp;
            listModel.remove(index);
        }
    }

    private void moveUpAction(int index) {
        if (index > 0) {
            Action temp = actions[index - 1];
            actions[index - 1] = actions[index];
            actions[index] = temp;
            listModel.setElementAt(temp, index);
            listModel.setElementAt(actions[index - 1], index - 1);
            list.setSelectedIndex(index - 1);
        }
    }

    private void moveDownAction(int index) {
        if (index < actions.length - 1) {
            Action temp = actions[index + 1];
            actions[index + 1] = actions[index];
            actions[index] = temp;
            listModel.setElementAt(temp, index);
            listModel.setElementAt(actions[index + 1], index + 1);
            list.setSelectedIndex(index + 1);
        }
    }

    public void load(Action[] actions) {
        this.actions = actions == null ? new Action[0] : actions;
        listModel.removeAllElements();
        if (actions != null) {
            listModel.addAll(Arrays.asList(actions));
        }
    }

    public Action[] getActions() {
        return actions;
    }
}
