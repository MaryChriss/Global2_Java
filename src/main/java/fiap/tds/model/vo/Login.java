package fiap.tds.model.vo;

public class Login {
    private int id_login;
    private String email_login;
    private String senha_login;
    private int id_cliente;
    private Cliente cliente;

    public Login() {
    }

    public Login(Cliente cliente, String emailLogin, int idLogin, String senhaLogin) {
        this.cliente = cliente;
        this.email_login = emailLogin;
        this.id_login = idLogin;
        this.senha_login = senhaLogin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public String getSenha_login() {
        return senha_login;
    }

    public void setSenha_login(String senha_login) {
        this.senha_login = senha_login;
    }
}
