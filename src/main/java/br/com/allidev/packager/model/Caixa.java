package br.com.allidev.packager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Caixa {

    CAIXA1("Caixa 1", 30, 40, 80),
    CAIXA2("Caixa 2", 80, 50, 40),
    CAIXA3("Caixa 3", 50, 80, 60);

    private final String nome;
    private final int altura;
    private final int largura;
    private final int comprimento;
}