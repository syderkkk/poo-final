package view.table;

import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ButtonEditor extends DefaultCellEditor {
    private JButton customButton;
    private Departamento departamento;
    private MainFrame mainFrame;
    private IDepartamentoDAO departamentoDAO;

    public ButtonEditor(JCheckBox checkBox, MainFrame mainFrame, IDepartamentoDAO departamentoDAO, JButton baseButton) {
        super(checkBox);
        this.mainFrame = mainFrame;
        this.departamentoDAO = departamentoDAO;

        // Crear un nuevo botón basado en el botón base
        customButton = new JButton(baseButton.getText());
        customButton.setIcon(baseButton.getIcon());
        customButton.setBackground(baseButton.getBackground());
        customButton.setForeground(baseButton.getForeground());
        customButton.setFont(baseButton.getFont());
        customButton.setFocusPainted(false);

        // Configurar acción del botón
        customButton.addActionListener(e -> {
            fireEditingStopped(); // Detener la edición antes de ejecutar la acción
            if (departamento != null) {
                mainFrame.abrirFormularioAlquilar(departamento);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cargar el departamento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
            int id = (int) table.getValueAt(row, 0);
            List<Departamento> departamentos = departamentoDAO.buscarPorId(id);
            if (!departamentos.isEmpty()) {
                departamento = departamentos.get(0);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el departamento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return customButton;
    }
}
