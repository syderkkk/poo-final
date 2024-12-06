package view;

import controller.DepartamentoController;
import dao.impl.DepartamentoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import dao.interfaces.IUsuarioDAO;
import model.Departamento;
import model.Usuario;
import utils.UsuarioSession;
import view.table.ButtonEditor;
import view.table.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {

    public JPanel Panel;
    private JButton perfilButton, addButton, searchButton, alquilarButton1, alquilarButton2, alquilarButton;
    private JTextField searchField;

    private JTable departamentosTable;
    private DefaultTableModel tableModel;
    private IDepartamentoDAO departamentosDAO;
    private DepartamentoController departamentoController;


    public MainFrame() {
        Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();
        initComponents();


        // Crear los paneles y agregar componentes
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // Ajusta el espacio entre botones
        eastPanel.add(searchButton);
        eastPanel.add(perfilButton);

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(eastPanel, BorderLayout.EAST); // Añadir el panel al este
        topPanel.add(addButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        departamentosDAO = new DepartamentoDAOImpl();
        departamentoController = new DepartamentoController(departamentosDAO); // Inicializar controlador

        String[] columnNames = {"ID", "Descripción", "Estado", "Precio", "Ciudad", "Habitaciones", "Baños", "Capacidad", "Dirección", "Acción"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna "Acción" será editable
                return column == 9;
            }
        };
        departamentosTable = new JTable(tableModel);

        // Ajustar la altura de las filas
        departamentosTable.setRowHeight(30); // Cambia 40 por el valor deseado
        // Centrar texto en las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < departamentosTable.getColumnCount(); i++) {
            departamentosTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Ajustar el ancho de las columnas
        int[] columnWidths = {50, 150, 100, 80, 80, 100, 60, 80, 200, 100};
        for (int i = 0; i < departamentosTable.getColumnCount(); i++) {
            TableColumn column = departamentosTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        // Ajustar el espaciado entre celdas: (ancho, alto)
        departamentosTable.setIntercellSpacing(new Dimension(0, 10)); // 10 píxeles de separación vertical

        // Opcional: configurar color de líneas de la grilla para mayor claridad
        departamentosTable.setShowGrid(true);
        departamentosTable.setGridColor(Color.LIGHT_GRAY);

        // Configurar renderizador y editor con el botón personalizado
        departamentosTable.getColumn("Acción").setCellRenderer(new ButtonRenderer(alquilarButton1));
        departamentosTable.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), this, departamentosDAO, alquilarButton1));

        add(new JScrollPane(departamentosTable), BorderLayout.CENTER);

        cargarTabla();

        searchButton.addActionListener(e -> buscarDepartamento(usuarioActual));
        addButton.addActionListener(e -> abrirFormularioAgregar());
        perfilButton.addActionListener(e -> abrirPerfilFrame());
    }

    private void initComponents() {
        setTitle("Ventana principal");
        // Configurar el layout del JFrame
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer un tamaño preferido para el JFrame
        setPreferredSize(new Dimension(900, 600)); // Establecer tamaño preferido
        pack(); // Ajusta el JFrame al tamaño preferido
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana al inicio

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);  // Esto asegura que la ventana se centre al abrirse
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
                    d.getIdDepartamento(), d.getDescripcion(), d.getEstado(), "S/. " + d.getPrecioAlquiler(),
                    d.getCiudad(), d.getNumeroHabitaciones(), d.getNumeroBanos(), d.getCapacidad(), d.getDireccion(), "Alquilar"
            });
        }

        // Refrescar la tabla
        tableModel.fireTableDataChanged();
    }

    private void buscarDepartamento(Usuario usuarioActual) {
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
        //AddDepartFrame addDepartFrame = new AddDepartFrame(this, departamentoController);
        agregarFrame.setVisible(true);
        //aaddDepartFrame.setVisible(true);
    }

    public void abrirFormularioAlquilar(Departamento departamento) {
        new AlquilerDepartFrame(this, departamento).setVisible(true);
    }

    public void abrirPerfilFrame() {
        PerfilFrame perfilFrame = new PerfilFrame();
        perfilFrame.setVisible(true);
    }
}