package dao.impl;

import config.ConexionBD;
import dao.interfaces.IDepartamentoDAO;
import model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl implements IDepartamentoDAO {

    @Override
    public List<Departamento> obtenerTodos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM Departamentos";

        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Departamento departamento = new Departamento(
                        resultSet.getInt("ID_Departamento"),
                        0, // idPropietario no está definido en la tabla proporcionada
                        resultSet.getString("descripcion"),
                        resultSet.getString("estado"),
                        resultSet.getDouble("precioAlquiler"),
                        resultSet.getString("ciudad"),
                        resultSet.getInt("numeroHabitaciones"),
                        resultSet.getInt("numeroBanos"),
                        resultSet.getInt("capacidad"),
                        resultSet.getString("direccion"),
                        // Obtener las fechas de la base de datos, manejando null si es necesario
                        resultSet.getTimestamp("fecha_alquiler"),
                        resultSet.getTimestamp("fecha_vencimiento")
                );
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }

    @Override
    public List<Departamento> buscarPorId(int id) throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM Departamentos WHERE ID_Departamento = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Departamento departamento = new Departamento(
                            resultSet.getInt("ID_Departamento"),
                            0,
                            resultSet.getString("descripcion"),
                            resultSet.getString("estado"),
                            resultSet.getDouble("precioAlquiler"),
                            resultSet.getString("ciudad"),
                            resultSet.getInt("numeroHabitaciones"),
                            resultSet.getInt("numeroBanos"),
                            resultSet.getInt("capacidad"),
                            resultSet.getString("direccion")
                    );
                    departamentos.add(departamento);
                }
            }
        }
        return departamentos;
    }

    @Override
    public void guardar(Departamento departamento) throws SQLException {
        String query = "INSERT INTO Departamentos (descripcion, estado, precioAlquiler, ciudad, numeroHabitaciones, numeroBanos, capacidad, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departamento.getDescripcion());
            statement.setString(2, departamento.getEstado());
            statement.setDouble(3, departamento.getPrecioAlquiler());
            statement.setString(4, departamento.getCiudad());
            statement.setInt(5, departamento.getNumeroHabitaciones());
            statement.setInt(6, departamento.getNumeroBanos());
            statement.setInt(7, departamento.getCapacidad());
            statement.setString(8, departamento.getDireccion());

            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Departamento departamento) throws SQLException {
        String query = "UPDATE Departamentos SET estado = ?, ID_Usuario = ? WHERE ID_Departamento = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departamento.getEstado());
            statement.setInt(2, departamento.getIdPropietario());
            statement.setInt(3, departamento.getIdDepartamento());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Departamento> obtenerPorUserId(int id) throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM Departamentos WHERE ID_Usuario = ?";

        try (Connection connection = ConexionBD.getConnection();
            PreparedStatement statement = connection.prepareStatement(query) ){

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    int idDepartamento = resultSet.getInt("ID_Departamento");
                    int idPropietario = resultSet.getInt("ID_Usuario");
                    String descripcion = resultSet.getString("descripcion");
                    String estado = resultSet.getString("estado");
                    double precioAlquiler = resultSet.getDouble("precioAlquiler");
                    String ciuddad = resultSet.getString("ciudad");
                    int numeroHabitaciones = resultSet.getInt("numeroHabitaciones");
                    int numeroBanos = resultSet.getInt("numeroBanos");
                    int capacidad = resultSet.getInt("capacidad");
                    String direccion = resultSet.getString("direccion");

                    Departamento departamento = new Departamento(idDepartamento,
                            idPropietario,
                            descripcion,
                            estado,
                            precioAlquiler, ciuddad, numeroHabitaciones, numeroBanos, capacidad, direccion);

                    departamentos.add(departamento);
                }
             }
        }
        return departamentos;
    }

    public void actualizarFechaAlquiler(int idDepartamento, Timestamp fechaAlquiler, Timestamp fechaVencimiento) throws SQLException {
        String query = "UPDATE Departamentos SET fecha_alquiler = ?, fecha_vencimiento = ? WHERE id_departamento = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Usar setTimestamp para valores de tipo java.sql.Timestamp
            stmt.setTimestamp(1, fechaAlquiler); // Fecha actual (con hora y segundos)
            stmt.setTimestamp(2, fechaVencimiento); // Fecha de vencimiento
            stmt.setInt(3, idDepartamento);

            stmt.executeUpdate();
        }
    }

    public void actualizarEstadoDepartamento(int idDepartamento) throws SQLException {
        String query = "UPDATE Departamentos " +
                "SET ID_Usuario = NULL, fecha_alquiler = NULL, fecha_vencimiento = NULL, estado = 'Disponible' " +
                "WHERE ID_Departamento = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDepartamento);
            statement.executeUpdate();
        }
    }
}