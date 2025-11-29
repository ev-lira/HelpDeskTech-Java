public class ArvoreServicos {
    private NoServico raiz;

    public ArvoreServicos() {
        this.raiz = null;
    }

    // --- Inserir Serviço ---
    public void inserir(Servico novoServico) {
        raiz = inserirRecursivo(raiz, novoServico);
    }

    private NoServico inserirRecursivo(NoServico noAtual, Servico novoServico) {
        if (noAtual == null) {
            // Se o nó atual for nulo, encontramos o local de inserção
            return new NoServico(novoServico);
        }
        int codigoAtual = noAtual.servico.getCodigo();
        int novoCodigo = novoServico.getCodigo();

        // Regra da ABB: menor código vai para a esquerda, maior para a direita
        if (novoCodigo < codigoAtual) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, novoServico);
        } else if (novoCodigo > codigoAtual) {
            noAtual.direita = inserirRecursivo(noAtual.direita, novoServico);
        } else {
            // Caso em que o código já existe (opcionalmente, lançar exceção ou ignorar)
            System.out.println("Erro: Serviço com código " + novoCodigo + " já cadastrado.");
        }

        return noAtual;
    }

    // --- Buscar Serviço ---
    public Servico buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Servico buscarRecursivo(NoServico noAtual, int codigo) {
        if (noAtual == null) {
            return null; // Serviço não encontrado
        }

        if (codigo == noAtual.servico.getCodigo()) {
            return noAtual.servico; // Serviço encontrado
        } else if (codigo < noAtual.servico.getCodigo()) {
            return buscarRecursivo(noAtual.esquerda, codigo); // Busca na subárvore esquerda
        } else {
            return buscarRecursivo(noAtual.direita, codigo); // Busca na subárvore direita
        }
    }

    // --- Listar em Ordem (In-Order) ---
    public void exibirEmOrdem() {
        if (raiz == null) {
            System.out.println("A árvore de serviços está vazia.");
            return;
        }
        System.out.println("\n--- Lista de Serviços (Ordem Crescente por Código) ---");
        exibirEmOrdemRecursivo(raiz);
        System.out.println("-----------------------------------------------------");
    }

    // O percurso In-Order garante a ordem crescente dos códigos
    private void exibirEmOrdemRecursivo(NoServico noAtual) {
        if (noAtual != null) {
            exibirEmOrdemRecursivo(noAtual.esquerda);  // Visita a subárvore esquerda
            System.out.println(noAtual.servico);       // Visita o nó atual (imprime)
            exibirEmOrdemRecursivo(noAtual.direita); // Visita a subárvore direita
        }
    }

    // Método para verificar se a árvore está vazia (útil para o menu)
    public boolean estaVazia() {
        return raiz == null;
    }
}
