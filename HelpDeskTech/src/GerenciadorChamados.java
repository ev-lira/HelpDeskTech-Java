// GerenciadorChamados.java
import java.util.LinkedList;
import java.util.Queue;

public class GerenciadorChamados {
    // Queue é a interface, LinkedList é a implementação mais comum para filas
    private Queue<Chamado> filaChamados;

    public GerenciadorChamados() {
        this.filaChamados = new LinkedList<>();
    }

    // --- 1. Inserir Chamado (Adicionar no final da fila) ---
    public void inserirChamado(Chamado chamado) {
        filaChamados.add(chamado); // 'add' ou 'offer'
        System.out.println("✅ Chamado CÓD " + chamado.getCodigo() + " adicionado à fila.");
    }

    // --- 2. Atender Próximo Chamado (Remover do início da fila) ---
    public Chamado atenderProximo() {
        if (filaChamados.isEmpty()) {
            return null; // Fila vazia
        }
        // 'remove' ou 'poll': remove e retorna o elemento do início da fila
        return filaChamados.poll();
    }

    // --- 3. Listar Todos os Chamados (Sem remover) ---
    public void listarChamados() {
        if (filaChamados.isEmpty()) {
            System.out.println("A fila de chamados está vazia.");
            return;
        }

        System.out.println("\n--- FILA DE CHAMADOS (Ordem de Atendimento) ---");
        int posicao = 1;
        // Percorre a fila sem remover os elementos
        for (Chamado c : filaChamados) {
            System.out.println(posicao++ + ". " + c);
        }
        System.out.println("----------------------------------------------");
    }

    // Método auxiliar
    public boolean estaVazia() {
        return filaChamados.isEmpty();
    }
}