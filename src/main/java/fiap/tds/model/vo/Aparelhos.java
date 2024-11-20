package fiap.tds.model.vo;

public class Aparelhos {
    private int id_aparelho;
    public String nome_aparelho;
    public int consumo;

    public Aparelhos() {
    }

    public Aparelhos(int consumo, int id_aparelho, String nome_aparelho) {
        this.consumo = consumo;
        this.id_aparelho = id_aparelho;
        this.nome_aparelho = nome_aparelho;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getId_aparelho() {
        return id_aparelho;
    }

    public void setId_aparelho(int id_aparelho) {
        this.id_aparelho = id_aparelho;
    }

    public String getNome_aparelho() {
        return nome_aparelho;
    }

    public void setNome_aparelho(String nome_aparelho) {
        this.nome_aparelho = nome_aparelho;
    }
}
