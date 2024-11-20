package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.AlertasConsumo;
import fiap.tds.model.vo.Cliente;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fiap.tds.credenciais.Credenciais.url;

public class AlertasConsumoDAOImpl implements AlertasConsumoDAO{
    private Connection conn;
    public AlertasConsumoDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public void inserirAlerta(AlertasConsumo alertasConsumo) throws SQLException {
        String sql = "INSERT INTO tb_alertas_consumo (id_cliente, mensagem) VALUES ( ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, alertasConsumo.getCliente().getId_cliente());
            pstmt.setString(2, alertasConsumo.getMensagem());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void excluirAlerta(int id) throws SQLException {
        String sql = "DELETE FROM tb_alertas_consumo WHERE id_alerta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarAlerta(AlertasConsumo alertasConsumo) throws SQLException {
        String sql = "UPDATE tb_alertas_consumo SET id_cliente = ?, mensagem = ? WHERE id_alerta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alertasConsumo.getCliente().getId_cliente());
            stmt.setInt(3, alertasConsumo.getId_alerta());
            stmt.setString(2, alertasConsumo.getMensagem());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<AlertasConsumo> listarAlertas() throws SQLException {
        List<AlertasConsumo> alertasConsumos = new ArrayList<>();
        String sql = "SELECT * FROM tb_alertas_consumo v JOIN TB_CLIENTE c ON v.id_cliente = c.id_cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente")
                );

                AlertasConsumo alertasConsumo = new AlertasConsumo(
                        cliente,
                        rs.getInt("id_alerta"),
                        rs.getString("mensagem")
                );

                alertasConsumos.add(alertasConsumo);
            }
        }
        return alertasConsumos;
    }

    @Override
    public AlertasConsumo buscarAlertaPorId(int id) throws SQLException {
        String sql = "SELECT v.*, c.nome_cliente FROM tb_alertas_consumo v " +
                "JOIN TB_CLIENTE c ON v.id_cliente = c.id_cliente WHERE v.id_alerta = ?";
        AlertasConsumo alertasConsumo = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    );

                    alertasConsumo = new AlertasConsumo(
                            cliente,
                            rs.getInt("id_alerta"),
                            rs.getString("mensagem")
                    );
                }
            }
        }
        return alertasConsumo;
    }

    @Override
    public AlertasConsumo buscarAlertasConsumoPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT v.*, c.nome_cliente FROM tb_alertas_consumo v " +
                "JOIN TB_CLIENTE c ON v.id_cliente = c.id_cliente WHERE v.id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    );

                    return new AlertasConsumo(
                            cliente,
                            rs.getInt("id_alerta"),
                            rs.getString("mensagem")
                    );
                }
            }
        }
        return null;
    }
}
