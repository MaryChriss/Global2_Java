package fiap.tds.model.vo;

public class Cliente {
    private int id_cliente;
    public String nome_cliente;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nome_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }
}
