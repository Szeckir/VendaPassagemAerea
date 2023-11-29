package interfaces;

public abstract class Aeronave {
    private String nome;
    private int qtdAssentos;
    private int codigoAviao;

    public Aeronave(String nome, int qtdAssentos, int codigoAviao) {
        this.nome = nome;
        this.qtdAssentos = qtdAssentos;
        this.codigoAviao = codigoAviao;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public int getCodigoAviao() {
        return codigoAviao;
    }

    
}
