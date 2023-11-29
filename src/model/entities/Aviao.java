package entities;
import interfaces.Aeronave;

public class Aviao extends Aeronave {
    private boolean disponibilidade = true;

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Aviao(int codigoAviao, String nome, int qtdAssentos) {
        super(nome, qtdAssentos, codigoAviao);
        disponibilidade = true;
    }

    @Override
    public String toString() {
        return "Aviao [ Código = " + getCodigoAviao() + ", Nome = " + getNome() + ", Quantidade de Assentos = "
                + getQtdAssentos() + " aviaoDisponivel =  " + disponibilidade + " ]";
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }
}