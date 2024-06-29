package com.ssj5.lpm.models;

import com.ssj5.lpm.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MenuFechadoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private MenuFechado menuFechado;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock de produtos do repositório
        List<Produto> produtosMock = new ArrayList<>();
        produtosMock.add(new Produto(1L, "FALAFEL_ASSADO", 20D));
        produtosMock.add(new Produto(2L, "CACAROLA_LEGUMES", 22D));
        produtosMock.add(new Produto(3L, "SALADA_PRIMAVERA", 25D));
        produtosMock.add(new Produto(4L, "COPO_DE_SUCO", 7D));
        produtosMock.add(new Produto(5L, "REFRIGERANTE_ORGANICO", 7D));
        produtosMock.add(new Produto(6L, "TACA_DE_VINHO_VEGANO", 18D));

        // Mock do comportamento do produtoRepository
        when(produtoRepository.save(produtosMock.get(0))).thenReturn(produtosMock.get(0));
        when(produtoRepository.save(produtosMock.get(1))).thenReturn(produtosMock.get(1));
        when(produtoRepository.save(produtosMock.get(2))).thenReturn(produtosMock.get(2));
        when(produtoRepository.save(produtosMock.get(3))).thenReturn(produtosMock.get(3));
        when(produtoRepository.save(produtosMock.get(4))).thenReturn(produtosMock.get(4));
        when(produtoRepository.save(produtosMock.get(5))).thenReturn(produtosMock.get(5));

        // Inicialização do menuFechado
        menuFechado.initProdutos();
    }

    @Test
    public void testMenuSize() {
        assertEquals(6, menuFechado.getMenu().size());
    }
}
