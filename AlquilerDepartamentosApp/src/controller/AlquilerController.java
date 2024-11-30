package controller;

import dao.impl.DepartamentoDAOImpl;
import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import view.AlquilarDepartamentoFrame;
import view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

public class AlquilerController {

    // Método para alquilar un departamento
    public void alquilarDepartamento(AlquilarDepartamentoFrame frame, Departamento departamento, JFrame parent) {
        try {

            departamento.setEstado("Alquilado"); // Cambiar el estado del departamento a "Alquilado"
            IDepartamentoDAO departamentosDAO = new DepartamentoDAOImpl(); // Crear el objeto DAO para la actualización

            departamentosDAO.actualizar(departamento); // Actualizar el estado del departamento en la base de datos

            ((MainFrame) parent).cargarTabla(); //Actualizacion de los datos en tabla de MainFrame

            regresar(frame, parent);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(frame, "Departamento alquilado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            // En caso de error, mostrar mensaje de error
            JOptionPane.showMessageDialog(frame, "Error al alquilar el departamento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para regresar al MainFrame sin hacer cambios
    public void regresar(AlquilarDepartamentoFrame frame, JFrame parent) {
        frame.dispose();  // Cerrar el formulario de alquiler
        parent.setVisible(true);  // Mostrar el MainFrame nuevamente
    }
}
