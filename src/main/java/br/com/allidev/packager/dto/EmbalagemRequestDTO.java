package br.com.allidev.packager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmbalagemRequestDTO {
    private List<PedidoRequestDTO> pedidos;
}
