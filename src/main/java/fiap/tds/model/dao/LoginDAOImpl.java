package fiap.tds.model.dao;

import fiap.tds.credenciais.Credenciais;
import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Login;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    private Connection conn;
    private ClienteDAOImpl clienteDAO;

    public LoginDAOImpl() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(Credenciais.url);
        ods.setUser(Credenciais.user);
        ods.setPassword(Credenciais.pwd);
        conn = ods.getConnection();
        clienteDAO = new ClienteDAOImpl();
    }

    @Override
    public Login inserirLogin(Login login) {
        String sql = "INSERT INTO TB_LOGIN ( id_cliente, email_login, senha_login) VALUES ( ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id_login" })) {
            ps.setInt(1, login.getId_cliente());
            ps.setString(2, login.getEmail_login());
            ps.setString(3, login.getSenha_login());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    login.setId_login(generatedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return login;
    }


    @Override
    public Login autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN WHERE email_login = ? AND senha_login = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idLogin = rs.getInt("id_login");
                int idCliente = rs.getInt("id_cliente");
                String senhaRetornada = rs.getString("senha_login");
                Cliente cliente = clienteDAO.buscarClienteId(idCliente);
                return new Login(cliente, email, idLogin, senhaRetornada);
            }
            return null;
        }
    }

    @Override
    public boolean atualizarLogin(int idCliente, Cliente cliente, String email, String senha) {
        String sql = "UPDATE TB_LOGIN SET email_login = ?, senha_login = ?  WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setInt(3, idCliente);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluirLogin(int idCliente) {
        String sql = "DELETE FROM TB_LOGIN WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Login> listarLogins() throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN"; // Consulta para buscar logins
        List<Login> listaLogins = new ArrayList<>(); // Lista para armazenar os logins

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Executa a consulta

            while (rs.next()) {
                // Busca o ID do cliente associado ao login
                int idCliente = rs.getInt("id_cliente");

                // Busca o cliente no DAO de clientes
                Cliente cliente = clienteDAO.buscarClienteId(idCliente);

                // Exibe os valores retornados para depuração
                System.out.println("ID Cliente: " + idCliente);
                System.out.println("Email Login: " + rs.getString("email_login"));

                // Cria um objeto Login com os dados retornados
                Login login = new Login(
                        cliente,
                        rs.getString("email_login"),
                        rs.getInt("id_login"),
                        rs.getString("senha_login")
                );

                // Adiciona o login à lista
                listaLogins.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erros no console
        }

        return listaLogins; // Retorna a lista de logins
    }



    @Override
    public Login buscarLoginPorId(int idCliente) throws SQLException {
        String sql = "SELECT * FROM TB_LOGIN WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id_cliente");
                Cliente cliente = clienteDAO.buscarClienteId(clienteId);
                return new Login(
                        cliente,
                        rs.getString("email_login"),
                        clienteId,
                        rs.getString("senha_login")
                );
            }
        }
        return null;
    }
}
