package fiap.tds.model.bo;

import fiap.tds.model.dao.FeedbackDAO;
import fiap.tds.model.dao.FeedbackDAOImpl;
import fiap.tds.model.vo.Feedback;

import java.sql.SQLException;
import java.util.List;

public class FeedbackBO {
    private final FeedbackDAO feedbackDAO;

    public FeedbackBO() throws SQLException {
        this.feedbackDAO = new FeedbackDAOImpl();
    }

    public Feedback inserirFeedback(Feedback feedback) throws SQLException {
        validarFeedback(feedback);
        feedbackDAO.inserirFeedback(feedback);
        return feedback;
    }

    // Excluir feedback por ID
    public void excluirFeedback(int idFeedback) throws SQLException {
        if (idFeedback <= 0) {
            throw new IllegalArgumentException("ID do feedback inválido.");
        }
        feedbackDAO.excluirFeedback(idFeedback);
    }

    public Feedback atualizarFeedback(Feedback feedback) throws SQLException {
        if (feedback.getId_feedback() <= 0) {
            throw new IllegalArgumentException("ID do feedback inválido.");
        }
        validarFeedback(feedback);
        feedbackDAO.atualizarFeedback(feedback);
        return feedback;
    }

    public List<Feedback> listarFeedbacks() throws SQLException {
        return feedbackDAO.listarFeedback();
    }

    public Feedback buscarFeedbackPorId(int idFeedback) throws SQLException {
        if (idFeedback <= 0) {
            throw new IllegalArgumentException("ID do feedback inválido.");
        }
        Feedback feedback = feedbackDAO.buscarFeedbackId(idFeedback);
        if (feedback == null) {
            throw new SQLException("Feedback não encontrado.");
        }
        return feedback;
    }


    private void validarFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback não pode ser nulo.");
        }
        if (feedback.getAvaliacao() < 1 || feedback.getAvaliacao() > 5) {
            throw new IllegalArgumentException("Avaliação deve estar entre 1 e 5 estrelas.");
        }
        if (feedback.getComentario() == null || feedback.getComentario().isEmpty()) {
            throw new IllegalArgumentException("Comentário não pode ser vazio.");
        }
        if (feedback.getCliente() == null || feedback.getCliente().getId_cliente() <= 0) {
            throw new IllegalArgumentException("Cliente associado ao feedback é inválido.");
        }
    }

    public Feedback buscarFeedbackPorIdCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido.");
        }

        Feedback feedback = feedbackDAO.buscarFeedbackPorIdCliente(idCliente);
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback não encontrado para o cliente fornecido.");
        }
        return feedback;
    }
}
