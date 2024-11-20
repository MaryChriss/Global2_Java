package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Aparelhos;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AparelhosDAOImpl implements AparelhosDAO {
    private Connection conn;

    public AparelhosDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public Aparelhos inserirAparelho(Aparelhos aparelhos) throws SQLException {
        String sql = "INSERT INTO tb_aparelhos (nome_aparelho, consumo) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_aparelho"})) {
            ps.setString(1, aparelhos.getNome_aparelho());
            ps.setInt(2, aparelhos.getConsumo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aparelhos.setId_aparelho(rs.getInt(1));
                }
            }
        }
        return aparelhos;
    }

    @Override
    public boolean excluirAparelho(int id) throws SQLException {
        String sql = "DELETE FROM tb_aparelhos WHERE id_aparelho = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean atualizarAparelho(Aparelhos aparelhos) throws SQLException {
        String sql = "UPDATE tb_aparelhos SET nome_aparelho = ?, consumo = ? WHERE id_aparelho = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aparelhos.getNome_aparelho());
            ps.setInt(2, aparelhos.getConsumo());
            ps.setInt(3, aparelhos.getId_aparelho());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Aparelhos> listarAparelho() throws SQLException {
        List<Aparelhos> aparelhos = new ArrayList<>();
        String sql = "SELECT * FROM tb_aparelhos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aparelhos aparelho = new Aparelhos(
                        rs.getInt("consumo"),
                        rs.getInt("id_aparelho"),
                        rs.getString("nome_aparelho")
                );
                aparelhos.add(aparelho);
            }
        }
        return aparelhos;
    }

    @Override
    public Aparelhos buscarAparelhoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tb_aparelhos WHERE id_aparelho = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Aparelhos(
                            rs.getInt("consumo"),
                            rs.getInt("id_aparelho"),
                            rs.getString("nome_aparelho")
                    );
                }
            }
        }
        return null;
    }
}
