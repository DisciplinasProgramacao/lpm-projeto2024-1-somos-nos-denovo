import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalTime;


public class Restaurante {

    private Mesa[] mesas;
    private ArrayList<Requisicao> filaDeEspera;
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
            mesas[i] = new Mesa(capacidade, true);
        }

        filaDeEspera = new ArrayList<>();
        historicoRequisicao = new ArrayList<>();
    }

    /**
     * Método para obter o número da mesa da requisição.
     * 
     * @param numMesa Número da mesa
     * @return O número da mesa da requisição
     */
    public int getNumeroMesa(int numMesa) {
        return numMesa;
    }

    /**
     * Método para obter o número de clientes da requisição.
     * 
     * @param requisicao Requisição
     * @return O número de clientes da requisição
     */
    public int getNumeroClientes(Requisicao requisicao) {
        return requisicao.getQuantidade();
    }

    /**
     * Método para alocar clientes em uma mesa a partir da fila de espera.
     * 
     * @param requisicao Requisição na fila de espera
     * @param numMesa    Número da mesa
     * @return 1 se os clientes foram alocados com sucesso, 0 se a fila de espera
     *         estiver vazia e -1 se o número da mesa for inválido
     */
    public int alocarNaMesa(Requisicao requisicao, int numMesa) {
        // Verifica se a requisição é nula
        if (requisicao == null) {
            return -1;
        }

        int mesaNumber = getNumeroMesa(numMesa); // Obtém o número da mesa da requisição

        // Verifica se o número da mesa é válido
        if (mesaNumber < 0 || mesaNumber >= mesas.length) {
            return -1; // Mesa inválida
        }

        if (!filaDeEspera.isEmpty()) {
            mesas[numMesa].setRequisicao(requisicao);
            historicoRequisicao.add(requisicao);
            filaDeEspera.remove(requisicao);
            return 1; // Alocado na mesa com sucesso
        } else {
            return 0; // Fila de espera está vazia
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
        if (requisicao == null) {
            return false; // Se a requisição fornecida for nula, não é possível desocupar a mesa
        }

        for (Mesa mesa : mesas) {
            if (mesa.getRequisicao() != null && mesa.getRequisicao().equals(requisicao)) {
                mesa.setRequisicao(null); // Desocupar a mesa atribuindo null à requisição associada
                return true;
            }
        }
        return false; // Mesa não encontrada ou requisição nula
    }

    /**
     * Método para localizar um cliente no restaurante.
     * 
     * @param cliente Cliente a ser localizado
     * @return O índice do array de mesas onde o cliente foi encontrado, -1 se não
     *         encontrado
     */
    public int localizarCliente(Cliente cliente) {
        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i].getRequisicao() != null && mesas[i].getRequisicao().getCliente().equals(cliente)) {
                return i; // Retorna o índice onde o cliente foi encontrado no array de mesas
            }
        }
        return -1; // Cliente não encontrado no restaurante
    }

    /**
     * Método para localizar uma requisição no restaurante.
     * 
     * @param requisicao Requisição a ser localizada
     * @return O índice do array de mesas onde a requisição foi encontrada, -1 se não
     *         encontrada
     */
    public int localizarRequisicao(Requisicao requisicao) {
        // Verifica em cada mesa se a requisição está alocada
        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i].getRequisicao() != null && mesas[i].getRequisicao().equals(requisicao)) {
                return i; // Retorna o índice onde a requisição foi encontrada no array de mesas
            }
        }
        return -1; // Requisição não encontrada em nenhuma mesa
    }

    /**
     * Método para fechar a conta e calcular a hora de saída.
     * 
     * @param mesas Um array de objetos Mesa que representam as mesas do restaurante
     * @param requisicao A requisição a ser fechada
     * @return A hora de saída após fechar a conta ou null se a requisição não
     *         estiver associada a nenhuma mesa
     */
    public LocalTime fecharConta(Mesa[] mesas, Requisicao requisicao) {
        // Verifica se a mesa em que a requisição está foi encontrada
        boolean mesaEncontrada = false;
        for (Mesa mesa : mesas) {
            // Verifica se a requisição está associada à mesa atual
            if (mesa.getRequisicao() != null && mesa.getRequisicao().equals(requisicao)) {
                mesaEncontrada = true;

                // Define a duração padrão da refeição (1 hora)
                Duration duracaoRefeicao = Duration.ofHours(1);

                // Calcula o horário de saída com base no horário de entrada e na duração da refeição
                LocalTime horaSaida = requisicao.getHoraEntrada().plus(duracaoRefeicao);

                // Define o horário de saída da requisição
                requisicao.setHoraSaida(horaSaida);

                // Fecha a requisição no restaurante
                fecharRequisicao(requisicao);

                // Retorna o horário de saída
                return horaSaida;
            }
        }

        // Retorna null se a requisição não estiver associada a nenhuma mesa
        if (!mesaEncontrada) {
            return null;
        }
        return null;
    }

}
