package br.com.allidev.packager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoRequestDTO {

    private Long pedido_id;
    private List<ProdutoRequestDTO> produtos;
}