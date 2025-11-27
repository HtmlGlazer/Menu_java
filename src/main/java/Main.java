import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import dao.BebidaDAO;
import dao.MesaDAO;
import dao.PratoDAO;
import dao.ReservaDAO;
import model.Bebida;
import model.Mesa;
import model.Prato;
import model.Reserva;
import model.ReservaEspecial;

public class Main {
    
    private static final PratoDAO pratoDAO = new PratoDAO();
    private static final BebidaDAO bebidaDAO = new BebidaDAO();
    private static final MesaDAO mesaDAO = new MesaDAO();
    private static final ReservaDAO reservaDAO = new ReservaDAO();
    
    public static void main(String[] args) {
        boolean rodar = true;

        try (Scanner sc = new Scanner(System.in)){
        while (rodar) {
            exibirMenuPrincipal();
            
            int opcao = sc.nextInt();
            sc.nextLine();
            
        switch (opcao) {
        case 1 -> menuCadastrar(sc);
        case 2 -> menuConsultar(sc);
        case 3 -> menuAtualizar(sc);
        case 4 -> menuEliminar(sc);
        case 0 -> {
            System.out.println("\n=================================");
            System.out.println("  Encerrando o sistema...");
            System.out.println("=================================\n");
        rodar = false;
        }
        default -> System.out.println("\nOpção invalida! Tente novamente.\n");
        }
            
            if (rodar && opcao >= 1 && opcao <= 4) {
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine();
            }
        }
        
     }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE RESTAURANTE            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Eliminar");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha: ");
    }
    
    // ============================================
    // CADASTRAR
    // ============================================
    private static void menuCadastrar(Scanner sc) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          CADASTRAR                    ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("1 - Prato");
        System.out.println("2 - Bebida");
        System.out.println("3 - Mesa");
        System.out.println("4 - Reserva");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
    switch (opcao) {
        case 1 -> cadastrarPrato(sc);
        case 2 -> cadastrarBebida(sc);
        case 3 -> cadastrarMesa(sc);
        case 4 -> cadastrarReserva(sc);
        case 0 -> System.out.println("Voltando...");
        default -> System.out.println("Opção invalida!");
    }
    }
    
    // ============================================
    // CADASTRAR PRATO
    // ============================================
    private static void cadastrarPrato(Scanner sc) {
        System.out.println("\n=== Cadastrar Prato ===");
        
        System.out.print("Nome do prato: ");
        String nome = sc.nextLine();
        
        System.out.print("Descricao: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preco: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        String categoria = escolherCategoriaPrato(sc);
        String tamanho = escolherTamanhoPrato(sc);
        
        System.out.print("Tempo de preparo (minutos): ");
        int tempoPreparo = sc.nextInt();
        sc.nextLine();
        
        Prato prato = new Prato(0, nome, descricao, preco, categoria, tamanho, tempoPreparo);
        pratoDAO.cadastrar(prato);
        
        System.out.println("\nPrato cadastrado com sucesso!");
        System.out.println("Preco final: R$ " + String.format("%.2f", prato.calcularPrecoFinal()));
        System.out.println(prato);
    }
    
    // ============================================
    // CADASTRAR BEBIDA
    // ============================================
    private static void cadastrarBebida(Scanner sc) {
        System.out.println("\n=== Cadastrar Bebida ===");
        
        System.out.print("Nome da bebida: ");
        String nome = sc.nextLine();
        
        System.out.print("Descricao: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preco: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        String categoria = escolherCategoriaBebida(sc);
        int volume = escolherVolumeBebida(sc);
        boolean gelada = escolherSeGelada(sc);
        
        Bebida bebida = new Bebida(0, nome, descricao, preco, categoria, volume, gelada);
        bebidaDAO.cadastrar(bebida);
        
        System.out.println("\nBebida cadastrada com sucesso!");
        System.out.println("Preco final: R$ " + String.format("%.2f", bebida.calcularPrecoFinal()));
        System.out.println(bebida);
    }
    
    // ============================================
    // CADASTRAR MESA
    // ============================================
    private static void cadastrarMesa(Scanner sc) {
        System.out.println("\n=== Cadastrar Mesa ===");
        
        System.out.print("Numero da mesa: ");
        int numero = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Capacidade (quantidade de pessoas): ");
        int capacidade = sc.nextInt();
        sc.nextLine();
        
        String localizacao = escolherLocalizacaoMesa(sc);
        
        Mesa mesa = new Mesa(numero, capacidade, localizacao);
        mesaDAO.cadastrar(mesa);
        
        System.out.println("\nMesa cadastrada com sucesso!");
        System.out.println(mesa);
    }
    
    // ============================================
    // CADASTRAR RESERVA
    // ============================================
    private static void cadastrarReserva(Scanner sc) {
        System.out.println("\n=== Cadastrar Reserva ===");
        System.out.println("1 - Reserva Normal");
        System.out.println("2 - Reserva Especial");
        System.out.println("0 - Cancelar");
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
    switch (opcao) {
        case 1 -> cadastrarReservaNormal(sc);
        case 2 -> cadastrarReservaEspecial(sc);
        case 0 -> System.out.println("Operação cancelada.");
        default -> System.out.println("Opção invalida!");
    }
    }
    
    private static void cadastrarReservaNormal(Scanner sc) {
        System.out.println("\n=== Cadastrar Reserva Normal ===");
        
        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();
        
        System.out.print("Quantidade de pessoas: ");
        int quantidadePessoas = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Numero da mesa: ");
        int numeroMesa = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime dataHora = escolherDataHora(sc);
        
        Reserva reserva = new Reserva(0, dataHora, nomeCliente, quantidadePessoas, numeroMesa, Reserva.STATUS_ATIVA);
        reservaDAO.cadastrar(reserva);
        
        System.out.println("\nReserva cadastrada com sucesso!");
        System.out.println(reserva);
    }
    
    private static void cadastrarReservaEspecial(Scanner sc) {
        System.out.println("\n=== Cadastrar Reserva Especial ===");
        
        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();
        
        System.out.print("Quantidade de pessoas: ");
        int quantidadePessoas = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Numero da mesa: ");
        int numeroMesa = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime dataHora = escolherDataHora(sc);
        
        String tipoEvento = escolherTipoEvento(sc);
        
        System.out.print("Decoracao especial? (1-Sim / 0-Nao): ");
        int decoracao = sc.nextInt();
        sc.nextLine();
        boolean decoracaoEspecial = decoracao == 1;
        
        ReservaEspecial reservaEspecial = new ReservaEspecial(0, dataHora, nomeCliente, 
                                                               quantidadePessoas, numeroMesa, 
                                                               Reserva.STATUS_ATIVA, tipoEvento, decoracaoEspecial);
        reservaDAO.cadastrar(reservaEspecial);
        
        System.out.println("\nReserva especial cadastrada com sucesso!");
        System.out.println(reservaEspecial);
    }
    
    // ============================================
    // METODOS AUXILIARES
    // ============================================
    private static String escolherCategoriaPrato(Scanner sc) {
        System.out.println("\nCategoria:");
        System.out.println("1 - " + Prato.CATEGORIA_ENTRADA);
        System.out.println("2 - " + Prato.CATEGORIA_PRINCIPAL);
        System.out.println("3 - " + Prato.CATEGORIA_ACOMPANHAMENTO);
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Prato.CATEGORIA_ENTRADA;
            case 2 -> Prato.CATEGORIA_PRINCIPAL;
            case 3 -> Prato.CATEGORIA_ACOMPANHAMENTO;
            default -> Prato.CATEGORIA_PRINCIPAL;
        };
    }
    
    private static String escolherTamanhoPrato(Scanner sc) {
        System.out.println("\nTamanho:");
        System.out.println("1 - " + Prato.TAMANHO_P);
        System.out.println("2 - " + Prato.TAMANHO_M);
        System.out.println("3 - " + Prato.TAMANHO_G);
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Prato.TAMANHO_P;
            case 2 -> Prato.TAMANHO_M;
            case 3 -> Prato.TAMANHO_G;
            default -> Prato.TAMANHO_M;
        };
    }
    
    private static String escolherCategoriaBebida(Scanner sc) {
        System.out.println("\nCategoria:");
        System.out.println("1 - " + Bebida.CATEGORIA_REFRIGERANTE);
        System.out.println("2 - " + Bebida.CATEGORIA_SUCO);
        System.out.println("3 - " + Bebida.CATEGORIA_ALCOOLICA);
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Bebida.CATEGORIA_REFRIGERANTE;
            case 2 -> Bebida.CATEGORIA_SUCO;
            case 3 -> Bebida.CATEGORIA_ALCOOLICA;
            default -> Bebida.CATEGORIA_REFRIGERANTE;
        };
    }
    
    private static int escolherVolumeBebida(Scanner sc) {
        System.out.println("\nVolume:");
        System.out.println("1 - 300ml");
        System.out.println("2 - 500ml");
        System.out.println("3 - 750ml");
        System.out.println("4 - 1000ml");
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> 300;
            case 2 -> 500;
            case 3 -> 750;
            case 4 -> 1000;
            default -> 500;
        };
    }
    
    private static boolean escolherSeGelada(Scanner sc) {
        System.out.println("\nGelada?");
        System.out.println("1 - Sim");
        System.out.println("2 - Nao");
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return opcao == 1;
    }
    
    private static String escolherLocalizacaoMesa(Scanner sc) {
        System.out.println("\nLocalizacao:");
        System.out.println("1 - " + Mesa.LOCALIZACAO_JANELA);
        System.out.println("2 - " + Mesa.LOCALIZACAO_CENTRO);
        System.out.println("3 - " + Mesa.LOCALIZACAO_VARANDA);
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Mesa.LOCALIZACAO_JANELA;
            case 2 -> Mesa.LOCALIZACAO_CENTRO;
            case 3 -> Mesa.LOCALIZACAO_VARANDA;
            default -> Mesa.LOCALIZACAO_CENTRO;
        };
    }
    
    private static LocalDateTime escolherDataHora(Scanner sc) {
        System.out.println("\nData e Hora:");
        
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        
        System.out.print("Mes: ");
        int mes = sc.nextInt();
        
        System.out.print("Dia: ");
        int dia = sc.nextInt();
        
        System.out.print("Hora: ");
        int hora = sc.nextInt();
        
        System.out.print("Minuto: ");
        int minuto = sc.nextInt();
        sc.nextLine();
        
        return LocalDateTime.of(ano, mes, dia, hora, minuto);
    }
    
    private static String escolherTipoEvento(Scanner sc) {
        System.out.println("\nTipo de Evento:");
        System.out.println("1 - " + ReservaEspecial.EVENTO_ANIVERSARIO);
        System.out.println("2 - " + ReservaEspecial.EVENTO_CASAMENTO);
        System.out.println("3 - " + ReservaEspecial.EVENTO_CORPORATIVO);
        System.out.println("4 - " + ReservaEspecial.EVENTO_FORMATURA);
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> ReservaEspecial.EVENTO_ANIVERSARIO;
            case 2 -> ReservaEspecial.EVENTO_CASAMENTO;
            case 3 -> ReservaEspecial.EVENTO_CORPORATIVO;
            case 4 -> ReservaEspecial.EVENTO_FORMATURA;
            default -> ReservaEspecial.EVENTO_ANIVERSARIO;
        };
    }

