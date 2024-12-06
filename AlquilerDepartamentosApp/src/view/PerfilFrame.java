package view;

import controller.PerfilController;
import dao.impl.DepartamentoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import dao.interfaces.IUsuarioDAO;
import model.Departamento;
import model.Usuario;
import utils.UsuarioSession;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PerfilFrame extends JFrame {

    private JTextField nombreField, correoField, saldoField;
    private JButton cerrarBtn, cargarSaldoButton;
    private JTextArea departamentos;
    private JPanel panel;

    private IDepartamentoDAO departamentosDAO;


    public PerfilFrame() {
        Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();
        initComponents();

        nombreField.setText(usuarioActual.getNombres() + " " + usuarioActual.getApellidos());
        correoField.setText(usuarioActual.getCorreo());
        saldoField.setText(String.valueOf(usuarioActual.getSaldo()));


        // Instanciar DAO y controlador
        IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        PerfilController perfilController = new PerfilController(this, usuarioDAO);


        // Cargar departamentos
        departamentosDAO = new DepartamentoDAOImpl();
        try {
            List<Departamento> resultado = departamentosDAO.obtenerPorUserId(usuarioActual.getId());
            StringBuilder sb = new StringBuilder();

            for (Departamento dept : resultado) {
                String direccion = dept.getDireccion().replace("\n", "").replace("\r", "").trim();
                String precio = String.format("S/. %.2f", dept.getPrecioAlquiler()).replace("\n", "").replace("\r", "").trim();

                sb.append(dept.getIdDepartamento())  // ID del departamento
                        .append(" | ")          // Separador
                        .append(direccion)      // Dirección del departamento
                        .append(" | ")          // Separador
                        .append(precio)         // Precio de alquiler
                        .append("\n");
            }

            departamentos.setText(sb.toString());
        } catch (SQLException e) {
            System.out.println(e);
        }

        cerrarBtn.addActionListener(e -> cerrarSesion());

    }


    private void initComponents() {
        setTitle("Perfil de usuario");
        setContentPane(panel);
        setSize(500, 516);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void cerrarSesion() {
        UsuarioSession.getInstance().cerrarSesion();

        for (Frame frame : Frame.getFrames()) {
            frame.dispose();
        }

        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    public JButton getCargarSaldoButton() {
        return cargarSaldoButton;
    }

    public JTextField getSaldoField() {
        return saldoField;
    }

}
