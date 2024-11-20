package fiap.tds.model.dao;

import fiap.tds.model.vo.Aparelhos;

import java.sql.SQLException;
import java.util.List;

public interface AparelhosDAO {

    public Aparelhos inserirAparelho (Aparelhos aparelhos) throws SQLException;
    public boolean excluirAparelho(int id) throws SQLException;
    public boolean atualizarAparelho(Aparelhos aparelhos) throws  SQLException;
    public List<Aparelhos> listarAparelho() throws SQLException;
    public Aparelhos buscarAparelhoPorId(int id) throws SQLException;
}