// ============================================
// CONSULTAR
// ============================================
private static void menuConsultar(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║          CONSULTAR                    ║");
    System.out.println("╚═══════════════════════════════════════╝");
    System.out.println("1 - Cardapio");
    System.out.println("2 - Mesas");
    System.out.println("3 - Reservas");
    System.out.println("0 - Voltar");
    System.out.print("\nEscolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    switch (opcao) {
        case 1 -> consultarCardapio(sc);
        case 2 -> consultarMesas(sc);
        case 3 -> consultarReservas(sc);
        case 0 -> System.out.println("Voltando...");
    default -> System.out.println("Opção invalida!");
    }
}

// ============================================
// CONSULTAR CARDAPIO
// ============================================
@SuppressWarnings("unused")
private static void consultarCardapio(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║   CONSULTAR CARDAPIO                  ║");
    System.out.println("╚═══════════════════════════════════════╝");
    
    List<Prato> listaPratos = pratoDAO.consultarTodos();
    List<Bebida> listaBebidas = bebidaDAO.consultarTodos();
    if (listaPratos.isEmpty() && listaBebidas.isEmpty()) {
        System.out.println("\nNenhum item cadastrado no cardapio!");
        return;
    }
    
    // Exibir Pratos
    System.out.println("\n┌─────────────────────────────────────┐");
    System.out.println("│           PRATOS                    │");
    System.out.println("└─────────────────────────────────────┘");
    
    if (listaPratos.isEmpty()) {
        System.out.println("Nenhum prato cadastrado.");
    } else {
        int contador = 1;
        for (Prato prato : listaPratos) {
            System.out.printf("%d. %s%n", contador++, prato);
            System.out.printf("   Preco final: R$ %.2f%n%n", prato.calcularPrecoFinal());
        }
    }
    
    // Exibir Bebidas
    System.out.println("\n┌─────────────────────────────────────┐");
    System.out.println("│           BEBIDAS                   │");
    System.out.println("└─────────────────────────────────────┘");
    
    if (listaBebidas.isEmpty()) {
        System.out.println("Nenhuma bebida cadastrada.");
    } else {
        int contador = 1;
        for (Bebida bebida : listaBebidas) {
            System.out.printf("%d. %s%n", contador++, bebida);
            System.out.printf("   Preco final: R$ %.2f%n%n", bebida.calcularPrecoFinal());
        }
    }
    
    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    System.out.printf("Total: %d pratos, %d bebidas%n", 
                     listaPratos.size(), listaBebidas.size());
}

