package com.ssj5.lpm.models;

import com.ssj5.lpm.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RestauranteTests {

    @Autowired
    private Restaurante restaurante;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private PedidoAbertoRepository pedidoAbertoRepository;

    @Autowired
    private PedidoFechadoRepository pedidoFechadoRepository;

    @Autowired
    private MenuFechado menuFechado;

    @BeforeEach
    public void setup() {
        clienteRepository.deleteAll();
        mesaRepository.deleteAll();
        requisicaoRepository.deleteAll();
        pedidoAbertoRepository.deleteAll();
        pedidoFechadoRepository.deleteAll();
    }

    @Test
    public void testInit() {
        restaurante.init();
        List<Mesa> mesas = mesaRepository.findAll();
        assertFalse(mesas.isEmpty());
    }

    @Test
    public void testInitMesasIfNotExists() {
        restaurante.initMesasIfNotExists();
        List<Mesa> mesas = mesaRepository.findAll();
        assertFalse(mesas.isEmpty());
    }

    @Test
    public void testAdicionarCliente() {
        restaurante.adicionarCliente("Cliente Teste");
        List<Cliente> clientes = clienteRepository.findAll();
        assertEquals(1, clientes.size());
        assertEquals("Cliente Teste", clientes.get(0).getNome());
    }

    @Test
    public void testAlocarNaRequisicao() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");

        boolean alocado = restaurante.alocarNaRequisicao(requisicao);
        assertTrue(alocado);
        assertNotNull(requisicao.getMesa());
    }

    @Test
    public void testEntrarNaFilaDeEspera() {
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(6, "Cliente Teste");

        boolean adicionado = restaurante.entrarNaFilaDeEspera(requisicao);
        assertTrue(adicionado);
        assertTrue(restaurante.exibirListaDeEspera().contains("Cliente: Cliente Teste, Quantidade: 6"));
    }

    @Test
    public void testExibirListaDeEspera() {
        List<String> listaEspera = restaurante.exibirListaDeEspera();
        assertNotNull(listaEspera);
    }

    @Test
    public void testDesocuparMesa() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");
        Mesa mesa = requisicao.getMesa();

        restaurante.desocuparMesa(mesa);
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testFecharConta() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");

        Long mesaId = requisicao.getMesa().getId();
        boolean contaFechada = restaurante.fecharConta(mesaId.intValue());
        assertTrue(contaFechada);
        assertEquals(0, requisicaoRepository.count());
    }


    @Test
    public void testGerarRequisicao() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");

        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");
        assertNotNull(requisicao);
        assertNotNull(requisicao.getMesa());
    }

    @Test
    public void testCriarPedido() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");

        Long requisicaoId = requisicao.getId();
        boolean pedidoCriado = restaurante.criarPedido(requisicaoId.intValue(), true);
        assertTrue(pedidoCriado);
        assertTrue(requisicao.getPedido() instanceof PedidoFechado);
    }


    @Test
    public void testAdicionarProduto() {
        restaurante.initMesasIfNotExists();
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");
    
        List<Produto> produtos = menuFechado.getMenu();
        Long idProduto = produtos.get(0).getId();
        boolean produtoAdicionado = restaurante.adicionarProduto(requisicao.getId().intValue(), idProduto, true);
        assertTrue(produtoAdicionado);
        assertEquals(1, requisicao.getPedido().getProdutos().size());
    }
    

    @Test
    public void testLocalizarRequisicao() {
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");

        Optional<Requisicao> requisicaoEncontrada = restaurante.localizarRequisicao(requisicao.getId().intValue());
        assertTrue(requisicaoEncontrada.isPresent());
        assertEquals(requisicao.getId(), requisicaoEncontrada.get().getId());
    }


    @Test
    public void testExibirHistorico() {
        restaurante.adicionarCliente("Cliente Teste");

        String historico = restaurante.exibirHistorico();
        assertTrue(historico.contains("Histórico de Requisições:"));
        assertTrue(historico.contains("Cliente: Cliente Teste"));
    }

    @Test
    public void testExibirMenuAberto() {
        String menuAberto = restaurante.exibirMenu("aberto");
        assertNotNull(menuAberto);
    }

    @Test
    public void testExibirMenuFechado() {
        String menuFechado = restaurante.exibirMenu("fechado");
        assertNotNull(menuFechado);
    }

    @Test
    public void testIsRequisicaoAtendida() {
        restaurante.adicionarCliente("Cliente Teste");
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Cliente Teste");

        boolean requisicaoAtendida = restaurante.isRequisicaoAtendida(requisicao.getId().intValue());
        assertFalse(requisicaoAtendida);
    }

}
