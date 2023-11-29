package entities;
import java.util.Random;
import java.util.Scanner;
import enums.StatusVoo;

public class Voo {
    Scanner sc = new Scanner(System.in);
    private String origem;
    private String destino;
    private String horario; 
    private Aviao aeronave;
    private String numeroVoo;
    private int assentosDisponiveis;
    private StatusVoo status;

    public Aviao getAeronave() {
        return aeronave;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public String getHorario() {
        return horario;
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigem() {
        return origem;
    }

    public StatusVoo getStatus() {
        return status;
    }

    public void setStatus(StatusVoo status) {
        this.status = status;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void decrementarAssentos() {
        assentosDisponiveis --;
    }

    public Voo(String origem, String destino, String horario, Aviao aeronave) throws Exception {
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.aeronave = aeronave;
        assentosDisponiveis = aeronave.getQtdAssentos();
        numeroVoo = gerarNumeroVoo();
        status = StatusVoo.DESCONFIRMADO;
    }

    private String gerarNumeroVoo() {
        Random aleatorio = new Random();
        StringBuilder geradorString = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int digit = aleatorio.nextInt(10);
            geradorString.append(digit);
        }

        String codigoVoo = geradorString.toString();
        return codigoVoo;
    }

    @Override
    public String toString() {
        return "VOO -> Origem = " + origem + ", Destino = " + destino + ", Horario = " + horario + " Número do Voo = [ "
                + numeroVoo + " ]";
    }
}
