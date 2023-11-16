import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Testes {
        
    @Test // Disponibildiade do aviao apos criá-lo
    public void criacaoAviaoDisponibilidadeTrue() {
        Aviao aviao1 = new Aviao(123, "Aviao 1", 50, "Boeing");

        boolean actual = aviao1.isDisponibilidade();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test // Verificar disponibilidade apos adicionar um aviao em um voo
    public void aviaoDispobilidadeAposVoo() throws Exception {
        Aviao aviao1 = new Aviao(123, "Aviao 1", 50, "Boeing");
        Voo voo1 = new Voo("Porto Alegre", "Brasilia", "12:35", aviao1);
        voo1.confirmarVoo();

        boolean actual = aviao1.isDisponibilidade();
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test // Validar criacao de cliente
    public void criacaoCliente() throws Exception {
        Cliente cliente1 = new Cliente("Thomaz Szeckir", "86735934091", "5551999602004");
    }

    @Test
    public void voosPendentes() throws Exception {
        
    }
    

}
