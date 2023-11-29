package actions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.Action;
import entities.Aviao;
import entities.Cliente;
import entities.Venda;
import entities.Voo;
import enums.StatusVoo;

public class Aeroporto {
    private ArrayList<Aviao> avioes;
    private ArrayList<Voo> voos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venda> vendasAtivas;
    private ArrayList<Venda> vendasCanceladas;
    private Scanner sc = new Scanner(System.in);

    public Aeroporto() {
        this.avioes = new ArrayList<Aviao>();
        this.voos = new ArrayList<Voo>();
        this.clientes = new ArrayList<Cliente>();
    }

    public void run() throws Exception {
        menu();
    }

    public ArrayList<Aviao> getAvioes() {
        return avioes;
    }

    public ArrayList<Voo> getVoos() {
        return voos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

   private void menu() {
    Scanner sc = new Scanner(System.in);
    boolean continuar = true;
    while (continuar) {
        try {
            System.out.println("Seja Bem-Vindo ao Aeroporto Internacional");
            System.out.println("");
            System.out.println("Escolha uma das opções abaixo: ");
            System.out.println(" 1 - Parâmetros do sistema");
            System.out.println(" 2 - Parâmetros do cliente");
            System.out.println(" 3 - Parâmetros do voo");
            System.out.println(" 4 - Relatórios");
            System.out.println(" 5 - Sair");

            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    menuParametrosSistema();
                    break;

                case 2:
                menuCliente();
                    
                    break;

                case 3:
                    menuParametrosVoos();
                    break;

                case 4:
                    menuRelatorios();
                    break;

                default:
                    System.out.println("Certeza que deseja sair? (S/N)");
                    String resposta = sc.next();
                    if (resposta.equalsIgnoreCase("S")) {
                        continuar = false;
                    } else {
                        System.out.println("Voltando ao menu principal...");
                    }
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: entrada inválida. Por favor, insira um número.");
            sc.nextLine(); 
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}


private void menuParametrosSistema() {
    boolean continuar = true;

    while (continuar) {
        System.out.println("=========== MENU DE PARÂMETROS DO SISTEMA ===========");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Cadastrar Avião");
        System.out.println(" 2 - Cadastrar Voo");
        System.out.println(" 3 - Cadastrar Cliente");
        System.out.println(" 4 - Voltar");

        try {
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    cadastrarAviao();
                    break;
                case 2:
                    cadastrarVoo();
                    break;
                case 3:
                    cadastrarCliente();
                    break;
                case 4:
                    menu();
                    continuar = false; // Sai do loop
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } catch (InputMismatchException ime) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            sc.nextLine(); // Limpa o buffer do scanner
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace(); // Para propósitos de depuração
        }
    }
}


    private void cadastrarAviao() throws Exception {

        System.out.println("Digite o código do avião: ");
        int codigoAviao = sc.nextInt();
        if (verificarCodigoAviao(codigoAviao) == false) {
            System.out.println("Código inválido");
            menuParametrosSistema();
        }

        System.out.println("Digite o nome do Aviao: ");
        String nome = sc.next();
        if (validarNome(nome) == false) {
            System.out.println("Nome inválido");
            menuParametrosSistema();
        }

        System.out.println("Digite a quantidade de assentos: ");
        int qtdAssentos = sc.nextInt();
        if (auxiliarQtdAssentos(qtdAssentos) == false) {
            System.out.println("Quantidade de assentos inválido");
            menuParametrosSistema();
        }

        avioes.add(new Aviao(codigoAviao, nome, qtdAssentos));
        System.out.println("Avião cadastrado com sucesso!");
    }

    private void cadastrarVoo() throws Exception {
        System.out.println("Digite o código do avião: ");
        int codigoAviao = sc.nextInt();
        if (verificarDisponibilidade(codigoAviao) == false) {
            System.out.println("Avião indisponível");
            menuParametrosSistema();
        }
        System.out.println("Origem do voo: ");
        String origem = sc.next();
        if (validarNome(origem) == false) {
            System.out.println("Origem inválida");
            menuParametrosSistema();
        }
        System.out.println("Destino do voo: ");
        String destino = sc.next();
        if (validarNome(destino) == false) {
            System.out.println("Destino inválido");
            menuParametrosSistema();
        }
        System.out.println("Horário do voo: [HH/MM]");
        String horario = sc.next();
        if (verificaHorario(horario) == false) {
            System.out.println("Horário inválido");
            menuParametrosSistema();
        }

        voos.add(new Voo(origem, destino, horario, buscarAviao(codigoAviao)));
        System.out.println("Voo cadastrado com sucesso!");
    }

    private void cadastrarCliente() throws Exception {
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.next();
        if (validarNome(nome) == false) {
            System.out.println("Nome inválido");
            menuParametrosSistema();
        }
        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.next();
        cpf = adicionarCPF(cpf);
        System.out.println("Digite o telefone do cliente: ");
        String telefone = sc.next();
        if (validarTelefone(telefone) == false) {
            System.out.println("Telefone inválido");
            menuParametrosSistema();
        }

        clientes.add(new Cliente(nome, cpf, telefone));
        System.out.println("Cliente cadastrado com sucesso!");
    }

   public void menuParametrosVoos() {
    boolean continuar = true;

    while (continuar) {
        System.out.println("=========== MENU DE VOOS ===========");
        System.out.println(" 1 - Cancelar VOO");
        System.out.println(" 2 - Confirmar VOO");
        System.out.println(" 3 - Desconfirmar VOO");
        System.out.println(" 4 - Listar VOOS");
        System.out.println(" 5 - Voltar");

        try {
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    cancelarVoo();
                    break;
                case 2:
                    confirmarVoo();
                    break;
                case 3:
                    desconfirmarVoo();
                    break;
                case 4:
                    if (voos.isEmpty()) {
                        System.out.println("Não há voos cadastrados");
                    } else {
                        listarVoos();
                    }
                    break;
                case 5:
                    menu();
                    continuar = false; // Sai do loop
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } catch (InputMismatchException ime) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            sc.nextLine(); // Limpa o buffer do scanner
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace(); // Para propósitos de depuração
        }
    }
}


    private void cancelarVoo() {
        System.out.println("Digite o código do voo: ");
        String codigoVoo = sc.next();
        if (verificarCodigoVoo(codigoVoo) == false) {
            System.out.println("Código inválido");
            menuParametrosVoos();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CANCELADO) {
            System.out.println("Voo já cancelado");
            menuParametrosVoos();
        }

        buscarVoo(codigoVoo).setStatus(StatusVoo.CANCELADO);
        System.out.println("Voo cancelado com sucesso!");
    }

    private void confirmarVoo() {
        System.out.println("Digite o código do voo: ");
        String codigoVoo = sc.next();
        if (verificarCodigoVoo(codigoVoo) == false) {
            System.out.println("Código inválido");
            menuParametrosVoos();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CONFIRMADO) {
            System.out.println("Voo já confirmado");
            menuParametrosVoos();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CANCELADO) {
            System.out.println("Voo cancelado");
            menuParametrosVoos();
        }

        buscarVoo(codigoVoo).setStatus(StatusVoo.CONFIRMADO);
        System.out.println("Voo confirmado com sucesso!");
    }

    private void desconfirmarVoo() {
        System.out.println("Digite o código do voo: ");
        String codigoVoo = sc.next();
        if (verificarCodigoVoo(codigoVoo) == false) {
            System.out.println("Código inválido");
            menuParametrosVoos();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.DESCONFIRMADO) {
            System.out.println("Voo já desconfirmado");
            menuParametrosVoos();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CANCELADO) {
            System.out.println("Voo cancelado");
            menuParametrosVoos();
        }

        buscarVoo(codigoVoo).setStatus(StatusVoo.DESCONFIRMADO);
        System.out.println("Voo desconfirmado com sucesso!");
    }

    private void listarVoos() {
        for (int i = 0; i < voos.size(); i++) {
            System.out.println(voos.get(i).toString());
        }
    }

    private void menuRelatorios() {
        boolean continuar = true;
    
        while (continuar) {
            System.out.println("=========== MENU DE RELATÓRIOS ===========");
            System.out.println(" 1 - Listar Aviões");
            System.out.println(" 2 - Listar Clientes");
            System.out.println(" 3 - Listar Vendas");
            System.out.println(" 4 - Listar VOOS");
            System.out.println(" 5 - Voltar");
    
            try {
                int escolha = sc.nextInt();
    
                switch (escolha) {
                    case 1:
                        if (avioes.isEmpty()) {
                            System.out.println("Não há aviões cadastrados");
                        } else {
                            listarAvioes();
                        }
                        break;
                    case 2:
                        if (clientes.isEmpty()) {
                            System.out.println("Não há clientes cadastrados");
                        } else {
                            listarClientes();
                        }
                        break;
                    case 3:
                        if (voos.isEmpty()) {
                            System.out.println("Não há vendas cadastradas");
                        } else {
                            listarVendas();
                        }
                        break;
                    case 4:
                        if (voos.isEmpty()) {
                            System.out.println("Não há voos cadastrados");
                        } else {
                            listarVoos();
                        }
                        break;
                    case 5:
                        menu();
                        continuar = false; // Sai do loop
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.nextLine(); // Limpa o buffer do scanner
            }
        }
    }
    

    private void listarAvioes() {
        for (int i = 0; i < avioes.size(); i++) {
            System.out.println(avioes.get(i).toString());
        }
    }

    private void listarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).toString());
        }
    }

    private void listarVendas() {
        for (int i = 0; i < voos.size(); i++) {
            System.out.println(voos.get(i).toString());
        }
    }

    private void menuCliente() {
        boolean continuar = true;
    
        while (continuar) {
            System.out.println("=========== MENU DE CLIENTES ===========");
            System.out.println(" 1 - Comprar Passagem");
            System.out.println(" 2 - Cancelar Passagem");
            System.out.println(" 3 - Listar Voos");
            System.out.println(" 4 - Voltar");
    
            try {
                int escolha = sc.nextInt();
    
                switch (escolha) {
                    case 1:
                        comprarPassagem();
                        break;
                    case 2:
                        cancelarPassagem();
                        break;
                    case 3:
                        listarVoos();
                        break;
                    case 4:
                        menu();
                        continuar = false; // Sai do loop
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.nextLine(); // Limpa o buffer do scanner
            }
        }
    }
    

    private void comprarPassagem() {
        System.out.println("Digite o código do voo: ");
        String codigoVoo = sc.next();
        if (verificarCodigoVoo(codigoVoo) == false) {
            System.out.println("Código inválido");
            menuCliente();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CANCELADO) {
            System.out.println("Voo cancelado");
            menuCliente();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.DESCONFIRMADO) {
            System.out.println("Voo desconfirmado");
            menuCliente();
        } else if (buscarVoo(codigoVoo).getAssentosDisponiveis() < 0) {
            System.out.println("Não há assentos disponíveis");
            menuCliente();
        }

        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.next();
        if (clienteExiste(cpf) == false) {
            System.out.println("Cliente não existe");
            menuCliente();
        }

        buscarVoo(codigoVoo).decrementarAssentos();
        vendasAtivas.add(new Venda(buscarCliente(cpf), buscarVoo(codigoVoo)));
        System.out.println("Passagem comprada com sucesso!");
    }

    private void cancelarPassagem() {
        System.out.println("Digite o código do voo: ");
        String codigoVoo = sc.next();
        if (verificarCodigoVoo(codigoVoo) == false) {
            System.out.println("Código inválido");
            menuCliente();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.CANCELADO) {
            System.out.println("Voo cancelado");
            menuCliente();
        } else if (buscarVoo(codigoVoo).getStatus() == StatusVoo.DESCONFIRMADO) {
            System.out.println("Voo desconfirmado");
            menuCliente();
        }

        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.next();
        if (clienteExiste(cpf) == false) {
            System.out.println("Cliente não existe");
            menuCliente();
        }

        for (int i = 0; i < vendasAtivas.size(); i++) {
            if (vendasAtivas.get(i).getCliente().getCpf().equals(cpf)
                    && vendasAtivas.get(i).getVoo().getNumeroVoo().equals(codigoVoo)) {
                vendasCanceladas.add(vendasAtivas.get(i));
                vendasAtivas.remove(i);
                buscarVoo(codigoVoo).decrementarAssentos();
                System.out.println("Passagem cancelada com sucesso!");
                menuCliente();
            }
        }

        buscarVoo(codigoVoo).decrementarAssentos();
        vendasAtivas.add(new Venda(buscarCliente(cpf), buscarVoo(codigoVoo)));
        System.out.println("Passagem comprada com sucesso!");
    }

    // =========================================================================

    private Cliente buscarCliente(String cpf) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    private boolean clienteExiste(String cpf) {
        if (clientes.isEmpty()) {
            return false;
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private Voo buscarVoo(String codigoVoo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo().equals(codigoVoo)) {
                return voos.get(i);
            }
        }
        return null;
    }

    private boolean verificarCodigoVoo(String codigoVoo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo().equals(codigoVoo)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarCodigoAviao(int codigoAviao) {
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).getCodigoAviao() == codigoAviao) {
                return false;
            }
        }
        return true;
    }

    private boolean auxiliarQtdAssentos(int qtdAssentos) {
        if (qtdAssentos > 0) {
            return true;
        }
        return false;
    }

    private boolean vericarValidadeCodigoAviao(int codigoAviao) {
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).getCodigoAviao() == codigoAviao) {
                return true;
            }
        }
        return false;
    }

    private Aviao buscarAviao(int codigoAviao) {
        for (int i = 0; i < avioes.size(); i++) {
            if (avioes.get(i).getCodigoAviao() == codigoAviao) {
                return avioes.get(i);
            }
        }
        return null;
    }

    private boolean verificarDisponibilidade(int codigoAviao) {
        if (vericarValidadeCodigoAviao(codigoAviao) == true) {
            if (buscarAviao(codigoAviao).getDisponibilidade() == true) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaHorario(String horario) {
        if (horario.length() == 5) {
            if (horario.charAt(2) == ':') {
                return true;
            }
        }
        return false;
    }

    private boolean validarNome(String nome) {
        for (int i = 0; i < nome.length(); i++) {
            if (Character.isLetter(nome.charAt(i)) == false) {
                return false;
            }
        }

        if (nome.length() > 0) {
            return true;
        }
        return false;
    }

    private String adicionarCPF(String cpf) throws Exception {
        if (clientes.isEmpty()) {
            return cpf;
        }

        if (validarCPF(cpf) == true) {
            if (CPFDuplicado(cpf) == false) {
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

    private boolean validarTelefone(String telefone) {
        if (telefone.length() > 14 && telefone.length() < 8) {
            return false;
        }
        return true;
    }
}
