package Gui;

import MonsterMakerPackage.Trait;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

public class ImportTraits extends JPanel {
    private Trait[] storedTraits;
    private final JList<Trait> list;
    private final DefaultListModel<Trait> listModel;

    public ImportTraits(Trait[] traits, TraitsPanel traitsPanel) {
        LinkedList<Trait> linkedList = new LinkedList<>();
        for (Trait trait : traits) {
            boolean isIn = false;
            for (int j = 0; j < traitsPanel.getTraits().length; j++) {
                if (trait.equals(traitsPanel.getTraits()[j])) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                linkedList.add(trait);
            }
        }

        storedTraits = linkedList.toArray(new Trait[0]);

        setLayout(new MigLayout("fill"));
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Traits"));

        listModel = new DefaultListModel<>();
        listModel.addAll(Arrays.asList(storedTraits));
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
                    traitsPanel.addTrait(storedTraits[list.getSelectedIndex()]);
                    removeTrait(list.getSelectedIndex());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(list);

        panel.add(scrollPane, "width 100%, height 100%");
        add(panel, "width 100%, height 100%");
    }

    private void removeTrait(int index) {
        if (index > -1 && index < storedTraits.length) {
            Trait[] temp = new Trait[storedTraits.length - 1];
            for (int i = 0, k = 0; i < storedTraits.length; i++) {
                if (i == index) {
                    continue;
                }
                temp[k++] = storedTraits[i];
            }
            storedTraits = temp;
            listModel.remove(index);
        }
    }
}
