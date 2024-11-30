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
        String query = "SELECT * FROM Departamento";

        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Departamento departamento = new Departamento(
                        resultSet.getInt("idDepartamento"),
                        0, // idPropietario no está definido en la tabla proporcionada
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
        return departamentos;
    }

    @Override
    public List<Departamento> buscarPorId(int id) throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM Departamento WHERE idDepartamento = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Departamento departamento = new Departamento(
                            resultSet.getInt("idDepartamento"),
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
        String query = "INSERT INTO Departamento (descripcion, estado, precioAlquiler, ciudad, numeroHabitaciones, numeroBanos, capacidad, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String query = "UPDATE Departamento SET estado = ? WHERE idDepartamento = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departamento.getEstado());
            statement.setInt(2, departamento.getIdDepartamento());

            statement.executeUpdate();
        }
    }
}