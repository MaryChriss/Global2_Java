package fiap.tds.model.vo;

public class SugestoesEconomia {
    private int id_sugestao;
    private int id_cliente;
    private Cliente cliente;
    private String mensagem;

    public SugestoesEconomia() {
    }

    public SugestoesEconomia(Cliente cliente, int id_cliente, int id_sugestao, String mensagem) {
        this.cliente = cliente;
        this.id_cliente = id_cliente;
        this.id_sugestao = id_sugestao;
        this.mensagem = mensagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_sugestao() {
        return id_sugestao;
    }

    public void setId_sugestao(int id_sugestao) {
        this.id_sugestao = id_sugestao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}