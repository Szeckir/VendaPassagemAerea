import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConfig {
    Scanner sc = new Scanner(System.in);

    public void run() throws Exception {
        menuOpcoes();
    }

    // =============================== MENU OPCOES
    private void menuOpcoes() {
        boolean continuar = true;
    
        while (continuar) {
            System.out.println("Seja Bem-Vindo ao Sistema de Venda de Passagens Aéreas");
            System.out.println("");
            System.out.println("Escolha uma das opções abaixo: ");
            System.out.println(" 1 - Parâmetros do sistema");
            System.out.println(" 2 - Parâmetros do cliente");
            System.out.println(" 3 - Relatórios");
            System.out.println(" 4 - Sair");
    
            int opcao = sc.nextInt();
    
            switch (opcao) {
                case 1:
                    try {
                        parametrosSistema();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Ocorreu um erro ao ajustar os parâmetros do sistema.");
                    }
                    break;
                case 2:
                    try {
                        parametrosCliente();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Ocorreu um erro ao ajustar os parâmetros do cliente.");
                    }
                    break;
                case 3:
                    try {
                        relatorios();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Ocorreu um erro ao gerar relatórios.");
                    }
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    
    // =============================== PARAMETROS SISTEMA
    private void parametrosSistema() throws Exception {
        boolean continuar = true;
    
        while (continuar) {
            System.out.println("========== Parâmetros do Sistema ==========");
            System.out.println("Escolha uma das opções abaixo: ");
            System.out.println(" 1 - Cadastrar Avião");
            System.out.println(" 2 - Cadastrar Voo");
            System.out.println(" 3 - Cadastrar Cliente");
            System.out.println(" 4 - Confirmar Voo");
            System.out.println(" 5 - Desconfirmar VOO");
            System.out.println(" 7 - Cancelar VOO");
            System.out.println(" 6 - Voltar");
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
                    menuConfirmarVoo();
                    break;
                case 5:
                    menuDesconfirmarVoo();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
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

    private void cadastrarVoo() {
    System.out.println("========== Cadastro de Voo ==========");
    try {
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
            return;
        }

        System.out.println("Digite a origem do voo: ");
        String origem = sc.next();
        sc.nextLine();

        System.out.println("Digite o destino do voo: ");
        String destino = sc.nextLine();

        System.out.println("Digite o horário do voo: [HH:MM]");
        String horario = sc.next();

        new Voo(origem, destino, horario, aviao);
        System.out.println("Voo cadastrado com sucesso!");

    } catch (InputMismatchException ime) {
        System.out.println("Erro de entrada. Por favor, insira os dados corretamente.");
        sc.nextLine(); 
    } catch (Exception e) {
        System.out.println("Ocorreu um erro ao cadastrar o voo: " + e.getMessage());
    } finally {
        System.out.println("====================================");
        menuOpcoes();
    }
}


private void cadastroCliente() throws Exception {
    
    System.out.println("========== Cadastro de Cliente ==========");
    
    System.out.println("Digite o nome do cliente: ");
    String nome = sc.next();
    
   
    while (contemNumeros(nome)) {
        System.out.println("Nome inválido. Por favor, digite um nome sem números: ");
        nome = sc.next(); 
    }
    sc.nextLine(); 

    System.out.println("Digite o CPF do cliente: ");
    String cpf = sc.nextLine();

    
    while (contemLetras(cpf)==false) {
        System.out.println("CPF inválido. Por favor, digite um CPF somente com números: ");
        cpf = sc.nextLine();
    }

    System.out.println("Digite o telefone do cliente: ");
    String telefone = sc.nextLine();

   
    while (contemLetras(telefone)==false) {
        System.out.println("Telefone inválido. Por favor, digite um telefone somente com números: ");
        telefone = sc.nextLine();
    }

    new Cliente(nome, cpf, telefone);
    System.out.println("Cliente cadastrado com sucesso!");
    menuOpcoes();
}
    private static boolean contemNumeros(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    private static boolean contemLetras(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private void menuConfirmarVoo() throws Exception {
        System.out.println("========================================");
        System.out.println(" 1 - Listar Voos Não confirmados");
        System.out.println(" 2 - Confirmar Voo");
        System.out.println("========================================");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                ListarVoosNãoConfirmados();
                break;
            case 2:
                confirmarVoo();
                break;

            default:
                break;
        }
    }

    private void ListarVoosNãoConfirmados() throws Exception {
        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getAeronave().isDisponibilidade() == true) {
                System.out.println(Voo.getVoosDisponiveis().get(i));
            }
        }
        menuConfirmarVoo();
    }

    private void confirmarVoo() throws Exception {
        System.out.println("Digite o número do voo que deseja confirmar: ");
        String numeroVoo = sc.next();

        if(confirmarExistenciaVoo(numeroVoo) == true) {
            for(int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
                if(Voo.getVoosDisponiveis().get(i).getNumeroVoo().equals(numeroVoo)) {
                    Voo.getVoosDisponiveis().get(i).getAeronave().setDisponibilidade(false);
                    System.out.println("Voo confirmado: " + Voo.getVoosDisponiveis().get(i).getNumeroVoo());
                    menuOpcoes();
                }
            }
        }

        while(!confirmarExistenciaVoo(numeroVoo)) {
            System.out.println("VOO não encontrado");
            System.out.println("Digite novamente: ");
            numeroVoo = sc.nextLine();
        }   
    }

    
    private boolean confirmarExistenciaVoo(String numeroVoo) {
        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if(Voo.getVoosDisponiveis().get(i).getNumeroVoo().equals(numeroVoo)) {
                return true;
            }
        }
        return false;
    }

    private void menuDesconfirmarVoo() throws Exception {
        System.out.println("========================================");
        System.out.println(" 1 - Listar Voos confirmados");
        System.out.println(" 2 - Desconfirmar Voo");
        System.out.println("========================================");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                ListarVoosConfirmados();
                break;
            case 2:
                desconfirmarVOO();
                break;

            default:
                break;
        }
    }

    private void ListarVoosConfirmados() throws Exception {
        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getAeronave().isDisponibilidade() == false) {
                System.out.println(Voo.getVoosDisponiveis().get(i));
            }
        }
        menuDesconfirmarVoo();
    }

    private void desconfirmarVOO() throws Exception {
        System.out.println("Digite o número do voo que deseja desconfirmar: ");
        String numeroVoo = sc.next();

        if(confirmarExistenciaVoo(numeroVoo) == true) {
            for(int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
                if(Voo.getVoosDisponiveis().get(i).getNumeroVoo().equals(numeroVoo)) {
                    Voo.getVoosDisponiveis().get(i).getAeronave().setDisponibilidade(true);
                    System.out.println("Voo cancelado: " + Voo.getVoosDisponiveis().get(i).getNumeroVoo());
                    menuOpcoes();
                }
            }
        }
        while(!confirmarExistenciaVoo(numeroVoo)) {
            System.out.println("VOO não encontrado");
            System.out.println("Digite novamente: ");
            numeroVoo = sc.nextLine();
        }
    }

    // =============================== Parametros do Cliente

    private void parametrosCliente() throws Exception {
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
                cancelarVenda();
                break;
            case 3:
                menuOpcoes();
                break;
            default:
                break;
        }
    }

    private void comprarPassagem() throws Exception {
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

    private void finalizarCompra() throws Exception {

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
        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getNumeroVoo().equals(numeroVoo)) {
                voo = Voo.getVoosDisponiveis().get(i);
            }
        }

        Cliente cliente = null;
        for (int i = 0; i < Cliente.getClientes().size(); i++) {
            if (Cliente.getClientes().get(i).getCpf().equals(cpfCliente)) {
                cliente = Cliente.getClientes().get(i);
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não existe.");
            menuOpcoes();
        }

        if (voo == null) {
            System.out.println("Voo não existe. ");
            menuOpcoes();
        }

        new Venda(cliente, voo);
        menuOpcoes();
    }

    private void cancelarVenda() throws Exception {
        System.out.println("Digite o número do voo para cancelamento: ");
        String numeroVoo = sc.next();
        System.out.println("Digite seu CPF: ");
        String cpf = sc.next();

        ArrayList<Venda> vendasRealizadas = Venda.getVendasRealizadas();
        ArrayList<Venda> vendasCanceladas = Venda.getVendasCanceladas();

        for (Venda venda : vendasRealizadas) {
            if (venda.getVoo().getNumeroVoo().equals(numeroVoo) && venda.getCliente().getCpf().equals(cpf)) {
                vendasRealizadas.remove(venda);
                vendasCanceladas.add(venda);
                System.out.println("Venda cancelada com sucesso.");
                menuOpcoes();
            }
        }

        System.out.println("Venda não encontrada.");
    }

    // ================================================== RELATORIOS
    private void relatorios() throws Exception {
        System.out.println("========== Relatórios ==========");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" 1 - Relatório de Vendas");
        System.out.println(" 2 - Relatório de Todos Voos");
        System.out.println(" 3 - Relatório de Clientes");
        System.out.println(" 4 - Relatório Aviões");
        System.out.println(" 5 - Voltar");
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
                relatorioAvioes();
                break;
            case 5:
                menuOpcoes();
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
            System.out.println(Venda.getVendasCanceladas() + " - CANCELADA");
        }
        menuOpcoes();
    }

    private void relatorioVoos() throws Exception {
        System.out.println("=====================================");
        if (Voo.getVoosDisponiveis().isEmpty()) {
            System.out.println("Nenhum VOO disponivel.");
            menuOpcoes();
        }

        System.out.println(" ====== VOOS DISPONIVEIS ====== ");
        for (int i = 0; i < Voo.getVoosDisponiveis().size(); i++) {
            if (Voo.getVoosDisponiveis().get(i).getAeronave().isDisponibilidade() == false) {
                System.out.println(Voo.getVoosDisponiveis().get(i) + " - CONFIRMADO");
            } else {
                System.out.println(Voo.getVoosDisponiveis().get(i) + " - NÃO CONFIRMADO");
            }
        }

        if(Voo.getVoosCancelados().isEmpty()) {
            System.out.println("Nenhum VOO cancelado.");
            menuOpcoes();
        }

        System.out.println("");
        System.out.println(" ====== VOOS CANCELADOS ====== ");
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

    private void relatorioAvioes() throws Exception {
        System.out.println("=====================================");
        if (Aviao.getAvioes().isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
            menuOpcoes();
        }

        for (int i = 0; i < Aviao.getAvioes().size(); i++) {
            System.out.println(Aviao.getAvioes().get(i));
        }

        System.out.println("=====================================");
        menuOpcoes();
    }

}
