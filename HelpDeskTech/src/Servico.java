public class Servico {
        private int codigo; // A chave de busca na árvore
        private String nome;
        private String descricao;

        public Servico(int codigo, String nome, String descricao) {
            this.codigo = codigo;
            this.nome = nome;
            this.descricao = descricao;
        }

    // Getter essencial para a busca na Árvore
    public int getCodigo() {
        return codigo;
    }

    // Método para exibir o serviço
    @Override
    public String toString() {
        return String.format("CÓD: %d | Serviço: %s | Descrição: %s", codigo, nome, descricao);
    }
}
