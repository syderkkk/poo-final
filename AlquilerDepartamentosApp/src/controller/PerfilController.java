package controller;

import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import utils.UsuarioSession;
import view.PerfilFrame;

import javax.swing.*;
import java.sql.SQLException;

public class PerfilController {
    private final PerfilFrame perfilFrame;
    private final IUsuarioDAO usuarioDAO;

    public PerfilController(PerfilFrame perfilFrame, IUsuarioDAO usuarioDAO) {
        this.perfilFrame = perfilFrame;
        this.usuarioDAO = usuarioDAO;

        initEventHandlers();
    }

    private void initEventHandlers() {
        // Manejar el botón para cargar saldo
        perfilFrame.getCargarSaldoButton().addActionListener(e -> mostrarVentanaCargarSaldo());
    }

    private void mostrarVentanaCargarSaldo() {
        // Crear un cuadro de diálogo para solicitar el monto a cargar
        String montoStr = JOptionPane.showInputDialog(perfilFrame,
                "Ingrese el monto a cargar:",
                "Cargar Saldo",
                JOptionPane.PLAIN_MESSAGE);

        // Validar la entrada del usuario
        if (montoStr != null && !montoStr.trim().isEmpty()) {
            try {
                double monto = Double.parseDouble(montoStr);

                if (monto <= 0) {
                    JOptionPane.showMessageDialog(perfilFrame,
                            "El monto debe ser mayor a 0.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Actualizar saldo utilizando el DAO
                cargarSaldo(monto);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(perfilFrame,
                        "Por favor, ingrese un monto válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarSaldo(double monto) {
        Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();
        try {
            // Actualizar saldo en la base de datos
            usuarioDAO.cargarSaldo(usuarioActual.getId(), monto);

            // Actualizar saldo en la sesión y en el campo de texto
            usuarioActual.setSaldo(usuarioActual.getSaldo() + monto);
            perfilFrame.getSaldoField().setText(String.valueOf(usuarioActual.getSaldo()));

            JOptionPane.showMessageDialog(perfilFrame,
                    "Saldo cargado exitosamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(perfilFrame,
                    "Error al cargar saldo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
