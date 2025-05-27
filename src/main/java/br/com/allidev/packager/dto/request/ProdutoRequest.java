package br.com.allidev.packager.dto.request;

import br.com.allidev.packager.model.Dimensoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoRequest {

    private String produto_id;
    private Dimensoes dimensoes;
}