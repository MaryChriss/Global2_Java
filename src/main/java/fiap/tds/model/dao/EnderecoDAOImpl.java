package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Endereco;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO {
    private Connection conn;

    public EnderecoDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public void inserirEndereco(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO tb_endereco (id_cliente, endereco_completo) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, endereco.getCliente().getId_cliente());
            pstmt.setString(2, endereco.getEndereco_completo());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void excluirEndereco(int id) throws SQLException {
        String sql = "DELETE FROM tb_endereco WHERE id_endereco = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void atualizarEndereco(Endereco endereco) throws SQLException {
        String sql = "UPDATE tb_endereco SET id_cliente = ?, endereco_completo = ? WHERE id_endereco = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, endereco.getCliente().getId_cliente());
            pstmt.setString(2, endereco.getEndereco_completo());
            pstmt.setInt(3, endereco.getId_endereco());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Endereco> listarEndereco() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM tb_endereco v JOIN tb_cliente c ON v.id_cliente = c.id_cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente")
                );
                Endereco endereco = new Endereco(
                        cliente,
                        rs.getString("endereco_completo"),
                        rs.getInt("id_endereco")
                );
                enderecos.add(endereco);
            }
        }
        return enderecos;
    }

    @Override
    public Endereco buscarEnderecoId(int id) throws SQLException {
        String sql = "SELECT * FROM tb_endereco v JOIN tb_cliente c ON v.id_cliente = c.id_cliente WHERE v.id_endereco = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    );
                    return new Endereco(
                            cliente,
                            rs.getString("endereco_completo"),
                            rs.getInt("id_endereco")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public Endereco buscarEnderecoPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM tb_endereco WHERE id_cliente = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
                    Cliente cliente = clienteDAO.buscarClienteId(idCliente);
                    return new Endereco(
                            cliente,
                            rs.getString("endereco_completo"),
                            rs.getInt("id_endereco")
                    );
                }
            }
        }
        return null;
    }
}
