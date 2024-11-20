package fiap.tds.model.bo;

import fiap.tds.model.dao.AlertasConsumoDAO;
import fiap.tds.model.dao.AlertasConsumoDAOImpl;
import fiap.tds.model.vo.AlertasConsumo;

import java.sql.SQLException;
import java.util.List;

public class AlertasConsumoBO {

    private AlertasConsumoDAO alertasConsumoDAO;

    public AlertasConsumoBO() throws SQLException {
        this.alertasConsumoDAO = new AlertasConsumoDAOImpl();
    }

    public void inserirAlerta(AlertasConsumo alertasConsumo) throws SQLException {
        if (alertasConsumo == null) {
            throw new IllegalArgumentException("O alerta não pode ser nulo.");
        }
        if (alertasConsumo.getCliente() == null || alertasConsumo.getCliente().getId_cliente() <= 0) {
            throw new IllegalArgumentException("O cliente associado ao alerta é inválido.");
        }
        if (alertasConsumo.getMensagem() == null || alertasConsumo.getMensagem().trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem do alerta não pode ser vazia.");
        }

        alertasConsumoDAO.inserirAlerta(alertasConsumo);
    }

    public void excluirAlerta(int id) throws SQLException {
        AlertasConsumo alerta = alertasConsumoDAO.buscarAlertaPorId(id);
        if (alerta == null) {
            throw new IllegalArgumentException("O alerta com ID " + id + " não existe.");
        }
        alertasConsumoDAO.excluirAlerta(id);
    }

    public void atualizarAlerta(AlertasConsumo alertasConsumo) throws SQLException {
        if (alertasConsumo == null || alertasConsumo.getId_alerta() <= 0) {
            throw new IllegalArgumentException("O alerta informado é inválido.");
        }
        if (alertasConsumo.getCliente() == null || alertasConsumo.getCliente().getId_cliente() <= 0) {
            throw new IllegalArgumentException("O cliente associado ao alerta é inválido.");
        }
        if (alertasConsumo.getMensagem() == null || alertasConsumo.getMensagem().trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem do alerta não pode ser vazia.");
        }

        alertasConsumoDAO.atualizarAlerta(alertasConsumo);
    }

    public List<AlertasConsumo> listarAlertas() throws SQLException {
        List<AlertasConsumo> alertas = alertasConsumoDAO.listarAlertas();
        if (alertas.isEmpty()) {
            throw new IllegalArgumentException("Nenhum alerta foi encontrado.");
        }
        return alertas;
    }


    public AlertasConsumo buscarAlertaPorId(int id) throws SQLException {
        AlertasConsumo alerta = alertasConsumoDAO.buscarAlertaPorId(id);
        if (alerta == null) {
            throw new IllegalArgumentException("O alerta com ID " + id + " não foi encontrado.");
        }
        return alerta;
    }


    public AlertasConsumo buscarAlertasPorCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("O ID do cliente é inválido.");
        }
        AlertasConsumo alerta = alertasConsumoDAO.buscarAlertasConsumoPorIdCliente(idCliente);
        if (alerta == null) {
            throw new IllegalArgumentException("Nenhum alerta foi encontrado para o cliente com ID " + idCliente + ".");
        }
        return alerta;
    }
}
