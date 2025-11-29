public class Cliente {
    private int id;
    private String nome;
    private String email;

    public Cliente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    // Método para exibir o cliente
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Email: %s", id, nome, email);
    }
}

