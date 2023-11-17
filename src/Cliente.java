import java.util.ArrayList;

public class Cliente {
    private final String nome;
    private final String cpf;
    private String telefone;
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Cliente(String nome, String cpf, String telefone) throws Exception {
        this.nome = nome;
        this.cpf = adicionarCPF(cpf);
        this.telefone = validarTelefone(telefone);
        clientes.add(this);
    }

    

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
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

        if (validarCPF(cpf) == true) {
            if (clientes.isEmpty()) {
                clientes.add(this);
                return cpf;
        }

        for(int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).cpf == cpf) {
                throw new Exception("CPF já cadastrado");
            } 
        }
        clientes.add(this);
        return cpf;
     }
      throw new Exception("ERROR: Erro na adição do CPF");
    }

    private boolean validarCPF(String cpf) {
        if (cpf == "00000000000" || cpf == "11111111111" ||
                cpf == "22222222222" || cpf == "33333333333" ||
                cpf == "44444444444" || cpf == "55555555555" ||
                cpf == "66666666666" || cpf == "77777777777" ||
                cpf == "88888888888" || cpf == "99999999999" ||
                cpf.length() != 11) {

            System.out.println("CPF inválido");
            return false;
        }
        return true;
    }

}
