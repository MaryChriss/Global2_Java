package fiap.tds.model.dao;

import fiap.tds.model.vo.Objetivos;

import java.sql.SQLException;
import java.util.List;

public interface ObjetivosDAO {
    public Objetivos inserirObjetivos (Objetivos objetivos) throws SQLException;
    public boolean excluirObjetivos(int id) throws SQLException;
    public boolean atualizarObjetivos(Objetivos objetivos) throws  SQLException;
    public List<Objetivos> listarObjetivos() throws SQLException;
    public Objetivos buscarObjetivosId(int id) throws SQLException;
    public List<Objetivos> buscarObjetivosPorIdCliente(int idCliente) throws SQLException;
}
