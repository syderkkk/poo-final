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
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {

    public JPanel Panel;
    private JButton perfilButton, addButton, searchButton, alquilarButton1, alquilarButton2, alquilarButton;
    private JTextField searchField;

    private JTable departamentosTable;
    private DefaultTableModel tableModel;
    private IDepartamentoDAO departamentosDAO;
    private DepartamentoController departamentoController;
    private ScheduledExecutorService scheduler;

    private static final String[] COLUMN_NAMES = {"ID", "Descripción", "Estado", "Precio", "Ciudad", "Habitaciones", "Baños", "Capacidad", "Dirección", "Acción"};

    public MainFrame() {

        initComponents();
        setupTable();
        setupTopPanel();

        departamentosDAO = new DepartamentoDAOImpl();
        departamentoController = new DepartamentoController(departamentosDAO);

        departamentosTable.getColumn("Acción").setCellRenderer(new ButtonRenderer(alquilarButton1));
        departamentosTable.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), this, departamentosDAO, alquilarButton1));

        departamentosTable.setShowGrid(true);
        departamentosTable.setGridColor(Color.LIGHT_GRAY);

        iniciarScheduler();
        setupListeners();
        cargarTabla();

    }

    private void initComponents() {
        setTitle("Ventana principal");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna "Acción" será editable
                return column == 9;
            }
        };
        departamentosTable = new JTable(tableModel);

        // Ajustar la altura de las filas
        departamentosTable.setRowHeight(30); // Cambia 40 por el valor deseado
        departamentosTable.setIntercellSpacing(new Dimension(0, 10)); // 10 píxeles de separación vertical
        // Centrar texto en las columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < departamentosTable.getColumnCount(); i++) {
            departamentosTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        int[] columnWidths = {50, 150, 100, 80, 80, 100, 60, 80, 200, 100};
        for (int i = 0; i < departamentosTable.getColumnCount(); i++) {
            TableColumn column = departamentosTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        add(new JScrollPane(departamentosTable), BorderLayout.CENTER);
    }

    private void setupTopPanel() {
        // Crear los paneles y agregar componentes
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // Ajusta el espacio entre botones
        eastPanel.add(searchButton);
        eastPanel.add(perfilButton);

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(eastPanel, BorderLayout.EAST); // Añadir el panel al este
        topPanel.add(addButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupListeners() {
        Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();

        searchButton.addActionListener(e -> buscarDepartamento(usuarioActual));
        addButton.addActionListener(e -> abrirFormularioAgregar());
        perfilButton.addActionListener(e -> abrirPerfilFrame());
    }


    private void iniciarScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                verificarYActualizarFechas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void verificarYActualizarFechas() throws SQLException {
        List<Departamento> departamentos = departamentosDAO.obtenerTodos();
        Timestamp ahora = new Timestamp(System.currentTimeMillis());  // Obtén la fecha actual solo una vez

        for (Departamento departamento : departamentos) {
            // Verifica si la fecha de vencimiento no es null antes de hacer la comparación
            if (departamento.getFechaVencimiento() != null && departamento.getFechaVencimiento().before(ahora)) {
                // Actualizar el estado del departamento si está vencido
                departamentosDAO.actualizarEstadoDepartamento(departamento.getIdDepartamento());
            }
        }
        // Llamar al método cargarTabla() para actualizar la interfaz gráfica
        SwingUtilities.invokeLater(this::cargarTabla);
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
        tableModel.setRowCount(0);
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
        new AgregarDepartamentoFrame(this, departamentoController).setVisible(true);;
    }

    public void abrirFormularioAlquilar(Departamento departamento) {
        new AlquilerDepartFrame(this, departamento).setVisible(true);
    }

    public void abrirPerfilFrame() {
        new PerfilFrame().setVisible(true);
    }
}
