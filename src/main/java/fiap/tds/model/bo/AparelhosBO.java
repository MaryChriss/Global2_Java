package fiap.tds.model.bo;

import fiap.tds.model.dao.AparelhosDAO;
import fiap.tds.model.dao.AparelhosDAOImpl;
import fiap.tds.model.vo.Aparelhos;

import java.sql.SQLException;
import java.util.List;

public class AparelhosBO {
    private AparelhosDAO aparelhosDAO;

    public AparelhosBO() throws SQLException {
        this.aparelhosDAO = new AparelhosDAOImpl();
    }

    public Aparelhos inserirAparelho(Aparelhos aparelho) throws SQLException {
        if (aparelho == null || aparelho.getNome_aparelho() == null || aparelho.getNome_aparelho().isEmpty()) {
            throw new IllegalArgumentException("Nome do aparelho é inválido.");
        }
        return aparelhosDAO.inserirAparelho(aparelho);
    }

    public boolean excluirAparelho(int idAparelho) throws SQLException {
        if (idAparelho <= 0) {
            throw new IllegalArgumentException("ID do aparelho inválido.");
        }
        return aparelhosDAO.excluirAparelho(idAparelho);
    }

    public List<Aparelhos> listarAparelhos() throws SQLException {
        return aparelhosDAO.listarAparelho();
    }

    public boolean atualizarAparelho(Aparelhos aparelhos) throws SQLException {
        if (aparelhos == null || aparelhos.getId_aparelho() <= 0) {
            throw new IllegalArgumentException("ID do aparelho inválido.");
        }
        return aparelhosDAO.atualizarAparelho(aparelhos);
    }
}
