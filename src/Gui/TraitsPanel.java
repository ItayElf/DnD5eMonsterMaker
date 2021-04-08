package Gui;

import MonsterMakerPackage.Trait;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TraitsPanel extends JPanel {
    private Trait[] traits = new Trait[0];
    private final JList<Trait> list;
    private final DefaultListModel<Trait> listModel;

    public TraitsPanel() {
        super();
        setLayout(new MigLayout("fill"));
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Traits"));

        listModel = new DefaultListModel<>();
        listModel.addAll(Arrays.asList(traits));
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
                    editTrait(list.getSelectedIndex());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel buttons = new JPanel(new MigLayout("filly"));

        JButton moveUp = new JButton("Move Up");
        moveUp.setFont(Defaults.getComboBoxFont());
        moveUp.addActionListener(e -> moveUpTrait(list.getSelectedIndex()));

        JButton moveDown = new JButton("Move Down");
        moveDown.setFont(Defaults.getComboBoxFont());
        moveDown.addActionListener(e -> moveDownTrait(list.getSelectedIndex()));

        JButton add = new JButton("Add");
        add.setFont(Defaults.getComboBoxFont());
        add.addActionListener(e -> addTrait());

        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> editTrait(list.getSelectedIndex()));
        edit.setFont(Defaults.getComboBoxFont());

        JButton remove = new JButton("Remove");
        remove.setFont(Defaults.getComboBoxFont());
        remove.addActionListener(e -> removeTrait(list.getSelectedIndex()));

        buttons.add(moveUp, "wrap, width 100%");
        buttons.add(moveDown, "wrap, width 100%");
        buttons.add(add, "wrap, width 100%");
        buttons.add(edit, "wrap, width 100%");
        buttons.add(remove, "wrap, width 100%");

        panel.add(scrollPane, "width 70%, height 100%");
        panel.add(buttons, "width 30%, height 100%");

        add(panel, "width 100%, height 100%");
    }

    private void addTrait() {
        JFrame topLevel = new JFrame("New Trait");
        topLevel.setSize(Defaults.getScreenWidth() / 2, Defaults.getScreenHeight());
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel namePanel = new JPanel(new MigLayout("fillx"));
        namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        JTextField nameField = new JTextField();
        nameField.setFont(Defaults.getTextFieldFont());
        namePanel.add(nameField, "width 100%");
        wrapper.add(namePanel, "wrap, width 100%");

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
                Trait trait = new Trait(nameField.getText(), descArea.getText());
                addTrait(trait);
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

    private void editTrait(int index) {
        Trait trait = traits[index];
        JFrame topLevel = new JFrame("Edit Trait");
        topLevel.setSize(Defaults.getScreenWidth() / 2, Defaults.getScreenHeight());
        JPanel wrapper = new JPanel(new MigLayout("fill"));

        JPanel namePanel = new JPanel(new MigLayout("fillx"));
        namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        JTextField nameField = new JTextField();
        nameField.setText(trait.getName());
        nameField.setFont(Defaults.getTextFieldFont());
        namePanel.add(nameField, "width 100%");
        wrapper.add(namePanel, "wrap, width 100%");

        JPanel description = new JPanel(new MigLayout("fillx"));
        description.setBorder(BorderFactory.createTitledBorder("Description"));
        JTextArea descArea = new JTextArea(9, 25);
        descArea.setText(trait.getDescription());
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
                Trait trait1 = new Trait(nameField.getText(), descArea.getText());
                updateTrait(trait1, index);
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

    public void addTrait(Trait trait) {
        if (trait != null) {
            Trait[] temp = new Trait[traits.length + 1];
            System.arraycopy(traits, 0, temp, 0, traits.length);
            temp[traits.length] = trait;
            traits = temp;
            listModel.add(listModel.getSize(), trait);
        }
    }

    private void updateTrait(Trait trait, int index) {
        traits[index] = trait;
        listModel.setElementAt(trait, index);
    }

    private void removeTrait(int index) {
        if (index > -1 && index < traits.length) {
            Trait[] temp = new Trait[traits.length - 1];
            for (int i = 0, k = 0; i < traits.length; i++) {
                if (i == index) {
                    continue;
                }
                temp[k++] = traits[i];
            }
            traits = temp;
            listModel.remove(index);
        }
    }

    private void moveUpTrait(int index) {
        if (index > 0) {
            Trait temp = traits[index - 1];
            traits[index - 1] = traits[index];
            traits[index] = temp;
            listModel.setElementAt(temp, index);
            listModel.setElementAt(traits[index - 1], index - 1);
            list.setSelectedIndex(index - 1);
        }
    }

    private void moveDownTrait(int index) {
        if (index < traits.length - 1) {
            Trait temp = traits[index + 1];
            traits[index + 1] = traits[index];
            traits[index] = temp;
            listModel.setElementAt(temp, index);
            listModel.setElementAt(traits[index + 1], index + 1);
            list.setSelectedIndex(index + 1);
        }
    }

    public void load(Trait[] traits) {
        this.traits = traits == null ? new Trait[0] : traits;
        listModel.removeAllElements();
        if (traits != null) {
            listModel.addAll(Arrays.asList(traits));
        }
    }

    public Trait[] getTraits() {
        return traits;
    }

    public void setTraits(Trait[] traits) {
        this.traits = traits;
    }

    public JList<Trait> getList() {
        return list;
    }

    public DefaultListModel<Trait> getListModel() {
        return listModel;
    }
}
