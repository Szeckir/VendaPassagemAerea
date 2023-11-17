public class Main {
    public static void main(String[] args) throws Exception {
        Aviao aviao1 = new Aviao(1234, "Airbus", 70, "Boeing-737");
        Aviao aviao2 = new Aviao(567, "A380", 70, "Boeing-737");
        Aviao aviao3 = new Aviao(8910, "Airbus", 30, "Boeing-130");

        Voo voo1 = new Voo("Porto Alegre", "Brasilia", "12:30", aviao1);
        Voo voo2 = new Voo("Porto Alegre", "Maceio", "12:38", aviao2);
        Voo voo3 = new Voo("Maceio", "Miami", "12:38", aviao3);

        Cliente cliente1 = new Cliente("Thomaz Szeckir", "86735934091", "51999502004");
        Cliente cliente2 = new Cliente("João da Silva", "87164329089", "51999602204");
        Cliente cliente3 = new Cliente("Maria da Silva", "23435144467", "51999602044");

        Venda venda1 = new Venda(cliente1, voo1);
        Venda venda2 = new Venda(cliente2, voo2);
        Venda venda3 = new Venda(cliente3, voo3);

        voo1.confirmarVoo();
        voo2.confirmarVoo();
        voo2.cancelarVoo(voo2.getNumeroVoo());

        MenuConfig menu = new MenuConfig();
        menu.run();
    }
}