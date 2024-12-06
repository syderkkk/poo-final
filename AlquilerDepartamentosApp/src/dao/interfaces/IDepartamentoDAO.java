package dao.interfaces;

import model.Departamento;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface IDepartamentoDAO {
    // CREATE - Crear un nuevo departamento
    void guardar(Departamento departamento) throws SQLException;

    // READ - Leer todos los departamentos
    List<Departamento> obtenerTodos() throws SQLException;
    List<Departamento> buscarPorId(int id) throws SQLException;
    List<Departamento> obtenerPorUserId(int id) throws  SQLException;

    // UPDATE - Actualizar un departamento existente
    void actualizar(Departamento departamento) throws SQLException;
    void actualizarFechaAlquiler(int idDepartamento, Timestamp fechaAlquiler, Timestamp fechaVencimiento) throws SQLException;
    void actualizarEstadoDepartamento(int iDepartamento) throws SQLException;
}
