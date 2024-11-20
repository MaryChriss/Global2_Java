package fiap.tds.model.dao;

import fiap.tds.model.vo.Feedback;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackDAO {
    void inserirFeedback (Feedback feedback) throws SQLException;
    void excluirFeedback(int id) throws SQLException;
    void atualizarFeedback(Feedback feedback) throws  SQLException;
    public List<Feedback> listarFeedback() throws SQLException;
    public Feedback buscarFeedbackId(int id) throws SQLException;
    public Feedback buscarFeedbackPorIdCliente(int idCliente) throws SQLException;

}
