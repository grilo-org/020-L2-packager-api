package br.com.allidev.packager.service.impl;

import br.com.allidev.packager.dto.request.PedidoRequest;
import br.com.allidev.packager.dto.request.ProdutoRequest;
import br.com.allidev.packager.dto.response.CaixaResponse;
import br.com.allidev.packager.dto.response.EmbalagemResponse;
import br.com.allidev.packager.dto.response.PedidoResponse;
import br.com.allidev.packager.model.Caixa;
import br.com.allidev.packager.model.Produto;
import br.com.allidev.packager.service.EmbalagemService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmbalagemServiceImpl implements EmbalagemService {

    @Override
    public EmbalagemResponse calcularEmbalagem(List<PedidoRequest> pedidos) {
        List<PedidoResponse> respostasPedidos = pedidos.stream()
                .map(this::processarPedido)
                .collect(Collectors.toList());

        return new EmbalagemResponse(respostasPedidos);
    }

    private PedidoResponse processarPedido(PedidoRequest pedido) {
        List<CaixaResponse> caixas = empacotarProdutos(pedido.getProdutos());
        return new PedidoResponse(pedido.getPedido_id(), caixas);
    }

    private List<CaixaResponse> empacotarProdutos(List<ProdutoRequest> produtos) {
        List<CaixaResponse> caixas = new ArrayList<>();
        List<Produto> produtosParaEmpacotar = converterProdutos(produtos);

        while (!produtosParaEmpacotar.isEmpty()) {
            Produto produtoAtual = produtosParaEmpacotar.getFirst();
            Optional<Caixa> caixaOpt = encontrarCaixaParaProduto(produtoAtual);

            if (caixaOpt.isEmpty()) {
                caixas.add(criarCaixaInvalida(produtoAtual));
                produtosParaEmpacotar.removeFirst();
                continue;
            }

            Caixa caixa = caixaOpt.get();
            List<Produto> produtosNaCaixa = new ArrayList<>();
            produtosNaCaixa.add(produtoAtual);
            produtosParaEmpacotar.removeFirst();

            // Tentar adicionar mais produtos na mesma caixa
            Iterator<Produto> iterator = produtosParaEmpacotar.iterator();
            while (iterator.hasNext()) {
                Produto produto = iterator.next();
                if (produtoCabeNaCaixa(produto, caixa)) {
                    produtosNaCaixa.add(produto);
                    iterator.remove();
                }
            }

            caixas.add(criarCaixaValida(caixa, produtosNaCaixa));
        }

        return caixas;
    }

    private List<Produto> converterProdutos(List<ProdutoRequest> produtos) {
        return produtos.stream()
                .map(p -> new Produto(p.getProduto_id(), p.getDimensoes()))
                .sorted(Comparator.comparingInt((Produto p) ->
                        -p.getDimensoes().getAltura() *
                                p.getDimensoes().getLargura() *
                                p.getDimensoes().getComprimento()))
                .collect(Collectors.toList());
    }

    private Optional<Caixa> encontrarCaixaParaProduto(Produto produto) {
        return Arrays.stream(Caixa.values())
                .filter(caixa -> produtoCabeNaCaixa(produto, caixa))
                .min(Comparator.comparingInt(c ->
                        c.getAltura() * c.getLargura() * c.getComprimento()));
    }

    private boolean produtoCabeNaCaixa(Produto produto, Caixa caixa) {
        return produto.getDimensoes().getAltura() <= caixa.getAltura() &&
                produto.getDimensoes().getLargura() <= caixa.getLargura() &&
                produto.getDimensoes().getComprimento() <= caixa.getComprimento();
    }

    private CaixaResponse criarCaixaValida(Caixa caixa, List<Produto> produtos) {
        return new CaixaResponse(
                caixa.getNome(),
                produtos.stream()
                        .map(Produto::getProduto_id)
                        .collect(Collectors.toList())
        );
    }

    private CaixaResponse criarCaixaInvalida(Produto produto) {
        return new CaixaResponse(
                null,
                Collections.singletonList(produto.getProduto_id()),
                "Produto não cabe em nenhuma caixa disponível."
        );
    }
}