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

public class MenuAbertoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private MenuAberto menuAberto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock de produtos do repositório
        List<Produto> produtosMock = new ArrayList<>();
        produtosMock.add(new Produto(7L, "FALAFEL_ASSADO", 20D));
        produtosMock.add(new Produto(8L, "CACAROLA_LEGUMES", 22D));
        produtosMock.add(new Produto(9L, "SALADA_PRIMAVERA", 25D));
        produtosMock.add(new Produto(10L, "ESCONDIDINHO_INHAME", 18D));
        produtosMock.add(new Produto(11L, "STROGONOFF_COGUMELOS", 35D));
        produtosMock.add(new Produto(12L, "MOQUECA_PALMITO", 32D));
        produtosMock.add(new Produto(13L, "AGUA", 3D));
        produtosMock.add(new Produto(14L, "COPO_DE_SUCO", 7D));
        produtosMock.add(new Produto(15L, "REFRIGERANTE_ORGANICO", 7D));
        produtosMock.add(new Produto(16L, "CERVEJA_VEGANA", 9D));
        produtosMock.add(new Produto(17L, "TACA_DE_VINHO_VEGANO", 18D));

        // Mock do comportamento do produtoRepository
        when(produtoRepository.save(produtosMock.get(0))).thenReturn(produtosMock.get(0));
        when(produtoRepository.save(produtosMock.get(1))).thenReturn(produtosMock.get(1));
        when(produtoRepository.save(produtosMock.get(2))).thenReturn(produtosMock.get(2));
        when(produtoRepository.save(produtosMock.get(3))).thenReturn(produtosMock.get(3));
        when(produtoRepository.save(produtosMock.get(4))).thenReturn(produtosMock.get(4));
        when(produtoRepository.save(produtosMock.get(5))).thenReturn(produtosMock.get(5));
        when(produtoRepository.save(produtosMock.get(6))).thenReturn(produtosMock.get(6));
        when(produtoRepository.save(produtosMock.get(7))).thenReturn(produtosMock.get(7));
        when(produtoRepository.save(produtosMock.get(8))).thenReturn(produtosMock.get(8));
        when(produtoRepository.save(produtosMock.get(9))).thenReturn(produtosMock.get(9));
        when(produtoRepository.save(produtosMock.get(10))).thenReturn(produtosMock.get(10));

        // Inicialização do menuAberto
        menuAberto.initProdutos();
    }

    @Test
    public void testMenuSize() {
        assertEquals(11, menuAberto.getMenu().size());
    }
}
