package controller;

import dao.impl.DepartamentoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import dao.interfaces.IUsuarioDAO;
import model.Departamento;
import model.Usuario;
import utils.UsuarioSession;
import view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AlquilerController {

    private IDepartamentoDAO departamentosDAO;
    private IUsuarioDAO iUsuarioDAO;
    Usuario usuarioActual = UsuarioSession.getInstance().getUsuarioActual();

    public AlquilerController() {
        this.departamentosDAO = new DepartamentoDAOImpl();
        this.iUsuarioDAO = new UsuarioDAOImpl();
    }

    // Método para manejar el proceso de alquiler
    public void alquilarDepartamento(Departamento departamento, JFrame currentFrame, JFrame parentFrame, int mesesAlquiler) {
        try {
            // Verificar si el departamento ya está alquilado
            if (departamento.getEstado().equalsIgnoreCase("Ocupado")) {
                mostrarMensaje(currentFrame, "El departamento se encuentra ocupado.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método sin realizar el alquiler
            }

            double precioAlquiler = departamento.getPrecioAlquiler();
            if (usuarioActual.getSaldo() >= precioAlquiler) {
                // Descontar saldo
                usuarioActual.setSaldo(usuarioActual.getSaldo() - precioAlquiler);
                iUsuarioDAO.actualizarSaldo(usuarioActual);

                // Cambiar estado del departamento
                departamento.setEstado("Ocupado");
                departamento.setIdPropietario(usuarioActual.getId());
                departamentosDAO.actualizar(departamento);

                // Obtener la fecha y hora actual
                LocalDateTime now = LocalDateTime.now(); // Fecha y hora actual
                LocalDateTime fechaVencimiento = now.plusSeconds(mesesAlquiler); // Sumar segundos para pruebas

                // Convertir a java.sql.Timestamp para almacenar en la base de datos
                Timestamp sqlFechaInicio = Timestamp.valueOf(now);
                Timestamp sqlFechaVencimiento = Timestamp.valueOf(fechaVencimiento);

                // Pasar las fechas al DAO
                departamentosDAO.actualizarFechaAlquiler(
                        departamento.getIdDepartamento(),
                        sqlFechaInicio,
                        sqlFechaVencimiento
                );



                // Actualizar UI
                if (parentFrame instanceof MainFrame) {
                    ((MainFrame) parentFrame).cargarTabla(); // Actualizar tabla en MainFrame
                }

                mostrarMensaje(currentFrame, "Departamento alquilado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                regresarAlMainFrame(currentFrame, parentFrame);
            } else {
                mostrarMensaje(currentFrame, "Saldo insuficiente para alquilar el departamento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            mostrarMensaje(currentFrame, "Error en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje(currentFrame, "Ha ocurrido un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Método para cerrar el frame actual y regresar al principal
    public void regresarAlMainFrame(JFrame currentFrame, JFrame parentFrame) {
        currentFrame.dispose();
        parentFrame.setVisible(true);
    }

    // Método para mostrar mensajes
    private void mostrarMensaje(JFrame frame, String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, titulo, tipoMensaje);
    }

}
