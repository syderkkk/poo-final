package view;

import controller.DepartamentoController;
import dao.impl.DepartamentoDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import view.table.ButtonEditor;
import view.table.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {

    private JTable departamentosTable;
    private DefaultTableModel tableModel;
    private IDepartamentoDAO departamentosDAO;
    private JTextField searchField;
    private DepartamentoController departamentoController;

    public MainFrame() {
        setTitle("Departamentos disponibles");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        departamentosDAO = new DepartamentoDAOImpl();
        departamentoController = new DepartamentoController(departamentosDAO); // Inicializar controlador

        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        JButton addButton = new JButton("Agregar Departamento");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);
        topPanel.add(addButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Configurar tabla y modelo
        String[] columnNames = {"ID", "Descripción", "Estado", "Precio", "Ciudad", "Habitaciones", "Baños", "Capacidad", "Dirección", "Acción"};
        tableModel = new DefaultTableModel(columnNames, 0);
        departamentosTable = new JTable(tableModel);
        departamentosTable.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        departamentosTable.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), this, departamentosDAO));

        add(new JScrollPane(departamentosTable), BorderLayout.CENTER);

        cargarTabla();

        searchButton.addActionListener(e -> buscarDepartamento());
        addButton.addActionListener(e -> abrirFormularioAgregar());
    }

    public void cargarTabla() {
        try {
            List<Departamento> departamentos = departamentosDAO.obtenerTodos();
            actualizarTabla(departamentos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTabla(List<Departamento> departamentos) {
        tableModel.setRowCount(0); // Limpiar el modelo existente

        for (Departamento d : departamentos) {
            tableModel.addRow(new Object[]{
                    d.getIdDepartamento(), d.getDescripcion(), d.getEstado(), d.getPrecioAlquiler(),
                    d.getCiudad(), d.getNumeroHabitaciones(), d.getNumeroBanos(), d.getCapacidad(), d.getDireccion(), "Alquilar"
            });
        }

        // Refrescar la tabla
        tableModel.fireTableDataChanged();
    }

    private void buscarDepartamento() {
        try {
            String textoBusqueda = searchField.getText().trim(); // Obtiene el texto del campo de búsqueda
            if (textoBusqueda.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(textoBusqueda); // Convierte el texto a un número
            List<Departamento> resultados = departamentosDAO.buscarPorId(id); // Busca en el DAO

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún departamento con el ID proporcionado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Como el ID es único, tomamos el primer elemento de la lista
                Departamento departamento = resultados.get(0);
                abrirFormularioAlquilar(departamento); // Abre el formulario de alquiler
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el departamento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirFormularioAgregar() {
        AgregarDepartamentoFrame agregarFrame = new AgregarDepartamentoFrame(this, departamentoController);
        agregarFrame.setVisible(true);
    }

    public void abrirFormularioAlquilar(Departamento departamento) {
        new AlquilarDepartamentoFrame(this, departamento).setVisible(true);
    }
}