package fiap.tds.model.bo;

import fiap.tds.model.dao.SugestoesEconomiaDAO;
import fiap.tds.model.dao.SugestoesEconomiaDAOImpl;
import fiap.tds.model.vo.SugestoesEconomia;

import java.sql.SQLException;
import java.util.List;

public class SugestoesEconomiaBO {
    private SugestoesEconomiaDAO sugestoesEconomiaDAO;

    public SugestoesEconomiaBO() throws SQLException {
        this.sugestoesEconomiaDAO = new SugestoesEconomiaDAOImpl();
    }

    public SugestoesEconomia inserirSugestao(SugestoesEconomia sugestao) throws SQLException {
        if (sugestao == null || sugestao.getMensagem() == null || sugestao.getMensagem().isBlank()) {
            throw new IllegalArgumentException("A mensagem da sugestão não pode estar vazia.");
        }

        if (sugestao.getId_cliente() <= 0) {
            throw new IllegalArgumentException("ID do cliente deve ser maior que zero.");
        }

        return sugestoesEconomiaDAO.inserirSugestoesEconomia(sugestao);
    }

    public boolean atualizarSugestao(SugestoesEconomia sugestao) throws SQLException {
        if (sugestao == null || sugestao.getId_sugestao() <= 0) {
            throw new IllegalArgumentException("ID da sugestão inválido.");
        }

        return sugestoesEconomiaDAO.atualizarSugestoesEconomia(sugestao);
    }

    public boolean excluirSugestao(int idSugestao) throws SQLException {
        if (idSugestao <= 0) {
            throw new IllegalArgumentException("ID da sugestão inválido.");
        }

        return sugestoesEconomiaDAO.excluirSugestoesEconomia(idSugestao);
    }

    public List<SugestoesEconomia> listarSugestoes() throws SQLException {
        return sugestoesEconomiaDAO.listarSugestoesEconomia();
    }

    public SugestoesEconomia buscarSugestaoPorId(int idSugestao) throws SQLException {
        if (idSugestao <= 0) {
            throw new IllegalArgumentException("ID da sugestão inválido.");
        }

        return sugestoesEconomiaDAO.buscarSugestoesEconomiaId(idSugestao);
    }

    public SugestoesEconomia buscarSugestaoPorIdCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        return sugestoesEconomiaDAO.buscarSugestoesEconomiaPorIdCliente(idCliente);
    }
}
