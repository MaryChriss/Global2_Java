package fiap.tds.model.bo;


import fiap.tds.model.dao.LoginDAO;
import fiap.tds.model.dao.LoginDAOImpl;
import fiap.tds.model.vo.Login;

import java.sql.SQLException;
import java.util.List;

public class LoginBO {
    private LoginDAO loginDAO;

    public LoginBO() throws SQLException {
        this.loginDAO = new LoginDAOImpl();
    }

    public Login autenticar(Login login) throws SQLException {
        if (!validarEmail(login.getEmail_login())) {
            throw new IllegalArgumentException("Email inválido. Certifique-se de que contém '@' e um domínio válido.");
        }

        if (!validarSenha(login.getSenha_login())) {
            throw new IllegalArgumentException("Senha inválida. A senha deve ter pelo menos 6 caracteres.");
        }

        Login loginAutenticado = loginDAO.autenticar(login.getEmail_login(), login.getSenha_login());
        if (loginAutenticado == null) {
            throw new IllegalArgumentException("Email ou senha invalidos" );
        }

        return loginAutenticado;
    }

    public Login inserirLogin(Login login) throws SQLException {
        if (login == null || login.getEmail_login() == null || login.getSenha_login() == null) {
            System.out.println(login.getEmail_login());
            System.out.println(login.getSenha_login());
            throw new IllegalArgumentException("Login inválido. Os campos de email e senha são obrigatórios." + login);
        }

        return loginDAO.inserirLogin(login);
    }


    public boolean atualizarLogin(Login login) throws SQLException {
        if (login == null || login.getId_login() <= 0) {
            throw new IllegalArgumentException("ID de login inválido.");
        }

        return loginDAO.atualizarLogin(login.getId_login(), login.getCliente(), login.getEmail_login(), login.getSenha_login());
    }

    public boolean excluirLogin(int idLogin) throws SQLException {
        if (idLogin <= 0) {
            throw new IllegalArgumentException("ID de login inválido.");
        }

        return loginDAO.excluirLogin(idLogin);
    }

    public List<Login> listarLogins() throws SQLException {
        return loginDAO.listarLogins();
    }

    private boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }

    public Login buscarLoginPorId(int idLogin) throws SQLException {
        return loginDAO.buscarLoginPorId(idLogin);
    }

}
