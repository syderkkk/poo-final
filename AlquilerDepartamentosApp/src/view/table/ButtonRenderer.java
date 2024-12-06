package view.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    private JButton baseButton;

    public ButtonRenderer(JButton baseButton) {
        this.baseButton = baseButton;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Crear un nuevo botón para cada celda, copiando el diseño del baseButton
        JButton button = new JButton(baseButton.getText());
        button.setIcon(baseButton.getIcon());
        button.setBackground(baseButton.getBackground());
        button.setForeground(baseButton.getForeground());
        button.setFont(baseButton.getFont());
        button.setFocusPainted(false);

        // Cambiar colores si la celda está seleccionada
        if (isSelected) {
            button.setBackground(table.getSelectionBackground());
            button.setForeground(table.getSelectionForeground());
        }
        return button;
    }
}
