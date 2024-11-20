package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Aparelhos;
import fiap.tds.model.vo.LogConsumo;
import fiap.tds.model.vo.Relatorio;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fiap.tds.credenciais.Credenciais.url;

public class LogConsumoDAOImpl implements LogConsumoDAO {
    private Connection conn;

    public LogConsumoDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public void inserirLogConsumo(LogConsumo logConsumo) throws SQLException {
        String sql = "INSERT INTO tb_logConsumo ( id_relatorio, id_aparelho, consumo_real) " +
                "VALUES ( ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, logConsumo.getRelatorio().getId_relatorio());
            pstmt.setInt(2, logConsumo.getAparelhos().getId_aparelho());
            pstmt.setFloat(3, logConsumo.getConsumo_real());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void excluirLogConsumo(int id) throws SQLException {
        String sql = "DELETE FROM tb_logConsumo WHERE id_log = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizarLogConsumo(LogConsumo logConsumo) throws SQLException {
        String sql = "UPDATE tb_logConsumo SET id_relatorio = ?, id_aparelho = ?, consumo_real = ? " +
                "WHERE id_log = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, logConsumo.getRelatorio().getId_relatorio());
            pstmt.setInt(2, logConsumo.getAparelhos().getId_aparelho());
            pstmt.setFloat(3, logConsumo.getConsumo_real());
            pstmt.setInt(4, logConsumo.getId_log());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<LogConsumo> listarLogConsumo() throws SQLException {
        List<LogConsumo> logs = new ArrayList<>();
        String sql = "SELECT lc.id_log, lc.consumo_real, lc.data_consumo, " +
                "r.id_relatorio, r.mes_referencia, r.quantidade_pessoas, " +
                "a.id_aparelho, a.nome_aparelho, a.consumo " +
                "FROM tb_logConsumo lc " +
                "JOIN tb_relatorio r ON lc.id_relatorio = r.id_relatorio " +
                "JOIN tb_aparelhos a ON lc.id_aparelho = a.id_aparelho";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id_relatorio"),
                        rs.getString("mes_referencia"),
                        rs.getInt("quantidade_pessoas")
                );
                Aparelhos aparelhos = new Aparelhos(
                        rs.getInt("consumo"),
                        rs.getInt("id_aparelho"),
                        rs.getString("nome_aparelho")
                );
                LogConsumo log = new LogConsumo(
                        aparelhos,
                        rs.getFloat("consumo_real"),
                        rs.getInt("id_aparelho"),
                        rs.getInt("id_log"),
                        rs.getInt("id_relatorio"),
                        relatorio
                );
                logs.add(log);
            }
        }
        return logs;
    }

    @Override
    public LogConsumo buscarLogConsumoId(int id) throws SQLException {
        String sql = "SELECT lc.id_log, lc.consumo_real, lc.data_consumo, " +
                "r.id_relatorio, r.mes_referencia, r.quantidade_pessoas, " +
                "a.id_aparelho, a.nome_aparelho, a.consumo " +
                "FROM tb_logConsumo lc " +
                "JOIN tb_relatorio r ON lc.id_relatorio = r.id_relatorio " +
                "JOIN tb_aparelhos a ON lc.id_aparelho = a.id_aparelho " +
                "WHERE lc.id_log = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id_relatorio"),
                        rs.getString("mes_referencia"),
                        rs.getInt("quantidade_pessoas")
                );
                Aparelhos aparelhos = new Aparelhos(
                        rs.getInt("consumo"),
                        rs.getInt("id_aparelho"),
                        rs.getString("nome_aparelho")
                );
                return new LogConsumo(
                        aparelhos,
                        rs.getFloat("consumo_real"),
                        rs.getInt("id_aparelho"),
                        rs.getInt("id_log"),
                        rs.getInt("id_relatorio"),
                        relatorio
                );
            } else {
                throw new SQLException("Nenhum log de consumo encontrado com o ID especificado.");
            }
        }
    }


    @Override
    public LogConsumo buscarLogConsumoPorIdCliente(int idRelatorio) throws SQLException {
        String sql = "SELECT lc.id_log, lc.consumo_real, lc.data_consumo, " +
                "r.id_relatorio, r.mes_referencia, r.quantidade_pessoas, " +
                "a.id_aparelho, a.nome_aparelho, a.consumo " +
                "FROM tb_logConsumo lc " +
                "JOIN tb_relatorio r ON lc.id_relatorio = r.id_relatorio " +
                "JOIN tb_aparelhos a ON lc.id_aparelho = a.id_aparelho " +
                "WHERE r.id_relatorio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRelatorio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id_relatorio"),
                        rs.getString("mes_referencia"),
                        rs.getInt("quantidade_pessoas")
                );
                Aparelhos aparelhos = new Aparelhos(
                        rs.getInt("consumo"),
                        rs.getInt("id_aparelho"),
                        rs.getString("nome_aparelho")
                );
                return new LogConsumo(
                        aparelhos,
                        rs.getFloat("consumo_real"),
                        rs.getInt("id_aparelho"),
                        rs.getInt("id_log"),
                        rs.getInt("id_relatorio"),
                        relatorio
                );
            }
        }
        return null;
    }
}
