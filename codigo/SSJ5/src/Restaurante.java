package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Construtor da classe Restaurante.
 */
public class Restaurante {
    private List<Mesa> listaDeMesas;
    public Queue<Requisicao> filaDeEspera;
    public List<Requisicao> historicoDeRequisicao;
    public List<Cliente> listaDeClientes;
    public Restaurante() {

        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
        // Adiciona 4 mesas com capacidade para 4 pessoas
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(4 , true));
        }
        // Adiciona 4 mesas com capacidade para 6 pessoas
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(6 , true));
        }
        // Adiciona 2 mesas com capacidade para 8 pessoas
        for (int i = 0; i < 2; i++) {
            listaDeMesas.add(new Mesa(8, true));
        }

    }

    /**
     * Retorna a lista de mesas do restaurante.
     * @return A lista de mesas.
     */
    public List<Mesa> getListaDeMesas() {
        return listaDeMesas;
    }

    /**
     * Retorna a fila de espera de requisições do restaurante.
     * @return A fila de espera de requisições.
     */
    public Queue<Requisicao> getFilaDeEspera() {
        return filaDeEspera;
    }

    /**
     * Retorna o histórico de requisições do restaurante.
     * @return A lista de requisições que foram processadas.
     */
    public List<Requisicao> getHistoricoDeRequisicao() {
        return historicoDeRequisicao;
    }

    /**
     * Retorna a lista de clientes do restaurante.
     * @return A lista de clientes.
     */
    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    /**
     * Aloca uma mesa para uma requisição se uma mesa adequada estiver disponível.
     * @param requisicao A requisição que vai ser alocada.
     * @return true para conseguirmos testar.
     */
    public boolean alocarNaRequisicao(Requisicao requisicao) {
        for (Mesa mesaDisponivel : listaDeMesas) {
            if (mesaDisponivel.getCapacidade() >= requisicao.getQuantidadePessoas() && mesaDisponivel.isDisponibilidade()) {
                mesaDisponivel.setDisponibilidade(false);
                requisicao.setMesa(mesaDisponivel);
                System.out.println("Requisição alocada na mesa ID: " + mesaDisponivel.getId());
                return true;
            }
        }
        System.out.println("Nenhuma mesa disponível para alocar a requisição.");
        return false;
    }

    /**
     * Adiciona uma requisição à fila de espera.
     * @param requisicao A requisição que vai ser adicionada à fila de espera.
     * @return true para conseguirmos testar.
     */
    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.offer(requisicao);
        return true;
    }

    /**
     * Exibe a lista de espera.
     */
    public void exibirListaDeEspera() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("Não há clientes na lista de espera.");
        } else {
            System.out.println("Lista de espera:");
            for (Requisicao requisicao : filaDeEspera) {
                System.out.println("Cliente: " + requisicao.getCliente().getNome() + ", Quantidade: " + requisicao.getQuantidade());
            }
        }
    }

    /**
     * Desocupa uma mesa.
     * @param requisicao A requisição associada à mesa que vai ser desocupada.
     * @param mesa A mesa a ser desocupada.
     * @return true para conseguirmos testar.
     */
    public boolean desocuparMesa(Requisicao requisicao, Mesa mesa){
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
        return true;
    }

    /**
     * Fecha a conta de uma requisição e tenta alocar a próxima requisição na fila de espera na mesa.
     * @param requisicao A requisição da conta que será fechada.
     * @return true para conseguirmos testar.
     */
    public boolean fecharConta(Requisicao requisicao) {
        System.out.println("Sua conta da requisicao: "+requisicao.getId()+" foi fechada com sucesso!");
        Mesa mesa = requisicao.getMesa();
        mesa.setDisponibilidade(true);
        if (filaDeEspera.contains(requisicao)) {
            filaDeEspera.remove(requisicao);
        }
        // Verifica a fila de espera e aloca a próxima requisição na mesa se possível
        for (Requisicao r : filaDeEspera) {
            if (mesa.getCapacidade() >= r.getQuantidadePessoas()) {
                r.setMesa(mesa);
                mesa.setDisponibilidade(false);
                filaDeEspera.remove(r);
                System.out.println("Requisição alocada na mesa ID: " + mesa.getId() + " após fechar a conta.");
                break;
            }
        }
        return true;
    }

    /**
     * Gera uma nova requisição.
     * @param quantidade A quantidade de pessoas na requisição.
     * @param nome O nome do cliente responsavel por gerar a requisição.
     * @return A nova requisição.
     */
    public Requisicao gerarRequisicao(int quantidade, String nome){
        Cliente clienteExistente = null;
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getNome().equals(nome)) {
                clienteExistente = cliente;
                break;
            }
        }
        Requisicao requisicao;
        if (clienteExistente != null) {
            requisicao = new Requisicao(quantidade, clienteExistente, LocalDate.now(), LocalTime.now(), null, this);
        } else {
            System.out.println("Cliente previamente nao cadastrado, novo cliente cadastrado de acordo com o nome inserido!");
            Cliente novoCliente = new Cliente(nome);
            listaDeClientes.add(novoCliente);
            requisicao = new Requisicao(quantidade, novoCliente, LocalDate.now(), LocalTime.now(), null, this);
        }
        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
        }

        return requisicao;
    }

    /**
     * Cria um pedido se não houver, e atribui uma lista de produtos a uma requisição;.
     * @param produtos Lista de produtos
     * @param requisicao Requisição que fez o pedido
     * @return
     */
    public Pedido fazerPedido(List<Produto> produtos, Requisicao requisicao){
        if(requisicao.getPedido().calcularValorFinal() == 0){
            Pedido pedido = new Pedido();
            pedido.setProdutos(produtos);
            requisicao.setPedido(pedido);
            return pedido;
        }
        else{
            requisicao.getPedido().addProdutos(produtos);
            return requisicao.getPedido();
        }
    }
}



