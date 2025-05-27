package br.com.allidev.packager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoRequest {

    private Long pedido_id;
    private List<ProdutoRequest> produtos;
}