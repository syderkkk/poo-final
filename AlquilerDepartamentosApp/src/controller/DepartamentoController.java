package controller;

import dao.interfaces.IDepartamentoDAO;
import model.Departamento;
import utils.Validaciones;
import view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

public class DepartamentoController {

    private final IDepartamentoDAO departamentoDAO;

    public DepartamentoController(IDepartamentoDAO departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    public void agregarDepartamento(JFrame frame, String descripcion, String estado, String precioStr,
                                    String ciudad, String habitacionesStr, String banosStr, String capacidadStr, String direccion,
                                    JFrame parent) throws SQLException {

        // Validar campos ( MEJORAR )
        if (!Validaciones.noVacio(descripcion,  "Descripción no puede estar vacía") ||
                !Validaciones.noVacio(ciudad, "Ciudad no puede estar vacía") ||
                !Validaciones.noVacio(direccion, "Dirección no puede estar vacía") ||
                !Validaciones.noVacio(precioStr, "Precio no puede estar vacío") ||
                !Validaciones.noVacio(habitacionesStr, "Número de habitaciones no puede estar vacío") ||
                !Validaciones.noVacio(banosStr, "Número de baños no puede estar vacío") ||
                !Validaciones.noVacio(capacidadStr, "Capacidad no puede estar vacía")) {
            return;
        }

        if (!Validaciones.esNumero(precioStr, "El precio debe ser un número válido") ||
                !Validaciones.esNumeroEntero(habitacionesStr, "El número de habitaciones debe ser un valor numérico") ||
                !Validaciones.esNumeroEntero(banosStr, "El número de baños debe ser un valor numérico") ||
                !Validaciones.esNumeroEntero(capacidadStr, "La capacidad debe ser un valor numérico")) {
            return;
        }

        try {
            // Crear objeto departamento
            double precio = Double.parseDouble(precioStr);
            int habitaciones = Integer.parseInt(habitacionesStr);
            int banos = Integer.parseInt(banosStr);
            int capacidad = Integer.parseInt(capacidadStr);

            Departamento nuevoDepartamento = new Departamento();
            nuevoDepartamento.setDescripcion(descripcion);
            nuevoDepartamento.setEstado(estado);
            nuevoDepartamento.setPrecioAlquiler(precio);
            nuevoDepartamento.setCiudad(ciudad);
            nuevoDepartamento.setNumeroHabitaciones(habitaciones);
            nuevoDepartamento.setNumeroBanos(banos);
            nuevoDepartamento.setCapacidad(capacidad);
            nuevoDepartamento.setDireccion(direccion);

            // Guardar en la base de datos
            departamentoDAO.guardar(nuevoDepartamento);

            ((MainFrame) parent).cargarTabla();
            frame.dispose();
            parent.setVisible(true);

            JOptionPane.showMessageDialog(frame, "Departamento agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el departamento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}