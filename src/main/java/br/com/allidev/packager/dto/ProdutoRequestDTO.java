package br.com.allidev.packager.dto;

import br.com.allidev.packager.model.Dimensoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoRequestDTO {

    private String produto_id;
    private Dimensoes dimensoes;
}