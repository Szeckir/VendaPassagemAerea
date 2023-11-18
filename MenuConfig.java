import java.util.Scanner;

public class MenuConfig {
    Scanner sc = new Scanner(System.in);

    public void run() throws Exception {
        menuOpcoes();
    }

    // =============================== MENU OPCOES
    public void menuOpcoes() throws Exception {
        System.out.println("Seja Bem-Vindo ao Sistema de Venda de Passagens Aéreas");
        System.out.println("");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Parâmetros do sistema");
        System.out.println(" 2 - Venda de Passagens");
        System.out.println(" 3 - Relatórios");
        System.out.println(" 4 - Sair");

        // crie o laco case para as opcoes abaixo
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                parametrosSistema();
                break;
            case 2:
                vendaPassagens();
                break;

            case 3:
                relatorios();
                break;
            default:
                break;
        }

    }

    // =============================== PARAMETROS SISTEMA
    private void parametrosSistema() throws Exception {
        System.out.println("========== Parâmetros do Sistema ==========");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Cadastrar Avião");
        System.out.println(" 2 - Cadastrar Voo");
        System.out.println(" 3 - Cadastrar Cliente");
        System.out.println(" 4 - Voltar");
        System.out.println("===========================================");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                cadastroAviao();
                break;
            case 2:
                cadastrarVoo();
                break;
            case 3:
                cadastroCliente();
                break;
            case 4:
                menuOpcoes();
                break;
            default:
                break;
        }
    }

    private void cadastroAviao() throws Exception {
        System.out.println("Digite o código do avião: ");
        int numeroAviao = sc.nextInt();
        System.out.println("Digite o nome do avião: ");
        String modeloAviao = sc.next();
        System.out.println("Digite a quantidade de assentos do avião: ");
        int assentosAviao = sc.nextInt();
        System.out.println("Digite o modelo do avião: ");
        String modelo = sc.next();

        new Aviao(numeroAviao, modeloAviao, assentosAviao, modelo);
        System.out.println("Avião cadastrado com sucesso!");
        menuOpcoes();
    }

    private void cadastrarVoo() throws Exception {
        System.out.println("========== Cadastro de Voo ==========");
        System.out.println("Digite o código do avião: ");
        int numeroAviao = sc.nextInt();
        Aviao aviao = null;
        for (int i = 0; i < Aviao.getAvioes().size(); i++) {
            if (Aviao.getAvioes().get(i).getCodigoAviao() == numeroAviao) {
                aviao = Aviao.getAvioes().get(i);
                break;
            }
        }
        if (aviao == null) {
            System.out.println("Avião não encontrado");
            menuOpcoes();
        }

        System.out.println("Digite a origem do voo: ");
        String origem = sc.next();
        System.out.println("Digite o destino do voo: ");
        String destino = sc.next();
        System.out.println("Digite o horário do voo: [HH:MM]");
        String horario = sc.next();

        new Voo(origem, destino, horario, aviao);
        System.out.println(" ");
        System.out.println("Voo cadastrado com sucesso!");
        System.out.println("====================================");
        menuOpcoes();
    }

    public void cadastroCliente() throws Exception {
        System.out.println("========== Cadastro de Cliente ==========");
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.next();
        System.out.println("Digite o telefone do cliente: ");
        String telefone = sc.next();

        new Cliente(nome, cpf, telefone);
        System.out.println("Cliente cadastrado com sucesso!");
        menuOpcoes();
    }

    // =============================== VENDA DE PASSAGENS

    public void vendaPassagens() throws Exception {
        System.out.println("========== Venda de Passagens ==========");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Comprar Passagem");
        System.out.println(" 2 - Cancelar Passagem");
        System.out.println(" 3 - Voltar");
        System.out.println("========================================");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                comprarPassagem();
                break;
            case 2:

                break;
            case 3:
                menuOpcoes();
                break;
            default:
                break;
        }
    }

    public void comprarPassagem() throws Exception {
        System.out.println("========== Comprar Passagem ==========");
        System.out.println("1 - Listar Voos");
        System.out.println("2 - Comprar Passagem");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
                    System.out.println(Voo.getVoosDisponiveis().get(i));
                }
                comprarPassagem();
                break;

            case 2:
                finalizarCompra();
                break;
            default:
                break;
        }
    }

    public void finalizarCompra() throws Exception {

        if (Voo.getVoosDisponiveis().isEmpty()) {
            System.out.println("Não foi possivel, nenhum voo disponível.");
            menuOpcoes();
        } else if (Cliente.getClientes().isEmpty()) {
            System.out.println("Não foi possivel, nenhum cliente cadastrado.");
            menuOpcoes();
        }

        System.out.println("Digite o número do voo: ");
        String numeroVoo = sc.next();
        System.out.println("Digite o seu CPF: ");
        String cpfCliente = sc.next();

        Voo voo = null;
        for(int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getNumeroVoo().equals(numeroVoo)) {
                voo = Voo.getVoosDisponiveis().get(i);
            }
        }

        Cliente cliente = null;
        for(int i = 0; i < Cliente.getClientes().size(); i++) {
            if (Cliente.getClientes().get(i).getCpf().equals(cpfCliente)) {
                cliente = Cliente.getClientes().get(i);
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não existe.");
            menuOpcoes();
        }

        if(voo == null) {
            System.out.println("Voo não existe. ");
            menuOpcoes();
        }
        
        new Venda(cliente, voo);
        menuOpcoes();
    }

    // ================================================== RELATORIOS
    private void relatorios() throws Exception {
        System.out.println("========== Relatórios ==========");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Relatório de Vendas");
        System.out.println(" 2 - Relatório de Todos Voos");
        System.out.println(" 3 - Relatório de Clientes");
        System.out.println(" 4 - Voltar");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                relatorioVendas();
                break;
            case 2:
                relatorioVoos();
                break;
            case 3:
                relatorioClientes();
                break;
            case 4:
                menuOpcoes();
                break;
            default:
                break;
        }
    }

    private void relatorioVendas() throws Exception {
        System.out.println("========== Relatório de Vendas ==========");
        if (Venda.getVendasRealizadas().isEmpty()) {
            System.out.println("Nenhuma venda realizada");
            menuOpcoes();
        }

        for (int i = 0; i < Venda.getVendasRealizadas().size(); i++) {
            System.out.println(Venda.getVendasRealizadas().get(i));

        }
         if (Venda.getVendasCanceladas().isEmpty()) {
            System.out.println("Nenhuma venda cancelada");
            menuOpcoes();
        }
        for (int i = 0; i < Venda.getVendasCanceladas().size(); i++) {
            
        }
        menuOpcoes();
    }

    private void relatorioVoos() throws Exception {
        System.out.println("=====================================");
        if (Voo.getVoosDisponiveis().isEmpty()) {
            System.out.println("Nenhum VOO disponivel.");
            menuOpcoes();
        }

        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getAeronave().isDisponibilidade() == false) {
                System.out.println(Voo.getVoosDisponiveis().get(i) + " - CONFIRMADO");
            } else {
                System.out.println(Voo.getVoosDisponiveis().get(i) + " - NÃO CONFIRMADO");
            }
        }

        for (int i = 0; i < Voo.getVoosCancelados().size(); i++) {
            System.out.println(Voo.getVoosCancelados().get(i) + " - CANCELADO");
        }
        System.out.println("=====================================");
        menuOpcoes();
    }

    private void relatorioClientes() throws Exception {
        System.out.println("=====================================");
        if (Cliente.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            menuOpcoes();
        }

        for (int i = 0; i < Cliente.getClientes().size(); i++) {
            System.out.println(Cliente.getClientes().get(i));
        }

        System.out.println("=====================================");
        menuOpcoes();
    }

}
