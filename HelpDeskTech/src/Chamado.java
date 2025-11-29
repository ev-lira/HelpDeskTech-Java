public class Chamado {
    private int codigo;
    private String nomeCliente;
    private String descricao;

    public Chamado(int codigo, String nomeCliente, String descricao) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.descricao = descricao;
    }
    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getDescricao() {
        return descricao;
    }
    // Método para exibir o chamado de forma legível
    @Override
    public String toString() {
        return String.format("CÓD: %d | Cliente: %s | Descrição: %s", codigo, nomeCliente, descricao);
    }
}