
/**
 * Classe que representa uma Mesa com atributos de capacidade, disponibilidade, id e requisição.
 */
public class Mesa {
    private int capacidade;
    private boolean disponibilidade;
    private int id;
    private Requisicao requisicao;

    //Gerar os ids dentro da classe Mesa 
    /**
     * Construtor da classe Mesa.
     *
     * @param capacidade A capacidade da mesa.
     * @param id O identificador da mesa.
     */
    public Mesa(int capacidade, int id) {
        this.capacidade = capacidade;
        this.id = id;
        this.disponibilidade = true; // A mesa está disponível inicialmente
        //A mesa não vai ter a informação da requisição, a requisição que vai ter o id da mesa
        this.requisicao = null; // Sem requisição inicialmente
    }

    // DESOCUPAR E OCUPAR É DA MESA
    /**
     * Método para ocupar a mesa com uma determinada requisição, se estiver disponível.
     *
     * @param requisicao A requisição para ocupar a mesa.
     * @return Verdadeiro se a mesa foi ocupada com sucesso, falso caso contrário.
     */
    public boolean ocupar(Requisicao requisicao) {
        if (disponibilidade) {
            this.requisicao = requisicao;
            disponibilidade = false;
            return true;
        }
        return false;
    }

    /**
     * Método para desocupar a mesa de uma determinada requisição, se estiver ocupada.
     *
     * @param requisicao A requisição para desocupar a mesa.
     * @return Verdadeiro se a mesa foi desocupada com sucesso, falso caso contrário.
     */
    public boolean desocupar(Requisicao requisicao) {
        if (!disponibilidade && this.requisicao.equals(requisicao)) {
            disponibilidade = true;
            this.requisicao = null;
            return true;
        }
        return false;
    }
}

