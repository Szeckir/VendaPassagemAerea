public class Voo {
    private String origem;
    private String destino;
    private String horario; // 00:00 
    private Aviao aeronave;


    public Voo(String origem, String destino, String horario, Aviao aeronave) throws Exception {
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.aeronave = disponibilidadeAviao(aeronave);
    }

    public Aviao disponibilidadeAviao(Aviao aeronave) throws Exception {
        if (aeronave.isDisponibilidade() == false) {
            throw new Exception("Aviao Indisponivel");
        } else {
            aeronave.setDisponibilidade(false);
            return aeronave;
        }
    }

    

    
}
