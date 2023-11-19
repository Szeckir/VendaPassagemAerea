import java.util.ArrayList;

public class Aviao extends Aeronave{
    private final int codigoAviao;
    private final String nome;
    private final int qtdAssentos;
    private boolean disponibilidade = true;
    private static ArrayList<Aviao> avioes = new ArrayList<>();

    public static ArrayList<Aviao> getAvioes() {
        return avioes;
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public int getCodigoAviao() {
        return codigoAviao;
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
        avioes.add(this);
    }

    @Override
    public String toString() {
        return "Aviao [codigoAviao=" + codigoAviao + ", nome=" + nome + ", qtdAssentos=" + qtdAssentos
                + ", disponibilidade=" + disponibilidade +"]";
    }

    

}