// ============================================
// CONSULTAR MESAS
// ============================================
@SuppressWarnings("unused")
private static void consultarMesas(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║        CONSULTAR MESAS                ║");
    System.out.println("╚═══════════════════════════════════════╝");
    
    List<Mesa> listaMesas = mesaDAO.consultarTodos();
    if (listaMesas.isEmpty()) {
        System.out.println("\nNenhuma mesa cadastrada!");
        return;
    }
    
    System.out.println("\n--- Mesas Cadastradas ---\n");
    
    int contador = 1;
    for (Mesa mesa : listaMesas) {
        System.out.printf("%d. %s%n", contador++, mesa);
    }
    
    System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    System.out.println("Total: " + listaMesas.size() + " mesas");
}

// ============================================
// CONSULTAR RESERVAS
// ============================================
@SuppressWarnings("unused")
private static void consultarReservas(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║      CONSULTAR RESERVAS               ║");
    System.out.println("╚═══════════════════════════════════════╝");
    
    List<Reserva> listaReservas = reservaDAO.consultarTodos();
    if (listaReservas.isEmpty()) {
        System.out.println("\nNenhuma reserva cadastrada!");
        return;
    }
    
    // Separar reservas normais e especiais
    int contadorNormais = 0;
    int contadorEspeciais = 0;
    
    // Exibir Reservas Normais
    System.out.println("\n┌─────────────────────────────────────┐");
    System.out.println("│      RESERVAS NORMAIS               │");
    System.out.println("└─────────────────────────────────────┘");
    
    for (Reserva reserva : listaReservas) {
        if (!(reserva instanceof ReservaEspecial)) {
            contadorNormais++;
            System.out.printf("%d. %s%n%n", contadorNormais, reserva);
        }
    }
    
    if (contadorNormais == 0) {
        System.out.println("Nenhuma reserva normal cadastrada.");
    }
    
    // Exibir Reservas Especiais
    System.out.println("\n┌─────────────────────────────────────┐");
    System.out.println("│      RESERVAS ESPECIAIS             │");
    System.out.println("└─────────────────────────────────────┘");
    
    for (Reserva reserva : listaReservas) {
        if (reserva instanceof ReservaEspecial) {
            contadorEspeciais++;
            System.out.printf("%d. %s%n%n", contadorEspeciais, reserva);
        }
    }
    
    if (contadorEspeciais == 0) {
        System.out.println("Nenhuma reserva especial cadastrada.");
    }
    
    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    System.out.printf("Total: %d reservas (%d normais, %d especiais)%n", 
                     listaReservas.size(), contadorNormais, contadorEspeciais);
}

