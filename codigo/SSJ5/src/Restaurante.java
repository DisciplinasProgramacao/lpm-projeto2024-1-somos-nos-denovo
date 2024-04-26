import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private List<Mesa> mesas;

    public Restaurante() {
        this.mesas = new ArrayList<>();
    }
    public void criarMesa(int capacidade) {
        Mesa novaMesa = new Mesa(capacidade, true);
        adicionarMesa(novaMesa);

        System.out.println("Mesa criada com sucesso. ID da Mesa: " + novaMesa.getId());
        
    }

    public  void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
        System.out.println("Mesa adicionada com sucesso. ID da Mesa: " + mesa.getId());
    }


    public boolean alocarNaMesa(Requisicao requisicao) {
        for (Mesa mesa : mesas) {
            if (mesa.getCapacidade() >= requisicao.getQuantidade() && mesa.isDisponibilidade()) {
                mesa.setDisponibilidade(false);
                mesa.setRequisicao(requisicao);
                System.out.println("Requisição alocada na mesa ID: " + mesa.getId());
                return true;
            }
        }
        System.out.println("Nenhuma mesa disponível para alocar a requisição.");
        return false;
    }
    
    public boolean desocuparMesa(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getId() == idMesa) {
                if (!mesa.isDisponibilidade()) {
                    mesa.setDisponibilidade(true);
                    mesa.setRequisicao(null);
                    System.out.println("Mesa ID: " + idMesa + " desocupada com sucesso.");
                    return true;
                } else {
                    System.out.println("Mesa ID: " + idMesa + " já está desocupada.");
                    return false;
                }
            }
        }
        System.out.println("Mesa ID: " + idMesa + " não encontrada.");
        return false;
    }

    public void listarMesas() {
            System.out.println("Lista de todas as mesas:");
            for (Mesa mesa : mesas) {
                String disponibilidade = mesa.isDisponibilidade() ? "Disponível" : "Ocupada";
                System.out.println("Mesa ID: " + mesa.getId() + " - Capacidade: " + mesa.getCapacidade() + " - " + disponibilidade);
            }
        }
    
}
