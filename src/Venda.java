import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {
    private String horarioCompra;
    private Cliente cliente;
    private Voo voo;
    private static ArrayList<Venda> vendasRealizadas = new ArrayList<>();
    private static ArrayList<Venda> vendasCanceladas= new ArrayList<>();

    
    
    public static ArrayList<Venda> getVendasCanceladas(){
        return vendasCanceladas;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public Voo getVoo() {
        return voo;
    }


    public static ArrayList<Venda> getVendasRealizadas() {
        return vendasRealizadas;
    }

    public Venda(Cliente cliente, Voo voo) {
        this.cliente = cliente;
        this.voo = assentosDisponiveis(voo);
        this.horarioCompra = obterHorarioAtual();
        imprimirVenda();
        vendasRealizadas.add(this);
        //voo.decretarAssentos();
    }

    
    @Override
    public String toString() {
        System.out.println("Dados cliente: " + " Nome = [" + cliente.getNome() + "], CPF = [ " + cliente.getCpf() + "]");
        System.out.println("Dados Voo: " + " Numero = [" + voo.getNumeroVoo() + "], Origem = [ " + voo.getOrigem() + " ], Destino = [" + voo.getDestino() + "], Horario = [" + voo.getHorario() + "]");
        System.out.println("[Horario compra = [" + horarioCompra + "]");
        return "==============================";
    }


    private Voo assentosDisponiveis(Voo voo) {
        if (voo.getAssentosDisponiveis() > 0) {
            voo.decretarAssentos();
            return voo;
        } else {
            System.out.println("Não possui assentos disponíveis");
            return null;
        }
    }

    public static String obterHorarioAtual() {
        // 2023-11-17T10:32:15.723462300
        String dataFormatada = LocalDateTime.now().toString().substring(0, 9);
        String horaFormatada = LocalDateTime.now().toString().substring(11, 19);
        String HorarioAtual = dataFormatada + " | " + horaFormatada;
        return HorarioAtual;
    }

    private void imprimirVenda() {
        System.out.println("========= VENDA CONFIRMADA ========== ");
        System.out.println("Voo Número: " + this.voo.getNumeroVoo());
        System.out.println("Origem: " + this.voo.getOrigem());
        System.out.println("Destino: " + this.voo.getDestino());
        System.out.println("Horario do Voo: " + this.voo.getHorario());
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("=====================================");
    }
}