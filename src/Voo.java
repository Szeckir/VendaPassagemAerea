import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Voo {
    private String origem;
    private String destino;
    private String horario; // 00:00 
    private Aviao aeronave;
    private String numeroVoo;
    private int assentosDisponiveis;
    private static ArrayList<Voo> voosDisponiveis = new ArrayList<>();
    private static ArrayList<Voo> voosCancelados = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static ArrayList<Voo> getVoosDisponiveis() {
        return voosDisponiveis;
    }

    public static ArrayList<Voo> getVoosCancelados() {
        return voosCancelados;
    }

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

    public Voo(String origem, String destino, String horario, Aviao aeronave) throws Exception {
        this.origem = origem;
        this.destino = destino;
        this.horario = verificaHorario(horario);
        this.aeronave = cadastroDisponibilidade(aeronave);
        assentosDisponiveis = aeronave.getQtdAssentos();
        numeroVoo = gerarNumeroVoo();
        voosDisponiveis.add(this);
    }    

    public boolean decretarAssentos() {
        if (assentosDisponiveis > 0) {
            assentosDisponiveis--;
            return true;
        }
        return false;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
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
        return "VOO -> Origem = " + origem + ", Destino = " + destino + ", Horario = " + horario + " Número do Voo = [ " + numeroVoo + " ]";
    }

    private boolean verificaDisponibilidadeAviao(Aviao aviao) {
        if(aviao == null) {
            return false;
        }
        if (aviao.isDisponibilidade() == true) {
            return true;
        }
        return false;
    }

    private Aviao cadastroDisponibilidade(Aviao aeronave) {
        if (Aviao.getAvioes().isEmpty()) {
            return aeronave;
        }
        if (verificaDisponibilidadeAviao(aeronave) == true) {
            return aeronave;
        }

        int novoNumero = 0;
        Aviao novoAviao = null;

        for(int i = 0; i < Aviao.getAvioes().size(); i++) {
            if (Aviao.getAvioes().get(i).getCodigoAviao() == novoNumero) {
                novoAviao = Aviao.getAvioes().get(i);
            }
        }

        while (!verificaDisponibilidadeAviao(novoAviao)) {
            System.out.println("Aviao Indisponivel");
            System.out.println("Aeronave Indisponivel, digite um novo codigo de aviao: ");
            novoNumero = sc.nextInt();

             for(int i = 0; i < Aviao.getAvioes().size(); i++) {
            if (Aviao.getAvioes().get(i).getCodigoAviao() == novoNumero) {
                novoAviao = Aviao.getAvioes().get(i);
            }
        }
        }
        return novoAviao;
    }

    public void confirmarVoo() {
        if (aeronave.isDisponibilidade() == false) {
            System.out.println("Avião já confirmado em outro voo");
            cancelarVoo(this.numeroVoo);
        } else {
            aeronave.setDisponibilidade(false);
            System.out.println("VOO: [ " + numeroVoo + " ] Confirmado");
        } 
    }

    public void cancelarVoo(String numeroVoo) {
        for(int i = 0; i < voosDisponiveis.size(); i++) {
            if (voosDisponiveis.get(i).numeroVoo == numeroVoo) {
                voosCancelados.add(voosDisponiveis.get(i));
                System.out.println("Voo: " + voosDisponiveis.get(i).numeroVoo + " - CANCELADO");
                voosDisponiveis.remove(i);
                return;
            }
        }
        System.out.println("O voo não existe");
    }

    private boolean verificaHorarioAux(String time) {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        return time.matches(regex);
    }

    private String verificaHorario (String time){
        if(verificaHorarioAux(time) == false){
            //return; //fazer uma exception

        }
        return time;
    }

}