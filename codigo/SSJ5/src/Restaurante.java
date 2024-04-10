import java.util.ArrayList;

public class Restaurante {

    private Mesa[] mesas;
    private ArrayList<Requisicao> filaDeEspera;
    private Requisicao requisicao;
    private ArrayList<Requisicao> historicoRequisicao;

    /**
     * Construtor da classe Restaurante.
     * 
     * @param numMesas   Número de mesas no restaurante
     * @param capacidade Capacidade de cada mesa
     */
    public Restaurante(int numMesas, int capacidade) {
        // Inicializa o array de mesas com o número especificado de mesas
        mesas = new Mesa[numMesas];
        // Instancia cada mesa com a capacidade especificada
        for (int i = 0; i < numMesas; i++) {
            mesas[i] = new Mesa(capacidade);
        }
        // Inicializa a lista de requisições na fila de espera e o histórico de
        // requisições
        filaDeEspera = new ArrayList<>();
        historicoRequisicao = new ArrayList<>();
    }

    /**
     * Método para alocar clientes em uma mesa a partir da fila de espera.
     * 
     * @param filaEspera Lista de requisições na fila de espera
     * @param numMesa    Número da mesa onde os clientes serão alocados
     * @return 1 se os clientes foram alocados com sucesso, 0 se a fila de espera
     *         estiver vazia e -1 se o número da mesa for inválido
     */
    public int alocarNaMesa(ArrayList<Requisicao> filaEspera, int numMesa) {
        // Verifica se o número da mesa é válido
        if (numMesa < 0 || numMesa >= mesas.length) {
            return -1; // Mesa inválida
        }
        if (filaEspera.isEmpty() || filaEspera != '\0') {
            return 0;
            mesas[numMesa].adicionarClientes(filaEspera.get(0).getNumeroClientes());
            historicoRequisicao.add(filaEspera.get(0));
            filaEspera.remove(0);
            return 1; // Alocado na mesa com sucesso
        }
    }

    /**
     * Método para adicionar uma requisição à fila de espera.
     * 
     * @param requisicao Requisição a ser adicionada à fila de espera
     * @return O tamanho da fila de espera após a adição da requisição
     */
    public int entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
        return filaDeEspera.size();
    }

    /**
     * Método para remover uma requisição da fila de espera.
     * 
     * @param requisicao Requisição a ser removida da fila de espera
     * @return O tamanho da fila de espera após a remoção da requisição
     */
    public int removerDaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.remove(requisicao);
        return filaDeEspera.size();

    }

    /**
     * Método para fechar uma requisição.
     * 
     * @param requisicao Requisição a ser fechada
     * @return true se a requisição foi fechada com sucesso, false caso contrário
     */
    public boolean fecharRequisicao(Requisicao requisicao) {
        // Verifica se a requisição está no histórico
        if (historicoRequisicao.contains(requisicao)) {
            historicoRequisicao.remove(requisicao); // Remove a requisição do histórico
            return true;
        } else {
            return false; // Requisição não encontrada no histórico
        }
    }

    /**
     * Método para desocupar uma mesa.
     * 
     * @param requisicao Requisição associada à mesa a ser desocupada
     * @return true se a mesa foi desocupada com sucesso, false caso contrário
     */
    public boolean desocupar(Requisicao requisicao) {
        for (Mesa mesa : mesas) {
            if (mesa.getRequisicao().equals(requisicao)) {
                mesa.desocupar();
                return true;
            }
        }
        return false;
    }

    /**
     * Método para localizar um cliente no restaurante.
     * 
     * @param cliente Cliente a ser localizado
     * @return Retorna o indice do array onde o cliente foi encontrado, caso cliente nao encontrado retorna -1
     */
    public int localizarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].equals(cliente)) {
                return i; // Retorna o índice onde o cliente foi encontrado no array
            }
        }
        return -1; // Cliente não encontrado no array
    }

}
