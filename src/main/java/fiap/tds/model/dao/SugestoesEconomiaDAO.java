package fiap.tds.model.dao;


import fiap.tds.model.vo.SugestoesEconomia;

import java.sql.SQLException;
import java.util.List;

public interface SugestoesEconomiaDAO {
    public SugestoesEconomia inserirSugestoesEconomia (SugestoesEconomia sugestoesEconomia) throws SQLException;
    public boolean excluirSugestoesEconomia(int id) throws SQLException;
    public boolean atualizarSugestoesEconomia(SugestoesEconomia sugestoesEconomia) throws  SQLException;
    public List<SugestoesEconomia> listarSugestoesEconomia() throws SQLException;
    public SugestoesEconomia buscarSugestoesEconomiaId(int id) throws SQLException;
    public SugestoesEconomia buscarSugestoesEconomiaPorIdCliente(int idCliente) throws SQLException;

}
