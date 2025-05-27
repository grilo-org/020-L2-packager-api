package br.com.allidev.packager.service;

import br.com.allidev.packager.dto.request.PedidoRequest;
import br.com.allidev.packager.dto.response.EmbalagemResponse;

import java.util.List;

public interface EmbalagemService {
    EmbalagemResponse calcularEmbalagem(List<PedidoRequest> pedidos);
}
