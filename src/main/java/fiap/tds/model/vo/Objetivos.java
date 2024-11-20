package fiap.tds.model.vo;

public class Objetivos {
    private int id_objetivo;
    private int id_cliente;
    private double meta_agua;
    private double meta_luz;


    public Objetivos(){}

    public Objetivos(int id_cliente, int id_objetivo, double meta_agua, double meta_luz) {
        this.id_cliente = id_cliente;
        this.id_objetivo = id_objetivo;
        this.meta_agua = meta_agua;
        this.meta_luz = meta_luz;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_objetivo() {
        return id_objetivo;
    }

    public void setId_objetivo(int id_objetivo) {
        this.id_objetivo = id_objetivo;
    }

    public double getMeta_agua() {
        return meta_agua;
    }

    public void setMeta_agua(double meta_agua) {
        this.meta_agua = meta_agua;
    }

    public double getMeta_luz() {
        return meta_luz;
    }

    public void setMeta_luz(double meta_luz) {
        this.meta_luz = meta_luz;
    }
}
