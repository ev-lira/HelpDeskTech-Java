// GerenciadorOperacoes.java
import java.util.Stack;

public class GerenciadorOperacoes {
    private Stack<String> historico;

    public GerenciadorOperacoes() {
        this.historico = new Stack<>();
    }

    // --- 1. Registrar Operação (Empilhar) ---
    public void registrar(String operacao) {
        historico.push(operacao); // 'push' adiciona no topo
    }

    // --- 2. Desfazer Última Operação (Desempilhar) ---
    public String desfazer() {
        if (historico.isEmpty()) {
            return null; // Pilha vazia
        }
        return historico.pop(); // 'pop' remove e retorna o elemento do topo
    }

    // --- 3. Listar Operações (Sem esvaziar) ---
    public void listar() {
        if (historico.isEmpty()) {
            System.out.println("O histórico de operações está vazio.");
            return;
        }

        System.out.println("\n--- HISTÓRICO DE OPERAÇÕES (Topo é a última) ---");
        // Cria uma cópia da pilha para iterar sem alterar a original
        for (String op : historico) {
            System.out.println(op);
        }
        System.out.println("-----------------------------------------------");
    }
}
