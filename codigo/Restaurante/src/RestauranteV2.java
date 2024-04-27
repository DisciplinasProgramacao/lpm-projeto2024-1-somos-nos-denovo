import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RestauranteV2 {
    
    private ArrayList<Mesa> mesas;
    private Queue<Requisicao> filaDeEspera;
    private ArrayList<Requisicao> requisicoesAtendidas;
    private ArrayList<Requisicao> requisicoesFinalizadas;
    private ArrayList<Cliente> clientes;
    
    private int MAX_CLIENTES = 56;
    private int MAX_MESAS = 10;
    

   public RestauranteV2() {
        mesas = new ArrayList<>();
        filaDeEspera = new LinkedList<>();
        requisicoesAtendidas = new ArrayList<>();
        requisicoesFinalizadas = new ArrayList<>();
        clientes = new ArrayList<>();
        
        for(int i = 0; i < MAX_MESAS; i++) {
            mesas.add(new Mesa(true));
        }
    }

    public int alocarNaMesa(Requisicao requisicao) {
        for (Mesa mesa : mesas) {
            if (mesa.isDisponibilidade() && mesa.getCapacidade() >= requisicao.getQuantidade()) {
                mesa.setRequisicao(requisicao);
                filaDeEspera.remove(requisicao);
                requisicoesAtendidas.add(requisicao);
                return mesas.indexOf(mesa);
            }
        }
        return -1; // "Todas as mesas estão ocupadas ou não têm capacidade suficiente"
    }

    public boolean desocupar(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setRequisicao(null);
            return true;
        }
        return false; // "A mesa já está desocupada"
    }

    public boolean gerarRequisicao(Cliente cliente) {
        if(clientes.contains(cliente)) {
            Requisicao requisicao = new Requisicao(8, cliente, LocalDate.now(), LocalTime.now(), LocalTime.now());
            filaDeEspera.add(requisicao);
            int mesaIndex = alocarNaMesa(requisicao);
            if(mesaIndex != -1) {
                return true;
            }
        }
        return false; // "Cliente não encontrado ou não foi possível alocar uma mesa"
    }        

    public boolean removerDaFilaDeEspera(Requisicao requisicao) {
        if(filaDeEspera.contains(requisicao)) {
            filaDeEspera.remove(requisicao);
            return true;
        }
        return false; // "Requisição não encontrada na fila de espera"
    }

    public boolean fecharRequisicao(Requisicao requisicao) {
        if(requisicoesAtendidas.contains(requisicao)) {
            requisicoesAtendidas.remove(requisicao);
            requisicoesFinalizadas.add(requisicao);
            for (Mesa mesa : mesas) {
                if (mesa.getRequisicao() != null && mesa.getRequisicao().equals(requisicao)) {
                    desocupar(mesa);
                    break;
                }
            }
            return true;
        }
        return false; // "Requisição não encontrada na lista de requisições atendidas"
    }
    
    public String localizarCliente(Cliente cliente) {
        for (Requisicao requisicao : filaDeEspera) {
            if (requisicao.getCliente().equals(cliente)) {
                return "Cliente na fila de espera na posição: " + (filaDeEspera.indexOf(requisicao) + 1);
            }
        }
        for (Mesa mesa : mesas) {
            if (mesa.getRequisicao() != null && mesa.getRequisicao().getCliente().equals(cliente)) {
                return "Cliente na mesa de ID: " + mesa.getId();
            }
        }
        return "Cliente não esta no Restaurante"; // "Cliente não está na fila de espera nem em uma mesa"
    }        
    
    public String localizarRequisicao(Requisicao requisicao) {
        if (filaDeEspera.contains(requisicao)) {
            return "Requisição na fila de espera na posição: " + (filaDeEspera.indexOf(requisicao) + 1);
        }
        for (Mesa mesa : mesas) {
            if (mesa.getRequisicao() != null && mesa.getRequisicao().equals(requisicao)) {
                return "Requisição na mesa de ID: " + mesa.getId();
            }
        }
        return "Requisição não encontrada"; // "Requisição não está na fila de espera nem em uma mesa"
    }
    
    public LocalTime fecharConta(Requisicao requisicao) {
        if(requisicoesAtendidas.contains(requisicao)) {
            fecharRequisicao(requisicao);
        }
        return null; // "Requisição não encontrada na lista de requisições atendidas"
    }       

}
