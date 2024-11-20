package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Cliente;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fiap.tds.credenciais.Credenciais.url;

public class ClienteDAOImpl implements ClienteDAO {
    private Connection conn;
    public ClienteDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
    }

    @Override
    public Cliente inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO TB_CLIENTE (nome_cliente) VALUES (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id_cliente" })) {
            ps.setString(1, cliente.getNome_cliente());
            ps.executeUpdate();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    cliente.setId_cliente(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return cliente;
    }


    @Override
    public boolean excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM TB_CLIENTE WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE TB_CLIENTE SET nome_cliente = ? WHERE id_cliente = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome_cliente());
            ps.setInt(2, cliente.getId_cliente());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }

        return true;
    }

    @Override
    public List<Cliente> listarCliente() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TB_CLIENTE";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                clientes.add(new Cliente(id,nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public Cliente buscarClienteId(int id) throws SQLException {
        String sql = "SELECT * FROM TB_CLIENTE WHERE id_cliente = ?";
        Cliente cliente = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idCliente = id;
                String nome = rs.getString("nome_cliente");

                cliente = new Cliente(idCliente, nome);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
}
