import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Voo {
    private String origem;
    private String destino;
    private String horario; // 00:00 
    private Aviao aeronave;
    private String numeroVoo;
    private static ArrayList<Voo> voosDisponiveis = new ArrayList<>();
    private static ArrayList<Voo> voosCancelados = new ArrayList<>();


    public Voo(String origem, String destino, String horario, Aviao aeronave) throws Exception {
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.aeronave = disponibilidadeAviao(aeronave);
        numeroVoo = gerarNumeroVoo();
        System.out.println("VOO: [ " + numeroVoo + " ], Saindo de: " + origem + " com Destino a: " + destino + " criado.");
        voosDisponiveis.add(this);
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
        return "Voo [origem=" + origem + ", destino=" + destino + ", horario=" + horario + ", aeronave=" + aeronave
                + ", numeroVoo=" + numeroVoo + "]";
    }

    public Aviao disponibilidadeAviao(Aviao aeronave) throws Exception {
        if (aeronave.isDisponibilidade() == false) {
            throw new Exception("Aviao Indisponivel");
        } 
        return aeronave;
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

    public void listagemVoosDisponiveis() {
        if (voosDisponiveis.isEmpty()) {
            System.out.println("Nenhum VOO disponivel.");
            return;
        }
        
        for(int i =0; i < voosDisponiveis.size(); i++) {
            if (voosDisponiveis.get(i).aeronave.isDisponibilidade() == false) {
                System.out.println(voosDisponiveis.get(i) + " - CONFIRMADO");
            } else {
                System.out.println(voosDisponiveis.get(i) + " - NÃO CONFIRMADO");
            }
        }
    }

    public void listagemVoosCancelados() {
        if (voosCancelados.isEmpty()) {
            System.out.println("Nenhum VOO cancelado.");
            return;
        }

        for (int i = 0; i < voosCancelados.size(); i++) {
            System.out.println(voosCancelados.get(i) + " - CANCELADO");
        }
    }
    
}