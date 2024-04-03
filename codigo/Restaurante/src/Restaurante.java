
import java.util.ArrayList;

public class Restaurante {

    // Atributos da classe Restaurante
    private Mesa[] mesas; // Array de mesas
    private ArrayList<Requisicao> filaDeEspera; // Lista de requisições na fila de espera
    private Requisicao requisicao; // Requisição atualmente sendo processada
    private ArrayList<Requisicao> historicoRequisicao; // Lista de todas as requisições atendidas pelo restaurante

    /**
     * Construtor da classe Restaurante.
     * 
     * @param numMesas   Número de mesas no restaurante
     * @param capacidade Capacidade de cada mesa
     */
    public Restaurante(int numMesas, int capacidade) {
        // Inicializa o array de mesas com o número especificado de mesas
        mesas = Mesa.capacidade;
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
        if (filaEspera.isEmpty()) {
            return 0; 
        mesas[numMesa].adicionarClientes(filaEspera.get(0).getNumeroClientes());
        historicoRequisicao.add(filaEspera.get(0));
        filaEspera.remove(0);
        return 1; // Alocado na mesa com sucesso
    }}

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
}
