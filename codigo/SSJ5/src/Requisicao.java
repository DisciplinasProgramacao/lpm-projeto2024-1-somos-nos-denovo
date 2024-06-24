package codigo.SSJ5.src;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Requisicao {
    private boolean emAtendimento;
    private static long id;
    private int qtdPessoas;
    private Cliente cliente;
    private LocalDate data;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private static int nextId = 0;
    private IPedido pedido;
    private Mesa mesa;

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
       this.horaEntrada = LocalDateTime.now();
       this.qtdPessoas = qtdPessoas;
       this.mesa = mesa;
       this.cliente = cliente;
       this.emAtendimento = emAtendimento;
       this.pedido= pedido;

    }


    public void fecharRequisicao() {
        this.horaSaida = LocalDateTime.now();
        mesa.desocupar();
    
    }
    }
    public boolean sendoAtendida(){
        return emAtendimento;
    }
    public void addPedido(IPedido pedido){
        

    }
    public double valorpessoa(){
        return IPedido.calcularValorFinal()/qtdPessoas;
    }

	public boolean isEmAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(boolean emAtendimento) {
		this.emAtendimento = emAtendimento;
	}

	public static long getId() {
		return id;
	}

	public static void setId(long id) {
		Requisicao.id = id;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalDateTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalDateTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		Requisicao.nextId = nextId;
	}

	public IPedido getPedido() {
		return pedido;
	}

	public void setPedido(IPedido pedido) {
		this.pedido = pedido;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
    }
