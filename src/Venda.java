import java.time.LocalDateTime;

public class Venda {
    private LocalDateTime horarioCompra;
    private Cliente cliente;
    private Voo voo;

    

    public Venda (){
        this.horarioCompra = obterHorarioAtual();
    }

    public static LocalDateTime obterHorarioAtual() {
        return LocalDateTime.now();
    }


}