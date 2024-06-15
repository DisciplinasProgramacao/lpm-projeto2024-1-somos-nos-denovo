// package codigo.SSJ5.src;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.List;

// public class RestauranteTest {

//     private Restaurante restaurante;
//     private Menu menu;

//     @Before
//     public void setUp() {
//         menu = new Menu();
//         restaurante = new Restaurante(menu);
//     }

//     @Test
//     public void testAdicionarClienteCorreto() {
//         restaurante.adicionarCliente("Zico");
//         assertEquals(1, restaurante.getListaDeClientes().size());
//     }

//     @Test
//     public void testAdicionarClienteIncorreto() {
//         restaurante.adicionarCliente("Adílio");
//         assertNotEquals(2, restaurante.getListaDeClientes().size());
//     }

//     @Test
//     public void testAlocarNaRequisicaoCorreto() {
//         restaurante.adicionarCliente("Júnior");
//         Requisicao requisicao = restaurante.gerarRequisicao(4, "Júnior");
//         assertTrue(restaurante.alocarNaRequisicao(requisicao));
//     }

//     @Test
//     public void testAlocarNaRequisicaoIncorreto() {
//         restaurante.adicionarCliente("Leandro");
//         Requisicao requisicao = restaurante.gerarRequisicao(10, "Leandro");
//         assertFalse(restaurante.alocarNaRequisicao(requisicao));
//     }

//     @Test
//     public void testEntrarNaFilaDeEsperaCorreto() {
//         restaurante.adicionarCliente("Zagallo");
//         Requisicao requisicao = restaurante.gerarRequisicao(6, "Zagallo");
//         assertTrue(restaurante.entrarNaFilaDeEspera(requisicao));
//     }

//     @Test
//     public void testEntrarNaFilaDeEsperaIncorreto() {
//         assertFalse(restaurante.exibirListaDeEspera().contains("Cliente: Nunes, Quantidade: 4"));
//     }

//     @Test
//     public void testFecharContaCorreto() {
//         restaurante.adicionarCliente("Jairzinho");
//         Requisicao requisicao = restaurante.gerarRequisicao(8, "Jairzinho");
//         int mesaId = requisicao.getMesa().getId();
//         assertTrue(restaurante.fecharConta(mesaId));
//     }

//     @Test
//     public void testFecharContaIncorreto() {
//         assertFalse(restaurante.fecharConta(99));
//     }

//     @Test
//     public void testGerarRequisicaoCorreto() {
//         Requisicao requisicao = restaurante.gerarRequisicao(4, "Zico");
//         assertNotNull(requisicao);
//     }

//     @Test
//     public void testGerarRequisicaoIncorreto() {
//         assertNull(restaurante.gerarRequisicao(10, "Pelé"));
//     }

//     @Test
//     public void testFazerPedidoCorreto() {
//         restaurante.adicionarCliente("Júnior");
//         Requisicao requisicao = restaurante.gerarRequisicao(2, "Júnior");
//         Produto produto = new Produto("Pizza", 25.0);
//         menu.adicionarProduto(produto);
//         restaurante.fazerPedido(requisicao.getId(), produto.getId());
//         assertEquals(1, requisicao.getPedido().getItens().size());
//     }

//     @Test
//     public void testFazerPedidoIncorreto() {
//         restaurante.adicionarCliente("Nunes");
//         Requisicao requisicao = restaurante.gerarRequisicao(4, "Nunes");
//         Produto produto = new Produto("Sushi", 30.0);
//         restaurante.fazerPedido(requisicao.getId(), produto.getIdProduto());
//         assertTrue(requisicao.getPedido().getItens().isEmpty());
//     }

//     @Test
//     public void testOrdenarMenuFechadoCorreto() {
//         restaurante.adicionarCliente("Romário");
//         Requisicao requisicao = restaurante.gerarRequisicao(2, "Romário");
//         menu.adicionarProduto(new Produto("Falafel Assado", 15.0));
//         menu.adicionarProduto(new Produto("Copo de suco", 5.0));
//         menu.adicionarProduto(new Produto("Refrigerante orgânico", 7.0));
//         restaurante.ordenarMenuFechado(requisicao.getId(), 1, 2, 3);
//         assertEquals(3, requisicao.getPedido().getItens().size());
//     }

//     @Test
//     public void testOrdenarMenuFechadoIncorreto() {
//         restaurante.adicionarCliente("Zico");
//         Requisicao requisicao = restaurante.gerarRequisicao(4, "Zico");
//         menu.adicionarProduto(new Produto("Falafel Assado", 15.0));
//         menu.adicionarProduto(new Produto("Copo de suco", 5.0));
//         menu.adicionarProduto(new Produto("Refrigerante orgânico", 7.0));
//         restaurante.ordenarMenuFechado(requisicao.getId(), 4, 2, 3);
//         assertTrue(requisicao.getPedido().getItens().isEmpty());
//     }

//     @Test
//     public void testExibirHistoricoDeRequisicoesCorreto() {
//         restaurante.adicionarCliente("Zico");
//         Requisicao requisicao = restaurante.gerarRequisicao(2, "Zico");
//         assertTrue(restaurante.exibirHistoricoDeRequisicoes().contains("Cliente: Zico, Quantidade: 2"));
//     }

//     @Test
//     public void testExibirHistoricoDeRequisicoesIncorreto() {
//         restaurante.adicionarCliente("Júnior");
//         Requisicao requisicao = restaurante.gerarRequisicao(4, "Júnior");
//         assertFalse(restaurante.exibirHistoricoDeRequisicoes().contains("Cliente: Pelé, Quantidade: 3"));
//     }

//     @Test
//     public void testExibirPedidosCorreto() {
//         restaurante.adicionarCliente("Renato Gaúcho");
//         Requisicao requisicao = restaurante.gerarRequisicao(3, "Renato Gaúcho");
//         menu.adicionarProduto(new Produto("Café", 5.0));
//         restaurante.fazerPedido(requisicao.getId(), 1);
//         assertTrue(restaurante.exibirPedidos().contains("Café - 5.0"));
//     }

//     @Test
//     public void testExibirPedidosIncorreto() {
//         assertFalse(restaurante.exibirPedidos().contains("Pizza - 25.0"));
//     }
// }