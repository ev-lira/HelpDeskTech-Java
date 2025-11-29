// GerenciadorClientes.java
import java.util.ArrayList;
import java.util.List;

public class GerenciadorClientes {
    private List<Cliente> listaClientes;

    public GerenciadorClientes() {
        this.listaClientes = new ArrayList<>();
    }

    // --- 1. Cadastrar Novo Cliente ---
    public void cadastrar(Cliente novoCliente) {
        listaClientes.add(novoCliente);
        System.out.println("✅ Cliente ID " + novoCliente.getId() + " cadastrado com sucesso.");
    }

    // --- 2. Remover Cliente pelo ID ---
    public boolean remover(int id) {
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                listaClientes.remove(c);
                return true;
            }
        }
        return false;
    }

    // --- 3. Buscar Cliente (por ID) ---
    public Cliente buscarPorId(int id) {
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // --- 4. Buscar Cliente (por Nome) ---
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> resultados = new ArrayList<>();
        // Busca ignorando maiúsculas/minúsculas
        String nomeBusca = nome.toLowerCase();
        for (Cliente c : listaClientes) {
            if (c.getNome().toLowerCase().contains(nomeBusca)) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    // --- 5. Listar Todos os Clientes ---
    public void listarTodos() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
        System.out.println("-------------------------");
    }

    public boolean estaVazia() {
        return listaClientes.isEmpty();
    }
}