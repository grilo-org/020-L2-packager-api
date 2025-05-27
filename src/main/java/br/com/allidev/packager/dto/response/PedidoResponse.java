package br.com.allidev.packager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoResponse {

    private Long pedido_id;
    private List<CaixaResponse> caixas;
}
