import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    // Listas temporárias para armazenar dados (substituir por DAO depois)
    private static List<Prato> listaPratos = new ArrayList<>();
    private static List<Bebida> listaBebidas = new ArrayList<>();
    private static List<Mesa> listaMesas = new ArrayList<>();
    
    public static void main(String[] args) {
        boolean rodar = true;
        Scanner sc = new Scanner(System.in);
        
        while (rodar) {
            exibirMenu();
            
            int opcao = sc.nextInt();
            sc.nextLine(); 
            
            switch (opcao) {
                case 1:
                    cadastrarItemCardapio(sc);
                case 2:
                    consultarItensCardapio(sc);
                case 3:
                    System.out.println("\n[Atualizar Item do Cardápio] - Em desenvolvimento");
                case 4:
                    eliminarItemCardapio(sc);
                case 5:
                    cadastrarMesa(sc);
                case 6:
                    consultarMesas(sc);
                case 7:
                    System.out.println("\n[Atualizar Mesa] - Em desenvolvimento");
                case 8:
                    eliminarMesa(sc);
                case 9:
                    System.out.println("\n[Cadastrar Reserva] - Em desenvolvimento");
                case 10:
                    System.out.println("\n[Consultar Reservas] - Em desenvolvimento");
                case 11:
                    System.out.println("\n[Atualizar Reserva] - Em desenvolvimento");
                case 12:
                    System.out.println("\n[Eliminar Reserva] - Em desenvolvimento");
                case 0:
                    System.out.println("\n=================================");
                    System.out.println("  Encerrando o sistema...");
                    System.out.println("  Até logo!");
                    System.out.println("=================================\n");
                    rodar = false;
                
                default:
                    System.out.println("\n Opção inválida! Tente novamente.\n");
            }
            
            if (rodar && opcao >= 1 && opcao <= 12) {
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine();
            }
        }
        
        sc.close();
    }
    
    // ============================================
    // EXIBIÇÃO DE MENU
    // ============================================
    private static void exibirMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE RESTAURANTE            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│          CARDÁPIO                   │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 1  - Cadastrar Item do Cardápio     │");
        System.out.println("│ 2  - Consultar Itens do Cardápio    │");
        System.out.println("│ 3  - Atualizar Item do Cardápio     │");
        System.out.println("│ 4  - Eliminar Item do Cardápio      │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println();
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│          MESAS                      │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 5  - Cadastrar Mesa                 │");
        System.out.println("│ 6  - Consultar Mesas                │");
        System.out.println("│ 7  - Atualizar Mesa                 │");
        System.out.println("│ 8  - Eliminar Mesa                  │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println();
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│          RESERVAS                   │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 9  - Cadastrar Reserva              │");
        System.out.println("│ 10 - Consultar Reservas             │");
        System.out.println("│ 11 - Atualizar Reserva              │");
        System.out.println("│ 12 - Eliminar Reserva               │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println();
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│ 0  - Sair do Sistema                │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("\n➤ Escolha uma opção: ");
    }
    
    // ============================================
    // CARDÁPIO - CADASTRAR
    // ============================================
    private static void cadastrarItemCardapio(Scanner sc) {
        System.out.println("\n=== Cadastrar Item do Cardápio ===");
        System.out.println("1 - Cadastrar Prato");
        System.out.println("2 - Cadastrar Bebida");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        switch (opcao) {
            case 1:
                cadastrarPrato(sc);
                break;
            case 2:
                cadastrarBebida(sc);
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void cadastrarPrato(Scanner sc) {
        System.out.println("\n=== Cadastrar Prato ===");
        
        System.out.print("Nome do prato: ");
        String nome = sc.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        String categoria = escolherCategoriaPrato(sc);
        String tamanho = escolherTamanhoPrato(sc);
        
        System.out.print("Tempo de preparo (minutos): ");
        int tempoPreparo = sc.nextInt();
        sc.nextLine();
        
        Prato prato = new Prato(0, nome, descricao, preco, categoria, tamanho, tempoPreparo);
        listaPratos.add(prato); 
        
        System.out.println("\n Prato cadastrado com sucesso!");
        System.out.println("Preço final: R$ " + String.format("%.2f", prato.calcularPrecoFinal()));
        System.out.println(prato);
    }
    
    private static void cadastrarBebida(Scanner sc) {
        System.out.println("\n=== Cadastrar Bebida ===");
        
        System.out.print("Nome da bebida: ");
        String nome = sc.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        String categoria = escolherCategoriaBebida(sc);
        int volume = escolherVolumeBebida(sc);
        boolean gelada = escolherSeGelada(sc);
        
        Bebida bebida = new Bebida(0, nome, descricao, preco, categoria, volume, gelada);
        listaBebidas.add(bebida); 
        
        System.out.println("\n Bebida cadastrada com sucesso!");
        System.out.println("Preço final: R$ " + String.format("%.2f", bebida.calcularPrecoFinal()));
        System.out.println(bebida);
    }
    
    // ============================================
    // CARDÁPIO - CONSULTAR
    // ============================================
    private static void consultarItensCardapio(Scanner sc) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║   CONSULTAR ITENS DO CARDÁPIO         ║");
        System.out.println("╚═══════════════════════════════════════╝");
        
        if (listaPratos.isEmpty() && listaBebidas.isEmpty()) {
            System.out.println("\n Nenhum item cadastrado no cardápio!");
            return;
        }
        
        // Exibir Pratos
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│           PRATOS                    │");
        System.out.println("└─────────────────────────────────────┘");
        
        if (listaPratos.isEmpty()) {
            System.out.println(" Nenhum prato cadastrado.");
        } else {
            int contador = 1;
            for (Prato prato : listaPratos) {
                System.out.printf("%d. %s%n", contador++, prato);
                System.out.printf("   Preço final: R$ %.2f%n%n", prato.calcularPrecoFinal());
            }
        }
        
        // Exibir Bebidas
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│           BEBIDAS                   │");
        System.out.println("└─────────────────────────────────────┘");
        
        if (listaBebidas.isEmpty()) {
            System.out.println("   Nenhuma bebida cadastrada.");
        } else {
            int contador = 1;
            for (Bebida bebida : listaBebidas) {
                System.out.printf("%d. %s%n", contador++, bebida);
                System.out.printf("   Preço final: R$ %.2f%n%n", bebida.calcularPrecoFinal());
            }
        }
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("Total: %d pratos, %d bebidas%n", 
                         listaPratos.size(), listaBebidas.size());
    }
    
    // ============================================
    // CARDÁPIO - ELIMINAR
    // ============================================
    private static void eliminarItemCardapio(Scanner sc) {
        System.out.println("\n=== Eliminar Item do Cardápio ===");
        System.out.println("1 - Eliminar Prato");
        System.out.println("2 - Eliminar Bebida");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        switch (opcao) {
            case 1:
                eliminarPrato(sc);
                break;
            case 2:
                eliminarBebida(sc);
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void eliminarPrato(Scanner sc) {
        if (listaPratos.isEmpty()) {
            System.out.println("\n Nenhum prato cadastrado!");
            return;
        }
        
        System.out.println("\n=== Eliminar Prato ===");
        System.out.println("Pratos cadastrados:\n");
        
        for (int i = 0; i < listaPratos.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), listaPratos.get(i).getNome());
        }
        
        System.out.print("\nDigite o número do prato a eliminar (0 para cancelar): ");
        int opcao = sc.nextInt();
        sc.nextLine();
        
        if (opcao == 0) {
            System.out.println("Operação cancelada.");
            return;
        }
        
        if (opcao < 1 || opcao > listaPratos.size()) {
            System.out.println(" Opção inválida!");
            return;
        }
        
        Prato pratoRemovido = listaPratos.remove(opcao - 1);
        System.out.println("\n Prato '" + pratoRemovido.getNome() + "' eliminado com sucesso!");
    }
    
    private static void eliminarBebida(Scanner sc) {
        if (listaBebidas.isEmpty()) {
            System.out.println("\n Nenhuma bebida cadastrada!");
            return;
        }
        
        System.out.println("\n=== Eliminar Bebida ===");
        System.out.println("Bebidas cadastradas:\n");
        
        for (int i = 0; i < listaBebidas.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), listaBebidas.get(i).getNome());
        }
        
        System.out.print("\nDigite o número da bebida a eliminar (0 para cancelar): ");
        int opcao = sc.nextInt();
        sc.nextLine();
        
        if (opcao == 0) {
            System.out.println("Operação cancelada.");
            return;
        }
        
        if (opcao < 1 || opcao > listaBebidas.size()) {
            System.out.println(" Opção inválida!");
            return;
        }
        
        Bebida bebidaRemovida = listaBebidas.remove(opcao - 1);
        System.out.println("\nBebida '" + bebidaRemovida.getNome() + "' eliminada com sucesso!");
    }
    
    // ============================================
    // MÉTODOS AUXILIARES - PRATO
    // ============================================
    private static String escolherCategoriaPrato(Scanner sc) {
        System.out.println("\n=== Escolha a Categoria ===");
        System.out.println("1 - " + Prato.CATEGORIA_ENTRADA);
        System.out.println("2 - " + Prato.CATEGORIA_PRINCIPAL);
        System.out.println("3 - " + Prato.CATEGORIA_ACOMPANHAMENTO);
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Prato.CATEGORIA_ENTRADA;
            case 2 -> Prato.CATEGORIA_PRINCIPAL;
            case 3 -> Prato.CATEGORIA_ACOMPANHAMENTO;
            default -> {
                System.out.println("Opção inválida! Usando categoria padrão.");
                yield Prato.CATEGORIA_PRINCIPAL;
            }
        };
    }
    
    private static String escolherTamanhoPrato(Scanner sc) {
        System.out.println("\n=== Escolha o Tamanho ===");
        System.out.println("1 - " + Prato.TAMANHO_P + " (Pequeno) - 20% desconto");
        System.out.println("2 - " + Prato.TAMANHO_M + " (Médio) - Preço normal");
        System.out.println("3 - " + Prato.TAMANHO_G + " (Grande) - +30%");
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Prato.TAMANHO_P;
            case 2 -> Prato.TAMANHO_M;
            case 3 -> Prato.TAMANHO_G;
            default -> {
                System.out.println("Opção inválida! Usando tamanho padrão.");
                yield Prato.TAMANHO_M;
            }
        };
    }
    
    // ============================================
    // MÉTODOS AUXILIARES - BEBIDA
    // ============================================
    private static String escolherCategoriaBebida(Scanner sc) {
        System.out.println("\n=== Escolha a Categoria ===");
        System.out.println("1 - " + Bebida.CATEGORIA_REFRIGERANTE);
        System.out.println("2 - " + Bebida.CATEGORIA_SUCO);
        System.out.println("3 - " + Bebida.CATEGORIA_ALCOOLICA);
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Bebida.CATEGORIA_REFRIGERANTE;
            case 2 -> Bebida.CATEGORIA_SUCO;
            case 3 -> Bebida.CATEGORIA_ALCOOLICA;
            default -> {
                System.out.println("Opção inválida! Usando categoria padrão.");
                yield Bebida.CATEGORIA_REFRIGERANTE;
            }
        };
    }
    
    private static int escolherVolumeBebida(Scanner sc) {
        System.out.println("\n=== Escolha o Volume ===");
        System.out.println("1 - 300ml (Pequeno) - 20% desconto");
        System.out.println("2 - 500ml (Médio) - Preço normal");
        System.out.println("3 - 750ml (Grande) - +20%");
        System.out.println("4 - 1000ml (1 litro) - +30%");
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> 300;
            case 2 -> 500;
            case 3 -> 750;
            case 4 -> 1000;
            default -> {
                System.out.println("Opção inválida! Usando volume padrão.");
                yield 500;
            }
        };
    }
    
    private static boolean escolherSeGelada(Scanner sc) {
        System.out.println("\n=== A bebida é gelada? ===");
        System.out.println("1 - Sim (gelada) +R$1,00");
        System.out.println("2 - Não (natural)");
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return opcao == 1;
    }
    
    // ============================================
    // MESA - CADASTRAR
    // ============================================
    private static void cadastrarMesa(Scanner sc) {
        System.out.println("\n=== Cadastrar Mesa ===");
        
        System.out.print("Número da mesa: ");
        int numero = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Capacidade (quantidade de pessoas): ");
        int capacidade = sc.nextInt();
        sc.nextLine();
        
        String localizacao = escolherLocalizacaoMesa(sc);
        
        Mesa mesa = new Mesa(numero, capacidade, localizacao);
        listaMesas.add(mesa); 
        
        System.out.println("\n Mesa cadastrada com sucesso!");
        System.out.println(mesa);
    }
    
    private static String escolherLocalizacaoMesa(Scanner sc) {
        System.out.println("\n=== Escolha a Localização ===");
        System.out.println("1 - " + Mesa.LOCALIZACAO_JANELA);
        System.out.println("2 - " + Mesa.LOCALIZACAO_CENTRO);
        System.out.println("3 - " + Mesa.LOCALIZACAO_VARANDA);
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine();
        
        return switch (opcao) {
            case 1 -> Mesa.LOCALIZACAO_JANELA;
            case 2 -> Mesa.LOCALIZACAO_CENTRO;
            case 3 -> Mesa.LOCALIZACAO_VARANDA;
            default -> {
                System.out.println("Opção inválida! Usando localização padrão.");
                yield Mesa.LOCALIZACAO_CENTRO;
            }
        };
    }
    
    // ============================================
    // MESA - CONSULTAR
    // ============================================
    private static void consultarMesas(Scanner sc) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║        CONSULTAR MESAS                ║");
        System.out.println("╚═══════════════════════════════════════╝");
        
        if (listaMesas.isEmpty()) {
            System.out.println("\n Nenhuma mesa cadastrada!");
            return;
        }
        
        System.out.println("\n--- Mesas Cadastradas ---\n");
        
        for (int i = 0; i < listaMesas.size(); i++) {
            Mesa mesa = listaMesas.get(i);
            System.out.printf("%d. %s%n", (i + 1), mesa);
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Total: " + listaMesas.size() + " mesas");
    }
    
    // ============================================
    // MESA - ELIMINAR
    // ============================================
    private static void eliminarMesa(Scanner sc) {
        if (listaMesas.isEmpty()) {
            System.out.println("\n Nenhuma mesa cadastrada!");
            return;
        }
        
        System.out.println("\n=== Eliminar Mesa ===");
        System.out.println("Mesas cadastradas:\n");
        
        for (int i = 0; i < listaMesas.size(); i++) {
            Mesa mesa = listaMesas.get(i);
            System.out.printf("%d. Mesa %d - %d pessoas - %s%n", 
                            (i + 1), mesa.getNumero(), mesa.getCapacidade(), mesa.getLocalizacao());
        }
        
        System.out.print("\nDigite o número da opção a eliminar (0 para cancelar): ");
        int opcao = sc.nextInt();
        sc.nextLine();
        
        if (opcao == 0) {
            System.out.println("Operação cancelada.");
            return;
        }
        
        if (opcao < 1 || opcao > listaMesas.size()) {
            System.out.println(" Opção inválida!");
            return;
        }
        
        Mesa mesaRemovida = listaMesas.remove(opcao - 1);
        System.out.println("\n Mesa " + mesaRemovida.getNumero() + " eliminada com sucesso!");
    }
}