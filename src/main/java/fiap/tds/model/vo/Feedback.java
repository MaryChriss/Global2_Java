package fiap.tds.model.vo;

public class Feedback {
    private int id_feedback;
    private int id_cliente;
    private Cliente cliente;
    private String comentario;
    private int avaliacao;

    public Feedback() {}

    public Feedback(int avaliacao, Cliente cliente, String comentario, int id_feedback) {
        this.avaliacao = avaliacao;
        this.cliente = cliente;
        this.comentario = comentario;
        this.id_feedback = id_feedback;
    }

    public Feedback(int id_feedback, String comentario, int avaliacao) {
        this.id_feedback = id_feedback;
        this.comentario = comentario;
        this.avaliacao = avaliacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }
}
