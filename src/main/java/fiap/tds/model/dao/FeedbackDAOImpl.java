package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Endereco;
import fiap.tds.model.vo.Feedback;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fiap.tds.credenciais.Credenciais.url;

public class FeedbackDAOImpl implements FeedbackDAO{
    private Connection conn;
    public FeedbackDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }


    @Override
    public void inserirFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO tb_feedback (id_cliente, comentario, avaliacao) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_feedback"})) {
            pstmt.setInt(1, feedback.getCliente().getId_cliente());
            pstmt.setString(2, feedback.getComentario());
            pstmt.setInt(3, feedback.getAvaliacao());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    feedback.setId_feedback(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void excluirFeedback(int id) throws SQLException {
        String sql = "DELETE FROM tb_feedback WHERE id_feedback = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE tb_feedback SET id_cliente = ?, comentario = ?, avaliacao = ? WHERE id_feedback = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getCliente().getId_cliente());
            stmt.setString(2, feedback.getComentario());
            stmt.setInt(3, feedback.getAvaliacao());
            stmt.setInt(4, feedback.getId_feedback());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Feedback> listarFeedback() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM tb_feedback v JOIN tb_cliente c ON v.id_cliente = c.id_cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente")
                );
                Feedback feedback = new Feedback(
                        rs.getInt("avaliacao"),
                        cliente,
                        rs.getString("comentario"),
                        rs.getInt("id_feedback")
                );
                feedbacks.add(feedback);
            }
        }
        return feedbacks;
    }

    @Override
    public Feedback buscarFeedbackId(int id) throws SQLException {
        String sql = """
            SELECT f.*, c.nome_cliente
            FROM tb_feedback f
            JOIN tb_cliente c ON f.id_cliente = c.id_cliente
            WHERE f.id_feedback = ?
            """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    );
                    return new Feedback(
                            rs.getInt("avaliacao"),
                            cliente,
                            rs.getString("comentario"),
                            rs.getInt("id_feedback")
                    );
                }
            }
        }
        return null;
    }


    @Override
    public Feedback buscarFeedbackPorIdCliente(int idCliente) throws SQLException {
        String sql = """
                SELECT f.*, c.nome_cliente
                FROM tb_feedback f
                JOIN tb_cliente c ON f.id_cliente = c.id_cliente
                WHERE f.id_cliente = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    );
                    return new Feedback(
                            rs.getInt("avaliacao"),
                            cliente,
                            rs.getString("comentario"),
                            rs.getInt("id_feedback")
                    );
                }
            }
        }
        return null;
    }
}