package br.com.allidev.packager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaixaResponse {

    private String caixa_id;
    private List<String> produtos;
    private String observacao;

    public CaixaResponse(String caixa_id, List<String> produtos) {
        this.caixa_id = caixa_id;
        this.produtos = produtos;
    }
}
