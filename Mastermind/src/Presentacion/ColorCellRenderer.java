package Presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase que extiende DefaultTableCellRenderer, usada para poder pintar bien las tablas en las ventanas de partidas
 */
public class ColorCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;
    private Color[][] selectedColors;

    public ColorCellRenderer(Color[][] selectedColors) {
        this.selectedColors = selectedColors;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setBackground(selectedColors[row][column]);
        return cellComponent;
    }
}
