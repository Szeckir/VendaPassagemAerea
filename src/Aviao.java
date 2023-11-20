import java.util.ArrayList;
import java.util.Scanner;

public class Aviao extends Aeronave {
    private Scanner sc = new Scanner(System.in);
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
        this.codigoAviao = cadastroCodAviao(codigoAviao);
        this.nome = nome;
        this.qtdAssentos = qtdAssentos;
        disponibilidade = true;
        avioes.add(this);
    }

    private boolean verificarCodigoAviao(int codigoAviao) {
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).codigoAviao == codigoAviao) {
                return false;
            }
        }
        return true;
    }

    private int cadastroCodAviao(int codigoAviao) {
        if (avioes.isEmpty()) {
            return codigoAviao;
        }
        if (verificarCodigoAviao(codigoAviao) == true) {
            return codigoAviao;
        }
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).codigoAviao != codigoAviao) {
            }
        }

        int novoNumero;
        System.out.println("Digite um novo código: ");
        novoNumero = sc.nextInt();

        while (!verificarCodigoAviao(novoNumero)) {
            System.out.println("Código do Avião Indisponivel");
            System.out.println("Digite um novo código: ");
            novoNumero = sc.nextInt();
        }
        return novoNumero;
    }

    @Override
    public String toString() {
        return "Aviao [Codigo Aviao = " + codigoAviao + ", Nome Aviao = " + nome + ", Quantidade Assentos = " + qtdAssentos
                + ", Avião disponivel = " + disponibilidade + "]";
    }
}