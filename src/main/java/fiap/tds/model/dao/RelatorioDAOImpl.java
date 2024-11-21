package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Relatorio;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAOImpl implements RelatorioDAO {

    private Connection conn;

    public RelatorioDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        this.conn = ods.getConnection();
    }

    @Override
    public Relatorio inserirRelatorio(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO tb_relatorio (mes_referencia, quantidade_pessoas, id_endereco) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_relatorio"})) {
            pstmt.setString(1, relatorio.getMes_referencia());
            pstmt.setInt(2, relatorio.getQuantidade_pessoas());
            pstmt.setInt(3, relatorio.getId_endereco());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    relatorio.setId_relatorio(rs.getInt(1));
                }
            }
        }
        return relatorio;
    }

    @Override
    public boolean excluirRelatorio(int id) throws SQLException {
        String sql = "DELETE FROM tb_relatorio WHERE id_relatorio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean atualizarRelatorio(Relatorio relatorio) throws SQLException {
        String sql = "UPDATE tb_relatorio SET mes_referencia = ?, quantidade_pessoas = ?, id_endereco = ? WHERE id_relatorio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, relatorio.getMes_referencia());
            pstmt.setInt(2, relatorio.getQuantidade_pessoas());
            pstmt.setInt(3, relatorio.getId_endereco());
            pstmt.setInt(4, relatorio.getId_relatorio());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public List<Relatorio> listarRelatorio() throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT id_relatorio, mes_referencia, quantidade_pessoas, id_endereco FROM tb_relatorio";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getInt("id_relatorio"),
                        rs.getString("mes_referencia"),
                        rs.getInt("quantidade_pessoas")
                );
                relatorio.setId_endereco(rs.getInt("id_endereco"));
                relatorios.add(relatorio);
            }
        }
        return relatorios;
    }

    @Override
    public Relatorio buscarRelatorioId(int id) throws SQLException {
        String sql = "SELECT id_relatorio, mes_referencia, quantidade_pessoas, id_endereco FROM tb_relatorio WHERE id_relatorio = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Relatorio relatorio = new Relatorio(
                            rs.getInt("id_relatorio"),
                            rs.getString("mes_referencia"),
                            rs.getInt("quantidade_pessoas")
                    );
                    relatorio.setId_endereco(rs.getInt("id_endereco"));
                    return relatorio;
                }
            }
        }
        return null;
    }

    @Override
    public List<Relatorio> listarRelatoriosPorEndereco(int idEndereco) throws SQLException {
                List<Relatorio> relatorios = new ArrayList<>();
            String sql = "SELECT id_relatorio, mes_referencia, quantidade_pessoas, id_endereco FROM tb_relatorio WHERE id_endereco = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idEndereco);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Relatorio relatorio = new Relatorio();
                        relatorio.setId_relatorio(rs.getInt("id_relatorio"));
                        relatorio.setMes_referencia(rs.getString("mes_referencia"));
                        relatorio.setQuantidade_pessoas(rs.getInt("quantidade_pessoas"));
                        relatorio.setId_endereco(rs.getInt("id_endereco"));
                        relatorios.add(relatorio);
                    }
                }
            }
            return relatorios;
        }

    }

