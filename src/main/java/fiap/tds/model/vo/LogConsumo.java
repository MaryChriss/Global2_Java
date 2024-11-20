package fiap.tds.model.vo;

public class LogConsumo {
    private int id_log;
    private int id_relatorio;
    private Relatorio relatorio;
    private int id_aparelho;
    private Aparelhos aparelhos;
    private float consumo_real;

    public LogConsumo() {
    }

    public LogConsumo(Aparelhos aparelhos, float consumo_real, int id_aparelho, int id_log, int id_relatorio, Relatorio relatorio) {
        this.aparelhos = aparelhos;
        this.consumo_real = consumo_real;
        this.id_aparelho = id_aparelho;
        this.id_log = id_log;
        this.id_relatorio = id_relatorio;
        this.relatorio = relatorio;
    }

    public Aparelhos getAparelhos() {
        return aparelhos;
    }

    public void setAparelhos(Aparelhos aparelhos) {
        this.aparelhos = aparelhos;
    }

    public float getConsumo_real() {
        return consumo_real;
    }

    public void setConsumo_real(float consumo_real) {
        this.consumo_real = consumo_real;
    }

    public int getId_aparelho() {
        return id_aparelho;
    }

    public void setId_aparelho(int id_aparelho) {
        this.id_aparelho = id_aparelho;
    }

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public int getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(int id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }
}
