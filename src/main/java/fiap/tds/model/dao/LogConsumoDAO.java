package fiap.tds.model.dao;

import fiap.tds.model.vo.LogConsumo;

import java.sql.SQLException;
import java.util.List;

public interface LogConsumoDAO {
    void inserirLogConsumo (LogConsumo logConsumo) throws SQLException;
    void excluirLogConsumo(int id) throws SQLException;
    void atualizarLogConsumo(LogConsumo logConsumo) throws  SQLException;
    public List<LogConsumo> listarLogConsumo() throws SQLException;
    public LogConsumo buscarLogConsumoId(int id) throws SQLException;
    public LogConsumo buscarLogConsumoPorIdCliente(int idCliente) throws SQLException;
}
