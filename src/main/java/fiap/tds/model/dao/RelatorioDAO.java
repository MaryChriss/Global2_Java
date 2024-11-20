package fiap.tds.model.dao;

import fiap.tds.model.vo.Relatorio;

import java.sql.SQLException;
import java.util.List;

public interface RelatorioDAO {
    public Relatorio inserirRelatorio (Relatorio relatorio) throws SQLException;
    public boolean excluirRelatorio(int id) throws SQLException;
    public boolean atualizarRelatorio(Relatorio relatorio) throws  SQLException;
    public List<Relatorio> listarRelatorio() throws SQLException;
    public Relatorio buscarRelatorioId(int id) throws SQLException;
}
