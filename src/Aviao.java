public class Aviao extends Aeronave{
    private final int codigoAviao;
    private final String nome;
    private final int qtdAssentos;
    private boolean disponibilidade = true;

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Aviao(int codigoAviao, String nome, int qtdAssentos, String modelo) {
        super(modelo);
        this.codigoAviao = codigoAviao;
        this.nome = nome;
        this.qtdAssentos = qtdAssentos;
        disponibilidade = true;
    }

    
}