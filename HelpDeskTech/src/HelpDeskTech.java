// HelpDeskTech.java
import java.util.List;
import java.util.Scanner;

public class HelpDeskTech {

    // Instâncias dos gerenciadores
    private static GerenciadorChamados gestorChamados = new GerenciadorChamados();
    private static GerenciadorOperacoes gestorOperacoes = new GerenciadorOperacoes();
    private static GerenciadorClientes gestorClientes = new GerenciadorClientes();
    private static ArvoreServicos gestorServicos = new ArvoreServicos();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicialização de dados para facilitar testes
        inicializarDados();

        // Laço principal do programa
        int opcao;
        do {
            exibirMenuPrincipal();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha

                switch (opcao) {
                    case 1:
                        menuFilaChamados();
                        break;
                    case 2:
                        menuPilhaOperacoes();
                        break;
                    case 3:
                        menuListaClientes();
                        break;
                    case 4:
                        menuArvoreServicos();
                        break;
                    case 0:
                        System.out.println("\nSaindo do sistema HelpDeskTech. Até mais!");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("❌ Entrada inválida. Digite um número.");
                scanner.nextLine(); // Limpa a entrada
                opcao = -1;
            }
        } while (opcao != 0);

        scanner.close();
    }

    // --- Métodos de Exibição de Menus ---

    private static void exibirMenuPrincipal() {
        System.out.println("\n====================================");
        System.out.println("==== MENU PRINCIPAL - HELPDESKTECH ====");
        System.out.println("1 - Gerenciar Fila de Chamados");
        System.out.println("2 - Gerenciar Pilha de Operações");
        System.out.println("3 - Gerenciar Lista de Clientes");
        System.out.println("4 - Gerenciar Árvore de Serviços");
        System.out.println("0 - Sair");
        System.out.println("====================================");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirSubMenu(String titulo, String[] opcoes) {
        System.out.println("\n--- SUB-MENU: " + titulo + " ---");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + " - " + opcoes[i]);
        }
        System.out.println("0 - Voltar ao Menu Principal");
        System.out.println("------------------------------------");
        System.out.print("Escolha uma opção: ");
    }

    // --- 1. Menu Fila de Chamados ---
    private static void menuFilaChamados() {
        int opcao;
        do {
            exibirSubMenu("FILA DE CHAMADOS", new String[]{
                    "Inserir novo Chamado",
                    "Atender próximo Chamado",
                    "Listar todos os Chamados"
            });
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    inserirChamado();
                    break;
                case 2:
                    atenderChamado();
                    break;
                case 3:
                    gestorChamados.listarChamados();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void inserirChamado() {
        System.out.print("Digite o código do chamado (número): ");
        int codigo = lerInteiro();
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a breve descrição: ");
        String descricao = scanner.nextLine();

        Chamado novoChamado = new Chamado(codigo, nome, descricao);
        gestorChamados.inserirChamado(novoChamado);

        // Requisito 3: Registrar operação na pilha
        gestorOperacoes.registrar("Inseriu Chamado CÓD: " + codigo + " (" + nome + ")");
    }

    private static void atenderChamado() {
        Chamado atendido = gestorChamados.atenderProximo();
        if (atendido != null) {
            System.out.println("\n🎉 Chamado ATENDIDO:");
            System.out.println(atendido);

            // Requisito 3: Registrar operação na pilha
            gestorOperacoes.registrar("Atendeu Chamado CÓD: " + atendido.getCodigo() + " (" + atendido.getNomeCliente() + ")");
        } else {
            System.out.println("⚠️ Não há chamados na fila para atendimento.");
        }
    }

    // --- 2. Menu Pilha de Operações ---
    private static void menuPilhaOperacoes() {
        int opcao;
        do {
            exibirSubMenu("PILHA DE OPERAÇÕES", new String[]{
                    "Desfazer última operação",
                    "Listar operações registradas"
            });
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    desfazerOperacao();
                    break;
                case 2:
                    gestorOperacoes.listar();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void desfazerOperacao() {
        String operacaoDesfeita = gestorOperacoes.desfazer();
        if (operacaoDesfeita != null) {
            System.out.println("\n⏪ Operação Desfeita (Removida da Pilha):");
            System.out.println(">> " + operacaoDesfeita);
        } else {
            System.out.println("⚠️ O histórico de operações está vazio.");
        }
    }

    // --- 3. Menu Lista de Clientes ---
    private static void menuListaClientes() {
        int opcao;
        do {
            exibirSubMenu("LISTA DE CLIENTES", new String[]{
                    "Cadastrar novo Cliente",
                    "Remover Cliente por ID",
                    "Buscar Cliente por ID ou Nome",
                    "Listar todos os Clientes"
            });
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    removerCliente();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    gestorClientes.listarTodos();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o ID do cliente (número): ");
        int id = lerInteiro();
        System.out.print("Digite o Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o Email do cliente: ");
        String email = scanner.nextLine();

        Cliente novoCliente = new Cliente(id, nome, email);
        gestorClientes.cadastrar(novoCliente);

        gestorOperacoes.registrar("Cadastrou Cliente ID: " + id + " (" + nome + ")");
    }

    private static void removerCliente() {
        if (gestorClientes.estaVazia()) {
            System.out.println("Não há clientes cadastrados para remover.");
            return;
        }
        System.out.print("Digite o ID do cliente a ser removido: ");
        int id = lerInteiro();

        if (gestorClientes.remover(id)) {
            System.out.println("✅ Cliente ID " + id + " removido com sucesso.");
            gestorOperacoes.registrar("Removeu Cliente ID: " + id);
        } else {
            System.out.println("❌ Cliente com ID " + id + " não encontrado.");
        }
    }

    private static void buscarCliente() {
        System.out.print("Buscar por (1 - ID, 2 - Nome): ");
        int tipoBusca = lerInteiro();

        if (tipoBusca == 1) {
            System.out.print("Digite o ID do cliente: ");
            int id = lerInteiro();
            Cliente c = gestorClientes.buscarPorId(id);
            if (c != null) {
                System.out.println("\n✅ Cliente encontrado:");
                System.out.println(c);
            } else {
                System.out.println("❌ Cliente com ID " + id + " não encontrado.");
            }
        } else if (tipoBusca == 2) {
            System.out.print("Digite parte ou o nome completo: ");
            String nome = scanner.nextLine();
            List<Cliente> resultados = gestorClientes.buscarPorNome(nome);

            if (resultados.isEmpty()) {
                System.out.println("❌ Nenhum cliente encontrado com o nome '" + nome + "'.");
            } else {
                System.out.println("\n✅ Clientes encontrados:");
                for (Cliente c : resultados) {
                    System.out.println(c);
                }
            }
        } else {
            System.out.println("❌ Opção de busca inválida.");
        }
    }


    // --- 4. Menu Árvore de Serviços ---
    private static void menuArvoreServicos() {
        int opcao;
        do {
            exibirSubMenu("ÁRVORE DE SERVIÇOS", new String[]{
                    "Inserir novo Serviço",
                    "Buscar Serviço por Código",
                    "Listar serviços em ordem (In-Order)"
            });
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    inserirServico();
                    break;
                case 2:
                    buscarServico();
                    break;
                case 3:
                    gestorServicos.exibirEmOrdem();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void inserirServico() {
        System.out.print("Digite o código do serviço (chave de busca): ");
        int codigo = lerInteiro();
        System.out.print("Digite o nome do serviço: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do serviço: ");
        String descricao = scanner.nextLine();

        Servico novoServico = new Servico(codigo, nome, descricao);
        gestorServicos.inserir(novoServico);

        gestorOperacoes.registrar("Inseriu Serviço CÓD: " + codigo + " (" + nome + ")");
    }

    private static void buscarServico() {
        if (gestorServicos.estaVazia()) {
            System.out.println("A árvore de serviços está vazia.");
            return;
        }
        System.out.print("Digite o código do serviço a ser buscado: ");
        int codigo = lerInteiro();

        Servico s = gestorServicos.buscar(codigo);
        if (s != null) {
            System.out.println("\n✅ Serviço encontrado:");
            System.out.println(s);
        } else {
            System.out.println("❌ Serviço com código " + codigo + " não encontrado.");
        }
    }

    // --- Métodos Auxiliares ---

    // Método para ler um inteiro com tratamento de erro
    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Entrada inválida. Por favor, digite um número inteiro.");
            scanner.nextLine(); // Limpa a entrada
            System.out.print("Digite novamente: ");
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha após o número
        return num;
    }

    private static int lerOpcao() {
        if (scanner.hasNextInt()) {
            int op = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            return op;
        } else {
            scanner.nextLine(); // Limpa a entrada
            return -1; // Retorna inválido
        }
    }

    // Inicializa algumas estruturas com dados de exemplo
    private static void inicializarDados() {
        // Fila
        gestorChamados.inserirChamado(new Chamado(101, "Alice Silva", "Problema com impressora."));
        gestorChamados.inserirChamado(new Chamado(102, "Bruno Costa", "Acesso ao servidor lento."));

        // Pilha (registros iniciais)
        gestorOperacoes.registrar("Inicializou sistema");
        gestorOperacoes.registrar("Inseriu Chamado CÓD: 101");
        gestorOperacoes.registrar("Inseriu Chamado CÓD: 102");

        // Lista
        gestorClientes.cadastrar(new Cliente(1, "Carlos Mendes", "carlos@mail.com"));
        gestorClientes.cadastrar(new Cliente(2, "Diana Souza", "diana@mail.com"));

        // Árvore (códigos para teste de ABB: 50, 30, 70, 20, 40)
        gestorServicos.inserir(new Servico(50, "Instalação de Software", "Instalação remota de pacote Office."));
        gestorServicos.inserir(new Servico(30, "Suporte Nível 1", "Atendimento de primeiro contato."));
        gestorServicos.inserir(new Servico(70, "Backup de Dados", "Criação de rotina de backup semanal."));
        gestorServicos.inserir(new Servico(20, "Configuração de Rede", "Ajuste de DHCP e DNS."));
        gestorServicos.inserir(new Servico(40, "Treinamento de Equipe", "Sessão sobre nova ferramenta."));
    }
}