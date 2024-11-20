package fiap.tds.model.vo;

public class AlertasConsumo {
    private int id_alerta;
    private Cliente cliente;
    private String mensagem;

    public AlertasConsumo() {
    }

    public AlertasConsumo(Cliente cliente, int id_alerta, String mensagem) {
        this.cliente = cliente;
        this.id_alerta = id_alerta;
        this.mensagem = mensagem;
    }

    public AlertasConsumo(String nome, int idAlerta) {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(int id_alerta) {
        this.id_alerta = id_alerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
