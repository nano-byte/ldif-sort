package de.nanobyte.ldap.ldif.sort;

import de.nanobyte.common.awt.GridBagConstraintsBuilder;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class SwingLdifSort extends JFrame {

    private final static JList<Path> ldifFiles = new JList<>();

    static {
        ldifFiles.setFixedCellWidth(160);
    }

    public SwingLdifSort(final String[] arguments) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ldif Sort");
        this.setLayout(new GridBagLayout());
        this.add(new JLabel("Ldif files"), new GridBagConstraintsBuilder().anchor(NORTHWEST).insets(new Insets(5, 5, 5, 0)).build());
        this.add(ldifFiles, new GridBagConstraintsBuilder().gridX(0).anchor(NORTHWEST).insets(new Insets(0, 5, 5, 5)).fill(BOTH).weightY(1).weightX(1).build());
        this.add(new JButton("Save"), new GridBagConstraintsBuilder().gridX(0).anchor(SOUTHEAST).insets(new Insets(0, 5, 5, 5)).build());
        this.pack();
    }
}