// ============================================
// ATUALIZAR
// ============================================
private static void menuAtualizar(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║          ATUALIZAR                    ║");
    System.out.println("╚═══════════════════════════════════════╝");
    System.out.println("1 - Cardapio");
    System.out.println("2 - Mesa");
    System.out.println("3 - Reserva");
    System.out.println("0 - Voltar");
    System.out.print("\nEscolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    switch (opcao) {
        case 1 -> atualizarCardapio(sc);
        case 2 -> atualizarMesa(sc);
        case 3 -> atualizarReserva(sc);
        case 0 -> System.out.println("Voltando...");
        default -> System.out.println("Opção invalida!");
    }
}

// ============================================
// ATUALIZAR CARDAPIO
// ============================================
private static void atualizarCardapio(Scanner sc) {
    System.out.println("\n=== Atualizar Item do Cardapio ===");
    System.out.println("1 - Atualizar Prato");
    System.out.println("2 - Atualizar Bebida");
    System.out.println("0 - Voltar");
    System.out.print("Escolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    switch (opcao) {
        case 1 -> atualizarPrato(sc);
        case 2 -> atualizarBebida(sc);
        case 0 -> System.out.println("Voltando...");
        default -> System.out.println("Opção invalida!");
    }
}

private static void atualizarPrato(Scanner sc) {
    List<Prato> listaPratos = pratoDAO.consultarTodos();
    if (listaPratos.isEmpty()) {
        System.out.println("\nNenhum prato cadastrado!");
        return;
    }
    
    System.out.println("\n=== Atualizar Prato ===");
    System.out.println("Pratos cadastrados:\n");
    
    for (int i = 0; i < listaPratos.size(); i++) {
        System.out.printf("%d. %s%n", (i + 1), listaPratos.get(i).getNome());
    }
    
    System.out.print("\nDigite o numero do prato a atualizar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaPratos.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Prato prato = listaPratos.get(opcao - 1);
    
    System.out.println("\nDados atuais:");
    System.out.println(prato);
    System.out.println("\n--- Atualizar dados ---");
    
    System.out.print("Novo nome (ENTER para manter atual): ");
    String nome = sc.nextLine();
    if (!nome.isEmpty()) {
        prato.setNome(nome);
    }
    
    System.out.print("Nova descricao (ENTER para manter atual): ");
    String descricao = sc.nextLine();
    if (!descricao.isEmpty()) {
        prato.setDescricao(descricao);
    }
    
    System.out.print("Novo preco (0 para manter atual): R$ ");
    double preco = sc.nextDouble();
    sc.nextLine();
    if (preco > 0) {
        prato.setPreco(preco);
    }
    
    System.out.print("Atualizar categoria? (1-Sim / 0-Nao): ");
    int atualizarCat = sc.nextInt();
    sc.nextLine();
    if (atualizarCat == 1) {
        String categoria = escolherCategoriaPrato(sc);
        prato.setCategoria(categoria);
    }
    
    System.out.print("Atualizar tamanho? (1-Sim / 0-Nao): ");
    int atualizarTam = sc.nextInt();
    sc.nextLine();
    if (atualizarTam == 1) {
        String tamanho = escolherTamanhoPrato(sc);
        prato.setTamanho(tamanho);
    }
    
    System.out.print("Novo tempo de preparo (0 para manter atual): ");
    int tempoPreparo = sc.nextInt();
    sc.nextLine();
    if (tempoPreparo > 0) {
        prato.setTempoPreparo(tempoPreparo);
    }

    pratoDAO.atualizar(prato);
    System.out.println("\nPrato atualizado com sucesso!");
    System.out.println(prato);
}

private static void atualizarBebida(Scanner sc) {
    List<Bebida> listaBebidas = bebidaDAO.consultarTodos();
    if (listaBebidas.isEmpty()) {
        System.out.println("\nNenhuma bebida cadastrada!");
        return;
    }
    
    System.out.println("\n=== Atualizar Bebida ===");
    System.out.println("Bebidas cadastradas:\n");
    
    for (int i = 0; i < listaBebidas.size(); i++) {
        System.out.printf("%d. %s%n", (i + 1), listaBebidas.get(i).getNome());
    }
    
    System.out.print("\nDigite o numero da bebida a atualizar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaBebidas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Bebida bebida = listaBebidas.get(opcao - 1);
    
    System.out.println("\nDados atuais:");
    System.out.println(bebida);
    System.out.println("\n--- Atualizar dados ---");
    
    System.out.print("Novo nome (ENTER para manter atual): ");
    String nome = sc.nextLine();
    if (!nome.isEmpty()) {
        bebida.setNome(nome);
    }
    
    System.out.print("Nova descricao (ENTER para manter atual): ");
    String descricao = sc.nextLine();
    if (!descricao.isEmpty()) {
        bebida.setDescricao(descricao);
    }
    
    System.out.print("Novo preco (0 para manter atual): R$ ");
    double preco = sc.nextDouble();
    sc.nextLine();
    if (preco > 0) {
        bebida.setPreco(preco);
    }
    
    System.out.print("Atualizar categoria? (1-Sim / 0-Nao): ");
    int atualizarCat = sc.nextInt();
    sc.nextLine();
    if (atualizarCat == 1) {
        String categoria = escolherCategoriaBebida(sc);
        bebida.setCategoria(categoria);
    }
    
    System.out.print("Atualizar volume? (1-Sim / 0-Nao): ");
    int atualizarVol = sc.nextInt();
    sc.nextLine();
    if (atualizarVol == 1) {
        int volume = escolherVolumeBebida(sc);
        bebida.setVolume(volume);
    }
    
    System.out.print("Atualizar se e gelada? (1-Sim / 0-Nao): ");
    int atualizarGelada = sc.nextInt();
    sc.nextLine();
    if (atualizarGelada == 1) {
        boolean gelada = escolherSeGelada(sc);
        bebida.setGelada(gelada);
    }
    
    bebidaDAO.atualizar(bebida);
    System.out.println("\nBebida atualizada com sucesso!");
    System.out.println(bebida);
}

// ============================================
// ATUALIZAR MESA
// ============================================
private static void atualizarMesa(Scanner sc) {
    List<Mesa> listaMesas = mesaDAO.consultarTodos();
    if (listaMesas.isEmpty()) {
        System.out.println("\nNenhuma mesa cadastrada!");
        return;
    }
    
    System.out.println("\n=== Atualizar Mesa ===");
    System.out.println("Mesas cadastradas:\n");
    
    for (int i = 0; i < listaMesas.size(); i++) {
        Mesa mesa = listaMesas.get(i);
        System.out.printf("%d. Mesa %d - %d pessoas - %s%n", 
                        (i + 1), mesa.getNumero(), mesa.getCapacidade(), mesa.getLocalizacao());
    }
    
    System.out.print("\nDigite o numero da opcao a atualizar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaMesas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Mesa mesa = listaMesas.get(opcao - 1);
    
    System.out.println("\nDados atuais:");
    System.out.println(mesa);
    System.out.println("\n--- Atualizar dados ---");
    
    System.out.print("Novo numero (0 para manter atual): ");
    int numero = sc.nextInt();
    sc.nextLine();
    if (numero > 0) {
        mesa.setNumero(numero);
    }
    
    System.out.print("Nova capacidade (0 para manter atual): ");
    int capacidade = sc.nextInt();
    sc.nextLine();
    if (capacidade > 0) {
        mesa.setCapacidade(capacidade);
    }
    
    System.out.print("Atualizar localizacao? (1-Sim / 0-Nao): ");
    int atualizarLoc = sc.nextInt();
    sc.nextLine();
    if (atualizarLoc == 1) {
        String localizacao = escolherLocalizacaoMesa(sc);
        mesa.setLocalizacao(localizacao);
    }
    mesaDAO.atualizar(mesa);
    System.out.println("\nMesa atualizada com sucesso!");
    System.out.println(mesa);
}

// ============================================
// ATUALIZAR RESERVA
// ============================================
@SuppressWarnings("unused")
private static void atualizarReserva(Scanner sc) {
    List<Reserva> listaReservas = reservaDAO.consultarTodos();
    if (listaReservas.isEmpty()) {
        System.out.println("\nNenhuma reserva cadastrada!");
        return;
    }
    
    System.out.println("\n=== Atualizar Reserva ===");
    System.out.println("Reservas cadastradas:\n");
    
    for (int i = 0; i < listaReservas.size(); i++) {
        Reserva reserva = listaReservas.get(i);
        String tipo = (reserva instanceof ReservaEspecial) ? "[ESPECIAL]" : "[NORMAL]";
        System.out.printf("%d. %s %s - Mesa %d - %s%n", 
                        (i + 1), tipo, reserva.getNomeCliente(), 
                        reserva.getNumeroMesa(), reserva.getDataHoraFormatada());
    }
    
    System.out.print("\nDigite o numero da reserva a atualizar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaReservas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Reserva reserva = listaReservas.get(opcao - 1);
    
    System.out.println("\nDados atuais:");
    System.out.println(reserva);
    System.out.println("\n--- Atualizar dados ---");
    
    System.out.print("Novo nome do cliente (ENTER para manter atual): ");
    String nomeCliente = sc.nextLine();
    if (!nomeCliente.isEmpty()) {
        reserva.setNomeCliente(nomeCliente);
    }
    
    System.out.print("Nova quantidade de pessoas (0 para manter atual): ");
    int quantidadePessoas = sc.nextInt();
    sc.nextLine();
    if (quantidadePessoas > 0) {
        reserva.setQuantidadePessoas(quantidadePessoas);
    }
    
    System.out.print("Novo numero da mesa (0 para manter atual): ");
    int numeroMesa = sc.nextInt();
    sc.nextLine();
    if (numeroMesa > 0) {
        reserva.setNumeroMesa(numeroMesa);
    }
    
    System.out.print("Atualizar data e hora? (1-Sim / 0-Nao): ");
    int atualizarData = sc.nextInt();
    sc.nextLine();
    if (atualizarData == 1) {
        LocalDateTime dataHora = escolherDataHora(sc);
        reserva.setDataHora(dataHora);
    }
    
    System.out.print("Atualizar status? (1-Sim / 0-Nao): ");
    int atualizarStatus = sc.nextInt();
    sc.nextLine();
    if (atualizarStatus == 1) {
        String status = escolherStatusReserva(sc);
        reserva.setStatus(status);
    }
    
    // Se for reserva especial, atualizar campos especificos
    if (reserva instanceof ReservaEspecial) {
        ReservaEspecial reservaEspecial = (ReservaEspecial) reserva;
        
        System.out.print("Atualizar tipo de evento? (1-Sim / 0-Nao): ");
        int atualizarEvento = sc.nextInt();
        sc.nextLine();
        if (atualizarEvento == 1) {
            String tipoEvento = escolherTipoEvento(sc);
            reservaEspecial.setTipoEvento(tipoEvento);
        }
        
        System.out.print("Atualizar decoracao especial? (1-Sim / 0-Nao): ");
        int atualizarDecoracao = sc.nextInt();
        sc.nextLine();
        if (atualizarDecoracao == 1) {
            System.out.print("Decoracao especial? (1-Sim / 0-Nao): ");
            int decoracao = sc.nextInt();
            sc.nextLine();
            reservaEspecial.setDecoracaoEspecial(decoracao == 1);
        }
    }
    reservaDAO.atualizar(reserva);
    System.out.println("\nReserva atualizada com sucesso!");
    System.out.println(reserva);
}

// ============================================
// METODO AUXILIAR ADICIONAL
// ============================================
private static String escolherStatusReserva(Scanner sc) {
    System.out.println("\nStatus:");
    System.out.println("1 - " + Reserva.STATUS_ATIVA);
    System.out.println("2 - " + Reserva.STATUS_CANCELADA);
    System.out.println("3 - " + Reserva.STATUS_FINALIZADA);
    System.out.print("Escolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    return switch (opcao) {
        case 1 -> Reserva.STATUS_ATIVA;
        case 2 -> Reserva.STATUS_CANCELADA;
        case 3 -> Reserva.STATUS_FINALIZADA;
        default -> Reserva.STATUS_ATIVA;
    };
}

// ============================================
// ELIMINAR
// ============================================
private static void menuEliminar(Scanner sc) {
    System.out.println("\n╔═══════════════════════════════════════╗");
    System.out.println("║          ELIMINAR                     ║");
    System.out.println("╚═══════════════════════════════════════╝");
    System.out.println("1 - Cardapio");
    System.out.println("2 - Mesa");
    System.out.println("3 - Reserva");
    System.out.println("0 - Voltar");
    System.out.print("\nEscolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    switch (opcao) {
        case 1 -> eliminarCardapio(sc);
        case 2 -> eliminarMesa(sc);
        case 3 -> eliminarReserva(sc);
        case 0 -> System.out.println("Voltando...");
        default -> System.out.println("Opção invalida!");
    }
}

// ============================================
// ELIMINAR CARDAPIO
// ============================================
private static void eliminarCardapio(Scanner sc) {
    System.out.println("\n=== Eliminar Item do Cardapio ===");
    System.out.println("1 - Eliminar Prato");
    System.out.println("2 - Eliminar Bebida");
    System.out.println("0 - Voltar");
    System.out.print("Escolha: ");
    
    int opcao = sc.nextInt();
    sc.nextLine();
    
    switch (opcao) {
        case 1 -> eliminarPrato(sc);
        case 2 -> eliminarBebida(sc);
        case 0 -> System.out.println("Voltando...");
        default -> System.out.println("Opção invalida!");
    }
}

private static void eliminarPrato(Scanner sc) {
    List<Prato> listaPratos = pratoDAO.consultarTodos();
    if (listaPratos.isEmpty()) {
        System.out.println("\nNenhum prato cadastrado!");
        return;
    }
    
    System.out.println("\n=== Eliminar Prato ===");
    System.out.println("Pratos cadastrados:\n");
    
    for (int i = 0; i < listaPratos.size(); i++) {
        System.out.printf("%d. %s%n", (i + 1), listaPratos.get(i).getNome());
    }
    
    System.out.print("\nDigite o numero do prato a eliminar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaPratos.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Prato pratoRemovido = listaPratos.remove(opcao - 1);
    pratoDAO.eliminar(pratoRemovido.getId());
    System.out.println("\nPrato '" + pratoRemovido.getNome() + "' eliminado com sucesso!");
}

private static void eliminarBebida(Scanner sc) {
    List<Bebida> listaBebidas = bebidaDAO.consultarTodos();
    if (listaBebidas.isEmpty()) {
        System.out.println("\nNenhuma bebida cadastrada!");
        return;
    }
    
    System.out.println("\n=== Eliminar Bebida ===");
    System.out.println("Bebidas cadastradas:\n");
    
    for (int i = 0; i < listaBebidas.size(); i++) {
        System.out.printf("%d. %s%n", (i + 1), listaBebidas.get(i).getNome());
    }
    
    System.out.print("\nDigite o numero da bebida a eliminar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaBebidas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Bebida bebidaRemovida = listaBebidas.remove(opcao - 1);
    bebidaDAO.eliminar(bebidaRemovida.getId());
    System.out.println("\nBebida '" + bebidaRemovida.getNome() + "' eliminada com sucesso!");
}

// ============================================
// ELIMINAR MESA
// ============================================
private static void eliminarMesa(Scanner sc) {
    List<Mesa> listaMesas = mesaDAO.consultarTodos();
    if (listaMesas.isEmpty()) {
        System.out.println("\nNenhuma mesa cadastrada!");
        return;
    }
    
    System.out.println("\n=== Eliminar Mesa ===");
    System.out.println("Mesas cadastradas:\n");
    
    for (int i = 0; i < listaMesas.size(); i++) {
        Mesa mesa = listaMesas.get(i);
        System.out.printf("%d. Mesa %d - %d pessoas - %s%n", 
                        (i + 1), mesa.getNumero(), mesa.getCapacidade(), mesa.getLocalizacao());
    }
    
    System.out.print("\nDigite o numero da mesa a eliminar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaMesas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Mesa mesaRemovida = listaMesas.remove(opcao - 1);
    mesaDAO.eliminar(mesaRemovida.getNumero());
    System.out.println("\nMesa " + mesaRemovida.getNumero() + " eliminada com sucesso!");
}

// ============================================
// ELIMINAR RESERVA
// ============================================
@SuppressWarnings("null")
private static void eliminarReserva(Scanner sc) {
    List<Reserva> listaReservas = reservaDAO.consultarTodos();
    if (listaReservas.isEmpty()) {
        System.out.println("\nNenhuma reserva cadastrada!");
        return;
    }
    
    System.out.println("\n=== Eliminar Reserva ===");
    System.out.println("Reservas cadastradas:\n");
    
    for (int i = 0; i < listaReservas.size(); i++) {
        Reserva reserva = listaReservas.get(i);
        String tipo = (reserva instanceof ReservaEspecial) ? "[ESPECIAL]" : "[NORMAL]";
        System.out.printf("%d. %s %s - Mesa %d - %s%n", 
                        (i + 1), tipo, reserva.getNomeCliente(), 
                        reserva.getNumeroMesa(), reserva.getDataHoraFormatada());
    }
    
    System.out.print("\nDigite o numero da reserva a eliminar (0 para cancelar): ");
    int opcao = sc.nextInt();
    sc.nextLine();
    
    if (opcao == 0) {
        System.out.println("Operação cancelada.");
        return;
    }
    
    if (opcao < 1 || opcao > listaReservas.size()) {
        System.out.println("Opção invalida!");
        return;
    }
    
    Reserva reservaRemovida = listaReservas.remove(opcao - 1);
    reservaDAO.eliminar(reservaRemovida.getId());
    System.out.println("\nReserva de '" + reservaRemovida.getNomeCliente() + "' eliminada com sucesso!");
}
}