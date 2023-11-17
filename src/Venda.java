import java.time.LocalDateTime;

public class Venda {
    private LocalDateTime horarioCompra;
    private Cliente cliente;
    private Voo voo;

    public Venda(Cliente cliente, Voo voo) {
        this.cliente = cliente;
        this.voo = assentosDisponiveis(voo);
        this.horarioCompra = obterHorarioAtual();
        //voo.decretarAssentos();
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

    public static LocalDateTime obterHorarioAtual() {
        return LocalDateTime.now();
    }

}