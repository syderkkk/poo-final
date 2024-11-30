package dao.interfaces;

import model.Departamento;

import java.sql.SQLException;
import java.util.List;

public interface IDepartamentoDAO {
    List<Departamento> obtenerTodos() throws SQLException;
    List<Departamento> buscarPorId(int id) throws SQLException;
    void guardar(Departamento departamento) throws SQLException;
    void actualizar(Departamento departamento) throws SQLException;
}
