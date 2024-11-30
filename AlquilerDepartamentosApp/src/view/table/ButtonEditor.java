package view.table;

import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private Departamento departamento;
    private MainFrame mainFrame; // Referencia al MainFrame
    private IDepartamentoDAO departamentoDAO; // Referencia al DAO

    public ButtonEditor(JCheckBox checkBox, MainFrame mainFrame, IDepartamentoDAO departamentoDAO) {
        super(checkBox);
        this.mainFrame = mainFrame;
        this.departamentoDAO = departamentoDAO;
        button = new JButton("Alquilar");
        button.addActionListener(e -> {
            mainFrame.abrirFormularioAlquilar(departamento); // Llama al método de MainFrame
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
            int id = (int) table.getValueAt(row, 0);
            departamento = departamentoDAO.buscarPorId(id).get(0); // Obtiene el departamento según el ID
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el departamento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return button;
    }
}
