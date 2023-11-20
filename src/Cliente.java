import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private final String nome;
    private final String cpf;
    private String telefone;
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Cliente(String nome, String cpf, String telefone) throws Exception {
        this.nome = nome;
        this.cpf = adicionarCPF(cpf);
        this.telefone = validarTelefone(telefone);
        clientes.add(this);
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public String toString() {
        return "Cliente [ Nome = " + nome + ", CPF = " + cpf + ", Telefone = " + telefone + " ]";
    }

    public String getNome() {
        return nome;
    }

    private String validarTelefone(String telefone) throws Exception {
        if (telefone.length() > 14) {
            throw new Exception("Número de digitos excedidos");
        }
        return telefone;
    }

    private String adicionarCPF(String cpf) throws Exception {

        if(clientes.isEmpty()) {
            return cpf;
        }
        
        if (validarCPF(cpf) == true) {
            if(CPFDuplicado(cpf) == false) {
                return cpf;
            }
        }

        while (!validarCPF(cpf) || CPFDuplicado(cpf)) {
            System.out.println("CPF inválido ou já cadastrado!");
            System.out.println("Digite novamente o CPF: ");
            cpf = sc.nextLine();
        }
        return cpf;
    }

    private boolean CPFDuplicado(String cpf) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean validarCPF(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            cpf.length() != 11) {
            return false;
        }
        return true;
        
    }
}
