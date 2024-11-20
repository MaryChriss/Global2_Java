package fiap.tds.model.dao;


import fiap.tds.model.vo.Cliente;
import fiap.tds.model.vo.Login;

import java.sql.SQLException;
import java.util.List;

public interface LoginDAO {

    public Login inserirLogin (Login login);
    public Login autenticar(String email_login, String senha_login) throws SQLException;
    public boolean atualizarLogin(int idLogin, Cliente cliente, String email_login, String senha_login);
    public boolean excluirLogin (int idLogin);
    List<Login> listarLogins() throws SQLException;

    Login buscarLoginPorId(int idLogin) throws SQLException;
}
