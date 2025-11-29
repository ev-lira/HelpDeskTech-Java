public class NoServico {
    Servico servico;
    NoServico esquerda;
    NoServico direita;

    public NoServico(Servico servico) {
        this.servico = servico;
        this.esquerda = null;
        this.direita = null;
    }
}

