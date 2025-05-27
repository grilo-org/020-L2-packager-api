package br.com.allidev.packager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    private String produto_id;
    private Dimensoes dimensoes;
}
