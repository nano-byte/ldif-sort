package de.nanobyte.ldap.ldif.sort.swing;

import de.nanobyte.common.awt.GridBagConstraintsBuilder;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import javax.swing.*;

public class SwingLdifSort extends JFrame {

    private final static JList<Path> ldifFiles = new JList<>();

    static {
        ldifFiles.setFixedCellWidth(160);
    }

    public SwingLdifSort(final String[] arguments) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ldif Sort");
        this.setLayout(new GridBagLayout());
        this.add(new JLabel("Ldif files"), new GridBagConstraintsBuilder().anchor(WEST).insets(new Insets(5, 5, 5, 0)).build());
        this.add(ldifFiles, new GridBagConstraintsBuilder().gridX(0).anchor(WEST).insets(new Insets(0, 5, 5, 5)).fill(BOTH).weightY(1).weightX(1).gridWidth(2).build());
        this.add(new JCheckBox("reverse sort"), new GridBagConstraintsBuilder().gridX(0).anchor(WEST).insets(new Insets(0, 5, 5, 5)).build());
        this.add(new JTextField(), new GridBagConstraintsBuilder().gridX(0).gridY(3).anchor(EAST).fill(HORIZONTAL).insets(new Insets(0, 5, 5, 5)).build());
        this.add(new JButton("Output file"), new GridBagConstraintsBuilder().gridX(1).gridY(3).anchor(EAST).insets(new Insets(0, 5, 5, 5)).build());
        this.add(new JButton("Cancel"), new GridBagConstraintsBuilder().gridX(0).gridY(4).anchor(EAST).weightX(1).insets(new Insets(15, 5, 5, 5)).build());
        this.add(new JButton("Sort"), new GridBagConstraintsBuilder().gridX(1).gridY(4).anchor(EAST).insets(new Insets(15, 5, 5, 5)).build());
        this.pack();
    }
}
