package fiap.tds.model.vo;

public class Endereco {
    private int id_endereco;
    private Cliente cliente;
    private String endereco_completo;

    public Endereco() {
    }

    public Endereco(Cliente cliente, String endereco_completo, int id_endereco) {
        this.cliente = cliente;
        this.endereco_completo = endereco_completo;
        this.id_endereco = id_endereco;
    }


    public Endereco(int idEndereco, String endereco) {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEndereco_completo() {
        return endereco_completo;
    }

    public void setEndereco_completo(String endereco_completo) {
        this.endereco_completo = endereco_completo;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }
}
