package fiap.tds.model.dao;

import fiap.tds.model.vo.AlertasConsumo;

import java.sql.SQLException;
import java.util.List;

public interface AlertasConsumoDAO {

    void inserirAlerta (AlertasConsumo alertasConsumo) throws SQLException;
    void excluirAlerta(int id) throws SQLException;
    void atualizarAlerta(AlertasConsumo alertasConsumo) throws  SQLException;
    public List<AlertasConsumo> listarAlertas() throws SQLException;
    public AlertasConsumo buscarAlertaPorId(int id) throws SQLException;
    public AlertasConsumo buscarAlertasConsumoPorIdCliente(int idCliente) throws SQLException;
}
