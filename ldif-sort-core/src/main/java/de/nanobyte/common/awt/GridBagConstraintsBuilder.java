package de.nanobyte.common.awt;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public final class GridBagConstraintsBuilder {

    private final GridBagConstraints gridBagConstraints;

    public GridBagConstraintsBuilder() {
        this.gridBagConstraints = new GridBagConstraints();
    }

    public GridBagConstraintsBuilder anchor(int value) {
        gridBagConstraints.anchor = value;
        return this;
    }

    public GridBagConstraintsBuilder fill(int value) {
        gridBagConstraints.fill = value;
        return this;
    }

    public GridBagConstraintsBuilder gridHeight(int value) {
        gridBagConstraints.gridheight = value;
        return this;
    }

    public GridBagConstraintsBuilder gridWidth(int value) {
        gridBagConstraints.gridwidth = value;
        return this;
    }

    public GridBagConstraintsBuilder gridX(int value) {
        gridBagConstraints.gridx = value;
        return this;
    }

    public GridBagConstraintsBuilder gridY(int value) {
        gridBagConstraints.gridy = value;
        return this;
    }

    public GridBagConstraintsBuilder insets(Insets insets) {
        gridBagConstraints.insets = insets;
        return this;
    }

    public GridBagConstraintsBuilder ipadX(int value) {
        gridBagConstraints.ipadx = value;
        return this;
    }

    public GridBagConstraintsBuilder ipadY(int value) {
        gridBagConstraints.ipady = value;
        return this;
    }

    public GridBagConstraintsBuilder weightX(double value) {
        gridBagConstraints.weightx = value;
        return this;
    }

    public GridBagConstraintsBuilder weightY(double value) {
        gridBagConstraints.weighty = value;
        return this;
    }

    public GridBagConstraints build() {
        return gridBagConstraints;
    }
}
