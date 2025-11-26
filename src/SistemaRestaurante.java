import java.util.Scanner;

public class SistemaRestaurante {
    
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
                    break;
                case 2:
                    System.out.println("\n[Consultar Itens do Cardápio] - Em desenvolvimento");
                    break;
                case 3:
                    System.out.println("\n[Atualizar Item do Cardápio] - Em desenvolvimento");
                    break;
                case 4:
                    System.out.println("\n[Eliminar Item do Cardápio] - Em desenvolvimento");
                    break;
                case 5:
                    System.out.println("\n[Cadastrar Mesa] - Em desenvolvimento");
                    break;
                case 6:
                    System.out.println("\n[Consultar Mesas] - Em desenvolvimento");
                    break;
                case 7:
                    System.out.println("\n[Atualizar Mesa] - Em desenvolvimento");
                    break;
                case 8:
                    System.out.println("\n[Eliminar Mesa] - Em desenvolvimento");
                    break;
                case 9:
                    System.out.println("\n[Cadastrar Reserva] - Em desenvolvimento");
                    break;
                case 10:
                    System.out.println("\n[Consultar Reservas] - Em desenvolvimento");
                    break;
                case 11:
                    System.out.println("\n[Atualizar Reserva] - Em desenvolvimento");
                    break;
                case 12:
                    System.out.println("\n[Eliminar Reserva] - Em desenvolvimento");
                    break;
                case 0:
                    System.out.println("\n=================================");
                    System.out.println("  Encerrando o sistema...");
                    System.out.println("  Até logo!");
                    System.out.println("=================================\n");
                    rodar = false;
                    break;
                
                default:
                    System.out.println("\n❌ Opção inválida! Tente novamente.\n");
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
    // CADASTRO DE ITENS DO CARDÁPIO
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
    
    // ============================================
    // CADASTRO DE PRATO
    // ============================================
    private static void cadastrarPrato(Scanner sc) {
        System.out.println("\n=== Cadastrar Prato ===");
        
        System.out.print("Nome do prato: ");
        String nome = sc.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        // Usa os métodos auxiliares
        String categoria = escolherCategoriaPrato(sc);
        String tamanho = escolherTamanhoPrato(sc);
        
        System.out.print("Tempo de preparo (minutos): ");
        int tempoPreparo = sc.nextInt();
        sc.nextLine();
        
        // Cria o objeto Prato (ID será gerado pelo banco, por isso 0)
        Prato prato = new Prato(0, nome, descricao, preco, categoria, tamanho, tempoPreparo);
        
        // Aqui você chamaria o DAO para salvar
        // PratoDAO dao = new PratoDAO();
        // dao.cadastrar(prato);
        
        System.out.println("\n✅ Prato cadastrado com sucesso!");
        System.out.println("Preço final calculado: R$ " + String.format("%.2f", prato.calcularPrecoFinal()));
        System.out.println(prato);
    }
    
    // ============================================
    // MÉTODOS AUXILIARES DE ESCOLHA
    // ============================================
    private static String escolherCategoriaPrato(Scanner sc) {
        System.out.println("\n=== Escolha a Categoria ===");
        System.out.println("1 - " + Prato.CATEGORIA_ENTRADA);
        System.out.println("2 - " + Prato.CATEGORIA_PRINCIPAL);
        System.out.println("3 - " + Prato.CATEGORIA_ACOMPANHAMENTO);
        System.out.print("Opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine(); // limpar buffer
        
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
    // CADASTRO DE BEBIDA
    // ============================================
    private static void cadastrarBebida(Scanner sc) {
        System.out.println("\n=== Cadastrar Bebida ===");
        
        System.out.print("Nome da bebida: ");
        String nome = sc.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = sc.nextDouble();
        sc.nextLine();
        
        // Usa os métodos auxiliares
        String categoria = escolherCategoriaBebida(sc);
        int volume = escolherVolumeBebida(sc);
        boolean gelada = escolherSeGelada(sc);
        
        // Cria o objeto Bebida (variável em minúscula!)
        Bebida bebida = new Bebida(0, nome, descricao, preco, categoria, volume, gelada);
        
        // Aqui você chamaria o DAO para salvar
        // BebidaDAO dao = new BebidaDAO();
        // dao.cadastrar(bebida);
        
        System.out.println("\n✅ Bebida cadastrada com sucesso!");
        System.out.println("Preço final calculado: R$ " + String.format("%.2f", bebida.calcularPrecoFinal()));
        System.out.println(bebida);
    }
    
    // ============================================
    // MÉTODOS AUXILIARES DE BEBIDA
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
        
        return opcao == 1;  // Retorna true se for 1, false caso contrário
    }
    
    // ============================================
    // MÉTODOS FUTUROS (comentados)
    // ============================================
    
    /* 
    private static void consultarItensCardapio(Scanner sc) {
        // Implementar consulta com filtros
    }
    
    private static void atualizarItemCardapio(Scanner sc) {
        // Implementar atualização
    }
    
    private static void eliminarItemCardapio(Scanner sc) {
        // Implementar exclusão
    }
    */

    private static void cadastrarMesa(Scanner sc) {
        System.out.println("\n=== Cadastrar Mesa ===");
        
        System.out.print("Número da mesa: ");
        int numero = sc.nextInt();
        sc.nextLine();
        
        System.out.print("\n=== Escolha a Localização da mesa ===");
        int capacidade = sc.nextInt();
        sc.nextLine();
        
        System.out.print("\n=== Escolha a Localização da mesa ===");
        System.out.print("1 - " + Mesa.LOCALIZACAO_JANELA);
        System.out.print("2 - " + Mesa.LOCALIZACAO_CENTRO);
        System.out.print("3 - " + Mesa.LOCALIZACAO_VARANDA);
        System.out.print("Opção: ");
        int opcao = sc.nextInt();

        return switch (opcao) {
            case 1 -> Mesa.LOCALIZACAO_JANELA;
            case 2 -> Mesa.LOCALIZACAO_CENTRO;
            case 3 -> Mesa.LOCALIZACAO_VARANDA;
            default -> {
                System.out.println("Opção inválida! Usando localização padrão.");
                yield Mesa.LOCALIZACAO_JANELA;
            }
        };
        
        Mesa mesa = new Mesa(numero, capacidade, localizacao);
        
        // Aqui você chamaria o DAO para salvar
        // MesaDAO dao = new MesaDAO();
        // dao.cadastrar(mesa);
        
        System.out.println("\n✅ Mesa cadastrada com sucesso!");
        System.out.println(mesa);
    }

    /*
    
    private static void consultarMesas(Scanner sc) {
        // Implementar consulta de mesas
    }
    
    private static void atualizarMesa(Scanner sc) {
        // Implementar atualização de mesa
    }
    
    private static void eliminarMesa(Scanner sc) {
        // Implementar exclusão de mesa
    }
    
    private static void cadastrarReserva(Scanner sc) {
        // Implementar cadastro de Reserva ou ReservaEspecial
    }
    
    private static void consultarReservas(Scanner sc) {
        // Implementar consulta de reservas
    }
    
    private static void atualizarReserva(Scanner sc) {
        // Implementar atualização de reserva
    }
    
    private static void eliminarReserva(Scanner sc) {
        // Implementar exclusão de reserva
    }
    */
}