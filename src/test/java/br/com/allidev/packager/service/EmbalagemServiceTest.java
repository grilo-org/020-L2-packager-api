package br.com.allidev.packager.service;

import br.com.allidev.packager.dto.*;
import br.com.allidev.packager.model.Dimensoes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmbalagemServiceTest {

    private EmbalagemService embalagemService;

    @BeforeEach
    void setUp() {
        embalagemService = new EmbalagemService();
    }

    @Test
    void testCalcularEmbalagem_ProdutoUnicoNaCaixaCorreta() {
        ProdutoRequestDTO produto = new ProdutoRequestDTO("PS5", new Dimensoes(40, 10, 25));
        PedidoRequestDTO pedido = new PedidoRequestDTO(1L, List.of(produto));

        EmbalagemResponseDTO response = embalagemService.calcularEmbalagem(List.of(pedido));

        assertEquals(1, response.getPedidos().size());
        PedidoResponseDTO pedidoResponse = response.getPedidos().getFirst();
        assertEquals(1, pedidoResponse.getCaixas().size());
        assertEquals("Caixa 2", pedidoResponse.getCaixas().getFirst().getCaixa_id());
        assertEquals("PS5", pedidoResponse.getCaixas().getFirst().getProdutos().getFirst());
    }

    @Test
    void testCalcularEmbalagem_MultiplosProdutosNaMesmaCaixa() {
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("Joystick", new Dimensoes(15, 20, 10));
        ProdutoRequestDTO p2 = new ProdutoRequestDTO("Fifa 24", new Dimensoes(10, 30, 10));
        PedidoRequestDTO pedido = new PedidoRequestDTO(2L, List.of(p1, p2));

        EmbalagemResponseDTO response = embalagemService.calcularEmbalagem(List.of(pedido));

        assertEquals(1, response.getPedidos().getFirst().getCaixas().size());
        assertEquals(2, response.getPedidos().getFirst().getCaixas().getFirst().getProdutos().size());
    }

    @Test
    void testCalcularEmbalagem_ProdutoGrandeSemCaixa() {
        ProdutoRequestDTO produto = new ProdutoRequestDTO("Cadeira Gamer", new Dimensoes(120, 60, 70));
        PedidoRequestDTO pedido = new PedidoRequestDTO(5L, List.of(produto));

        EmbalagemResponseDTO response = embalagemService.calcularEmbalagem(List.of(pedido));
        CaixaResponseDTO caixa = response.getPedidos().getFirst().getCaixas().getFirst();

        assertNull(caixa.getCaixa_id());
        assertEquals("Produto não cabe em nenhuma caixa disponível.", caixa.getObservacao());
    }

    @Test
    void testCalcularEmbalagem_PedidoVazio() {
        PedidoRequestDTO pedido = new PedidoRequestDTO(3L, List.of());
        EmbalagemResponseDTO response = embalagemService.calcularEmbalagem(List.of(pedido));

        assertTrue(response.getPedidos().getFirst().getCaixas().isEmpty());
    }

}