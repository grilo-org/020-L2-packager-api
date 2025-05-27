package br.com.allidev.packager.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmbalagemRequest {
    private List<PedidoRequest> pedidos;
}
