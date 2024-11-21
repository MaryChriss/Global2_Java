package fiap.tds.model.bo;

import fiap.tds.model.dao.RelatorioDAO;
import fiap.tds.model.dao.RelatorioDAOImpl;
import fiap.tds.model.vo.Feedback;
import fiap.tds.model.vo.Relatorio;

import java.sql.SQLException;
import java.util.List;

public class RelatorioBO {

    private RelatorioDAO relatorioDAO;

    public RelatorioBO() throws SQLException {
        this.relatorioDAO = new RelatorioDAOImpl();
    }

    public Relatorio inserirRelatorio(Relatorio relatorio) throws SQLException {
        if (relatorio == null || relatorio.getMes_referencia() == null || relatorio.getMes_referencia().trim().isEmpty()) {
            throw new IllegalArgumentException("Mês de referência inválido.");
        }
        if (relatorio.getQuantidade_pessoas() <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas inválida.");
        }
        if (relatorio.getId_endereco() <= 0) {
            throw new IllegalArgumentException("ID do endereço inválido.");
        }
        return relatorioDAO.inserirRelatorio(relatorio);
    }

    public boolean atualizarRelatorio(Relatorio relatorio) throws SQLException {
        if (relatorio == null || relatorio.getId_relatorio() <= 0) {
            throw new IllegalArgumentException("ID do relatório inválido.");
        }
        return relatorioDAO.atualizarRelatorio(relatorio);
    }

    public boolean excluirRelatorio(int idRelatorio) throws SQLException {
        if (idRelatorio <= 0) {
            throw new IllegalArgumentException("ID do relatório inválido.");
        }
        return relatorioDAO.excluirRelatorio(idRelatorio);
    }

    public List<Relatorio> listarRelatorios() throws SQLException {
        return relatorioDAO.listarRelatorio();
    }

    public Relatorio buscarRelatorioPorId(int idRelatorio) throws SQLException {
        if (idRelatorio <= 0) {
            throw new IllegalArgumentException("ID do relatório inválido.");
        }
        return relatorioDAO.buscarRelatorioId(idRelatorio);
    }

    public List<Relatorio> listarRelatoriosPorEndereco(int idEndereco) throws SQLException {
        return relatorioDAO.listarRelatoriosPorEndereco(idEndereco);
    }

}
