package fiap.tds.model.vo;

public class Relatorio {
    private int id_relatorio;
    private String mes_referencia;
    private int quantidade_pessoas;
    private int id_endereco;
    private Endereco endereco;

    public Relatorio() {
    }

    public Relatorio(Endereco endereco, int id_endereco, int id_relatorio, String mes_referencia, int quantidade_pessoas) {
        this.endereco = endereco;
        this.id_endereco = id_endereco;
        this.id_relatorio = id_relatorio;
        this.mes_referencia = mes_referencia;
        this.quantidade_pessoas = quantidade_pessoas;
    }

    public Relatorio(int idRelatorio, String mesReferencia, int quantidadePessoas) {
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public int getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(int id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public String getMes_referencia() {
        return mes_referencia;
    }

    public void setMes_referencia(String mes_referencia) {
        this.mes_referencia = mes_referencia;
    }

    public int getQuantidade_pessoas() {
        return quantidade_pessoas;
    }

    public void setQuantidade_pessoas(int quantidade_pessoas) {
        this.quantidade_pessoas = quantidade_pessoas;
    }

}
