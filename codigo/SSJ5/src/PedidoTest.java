// package codigo.SSJ5.src;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.List;

// public class PedidoTest {
//     private Requisicao requisicao;
//     private Pedido pedido;

//     @BeforeEach
//     public void setup() {
//         Cliente cliente = new Cliente("Zico");
//         requisicao = new Requisicao(2, cliente, LocalDate.now(), LocalTime.now());
//         pedido = requisicao.getPedido();
//     }

//     @Test
//     public void testCalcularValorTotalEquals() {
//         assertEquals(0.0, pedido.calcularValorTotal());
//     }

//     @Test
//     public void testCalcularValorTotalNotEquals() {
//         Produto produto = new Produto("Pizza", 20);
//         pedido.addProduto(produto);
//         assertNotEquals(0.0, pedido.calcularValorTotal());
//     }

//     @Test
//     public void testCalcularValorFinalEquals() {
//         assertEquals(0.0, pedido.calcularValorFinal());
//     }

//     @Test
//     public void testCalcularValorFinalNotEquals() {
//         Produto produto = new Produto("Pizza", 20);
//         pedido.addProduto(produto);
//         assertNotEquals(0.0, pedido.calcularValorFinal());
//     }

//     @Test
//     public void testDividirContaEquals() {
//         assertEquals((pedido.calcularValorFinal()/0), pedido.dividirConta());
//     }

//     @Test
//     public void testDividirContaNotEquals() {
//         assertNotEquals(-1, pedido.dividirConta());
//     }

//     @Test
//     public void testFormatPedidoTrue() {
//         String pedidoFormatado = pedido.formatPedido();
//         assertTrue(pedidoFormatado.contains("ID Requisição: 0"));
//         assertTrue(pedidoFormatado.contains("Nome do Dono da Requisição: Zico"));
//         assertTrue(pedidoFormatado.contains("Itens do Pedido:"));
//         assertTrue(pedidoFormatado.contains("Preço Total: R$0,00"));
//     }

//     @Test
//     public void testFormatPedidoNotEquals() {
//         Produto produto = new Produto("Pizza", 20);
//         pedido.addProduto(produto);
//         assertNotEquals("", pedido.formatPedido());
//     }

//     @Test
//     public void testFecharContaEquals() {
//         pedido.fecharConta();
//         assertEquals(0.0, pedido.calcularValorTotal());
//     }

//     @Test
//     public void testFecharContaNotEquals() {
//         Produto produto = new Produto("Pizza", 20);
//         pedido.addProduto(produto);
//         pedido.fecharConta();
//         assertNotEquals(0.0, pedido());
//     }

//     @Test
//     public void testcalcularValorTotalEquals() {
//         assertEquals(0.0, pedido.calcularValorTotal());
//     }

//     @Test
//     public void testcalcularValorTotalNotEquals() {
//         assertNotEquals(1.0, pedido.calcularValorTotal());
//     }


//     @Test
//     public void testGetProdutosEquals() {
//         List<Produto> produtos = new ArrayList<>();
//         assertEquals(produtos, pedido.getProdutos());
//     }

//     @Test
//     public void testGetProdutosNotEquals() {
//         Produto produto = new Produto("Pizza", 20);
//         pedido.addProduto(produto);
//         assertNotEquals(new ArrayList<>(), pedido.getProdutos());
//     }
// }