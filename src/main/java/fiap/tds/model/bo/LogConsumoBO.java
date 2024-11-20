package fiap.tds.model.bo;

import fiap.tds.model.dao.LogConsumoDAOImpl;
import fiap.tds.model.vo.LogConsumo;

import java.sql.SQLException;
import java.util.List;

public class LogConsumoBO {

    private final LogConsumoDAOImpl logConsumoDAO;

    public LogConsumoBO() throws SQLException {
        this.logConsumoDAO = new LogConsumoDAOImpl();
    }

    public void inserirLogConsumo(LogConsumo logConsumo) throws SQLException {
        validarLogConsumo(logConsumo);
        logConsumoDAO.inserirLogConsumo(logConsumo);
    }

    public void excluirLogConsumo(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }
        logConsumoDAO.excluirLogConsumo(id);
    }

    public void atualizarLogConsumo(LogConsumo logConsumo) throws SQLException {
        if (logConsumo.getId_log() <= 0) {
            throw new IllegalArgumentException("O ID do log deve ser maior que zero.");
        }
        validarLogConsumo(logConsumo);
        logConsumoDAO.atualizarLogConsumo(logConsumo);
    }

    public List<LogConsumo> listarLogConsumo() throws SQLException {
        List<LogConsumo> logs = logConsumoDAO.listarLogConsumo();
        if (logs.isEmpty()) {
            throw new SQLException("Nenhum log de consumo encontrado.");
        }
        return logs;
    }

    public LogConsumo buscarLogConsumoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }
        LogConsumo log = logConsumoDAO.buscarLogConsumoId(id);
        if (log == null) {
            throw new SQLException("Log de consumo não encontrado.");
        }
        return log;
    }

    private void validarLogConsumo(LogConsumo logConsumo) {
        if (logConsumo == null) {
            throw new IllegalArgumentException("O log de consumo não pode ser nulo.");
        }
        if (logConsumo.getConsumo_real() <= 0) {
            throw new IllegalArgumentException("O consumo real deve ser maior que zero.");
        }
        if (logConsumo.getRelatorio() == null || logConsumo.getRelatorio().getId_relatorio() <= 0) {
            throw new IllegalArgumentException("O relatório associado ao log de consumo é inválido.");
        }
        if (logConsumo.getAparelhos() == null || logConsumo.getAparelhos().getId_aparelho() <= 0) {
            throw new IllegalArgumentException("O aparelho associado ao log de consumo é inválido.");
        }
    }

}
