package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.SugestoesEconomia;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SugestoesEconomiaDAOImpl implements SugestoesEconomiaDAO {

    private Connection conn;

    public SugestoesEconomiaDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public SugestoesEconomia inserirSugestoesEconomia(SugestoesEconomia sugestoesEconomia) throws SQLException {
        String sql = "INSERT INTO tb_sugestoes_economia (id_cliente, mensagem) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_sugestao"})) {
            pstmt.setInt(1, sugestoesEconomia.getId_cliente());
            pstmt.setString(2, sugestoesEconomia.getMensagem());
            pstmt.executeUpdate();


            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                sugestoesEconomia.setId_sugestao(rs.getInt(1));
            }
        }
        return sugestoesEconomia;
    }

    @Override
    public boolean excluirSugestoesEconomia(int id) throws SQLException {
        String sql = "DELETE FROM tb_sugestoes_economia WHERE id_sugestao = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean atualizarSugestoesEconomia(SugestoesEconomia sugestoesEconomia) throws SQLException {
        String sql = "UPDATE tb_sugestoes_economia SET id_cliente = ?, mensagem = ? WHERE id_sugestao = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sugestoesEconomia.getId_cliente());
            pstmt.setString(2, sugestoesEconomia.getMensagem());
            pstmt.setInt(3, sugestoesEconomia.getId_sugestao());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<SugestoesEconomia> listarSugestoesEconomia() throws SQLException {
        List<SugestoesEconomia> sugestoesList = new ArrayList<>();
        String sql = "SELECT id_sugestao, id_cliente, mensagem FROM tb_sugestoes_economia";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SugestoesEconomia sugestao = new SugestoesEconomia();
                sugestao.setId_sugestao(rs.getInt("id_sugestao"));
                sugestao.setId_cliente(rs.getInt("id_cliente"));
                sugestao.setMensagem(rs.getString("mensagem"));
                sugestoesList.add(sugestao);
            }
        }
        return sugestoesList;
    }

    @Override
    public SugestoesEconomia buscarSugestoesEconomiaId(int id) throws SQLException {
        String sql = "SELECT id_sugestao, id_cliente, mensagem FROM tb_sugestoes_economia WHERE id_sugestao = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new SugestoesEconomia(
                        null,  // Cliente deve ser buscado em outro DAO se necessário
                        rs.getInt("id_cliente"),
                        rs.getInt("id_sugestao"),
                        rs.getString("mensagem")
                );
            }
        }
        return null;
    }

    @Override
    public SugestoesEconomia buscarSugestoesEconomiaPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT id_sugestao, id_cliente, mensagem FROM tb_sugestoes_economia WHERE id_cliente = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new SugestoesEconomia(
                        null,  // Cliente deve ser buscado em outro DAO se necessário
                        rs.getInt("id_cliente"),
                        rs.getInt("id_sugestao"),
                        rs.getString("mensagem")
                );
            }
        }
        return null;
    }
}
