public class Main {
    public static void main(String[] args) throws Exception {
        Aviao aviao1 = new Aviao(1234, "Airbus", 70, "Boeing-737");
        Aviao aviao2 = new Aviao(567, "A380", 70, "Boeing-737");
        Aviao aviao3 = new Aviao(8910, "Airbus", 30, "Boeing-130");

        Voo voo1 = new Voo("Porto Alegre", "Brasilia", "12:30", aviao1);
        Voo voo2 = new Voo("Porto Alegre", "Maceio", "12:38", aviao1);
        Voo voo3 = new Voo("Maceio", "Miami", "12:38", aviao2);

        voo1.confirmarVoo();
        voo3.confirmarVoo();

        Cliente cliente1 = new Cliente("Thomaz", "86735934091", "9219391");

        Venda venda1 = new Venda(cliente1, voo1);


    }
}