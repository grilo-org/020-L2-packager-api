package br.com.allidev.packager.controller;

import br.com.allidev.packager.dto.request.EmbalagemRequest;
import br.com.allidev.packager.dto.request.PedidoRequest;
import br.com.allidev.packager.dto.response.EmbalagemResponse;
import br.com.allidev.packager.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/embalagem")
public class EmbalagemController {

    private final EmbalagemService embalagemService;

    @Autowired
    public EmbalagemController(EmbalagemService embalagemService) {
        this.embalagemService = embalagemService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<EmbalagemResponse> calcularEmbalagem(@RequestBody EmbalagemRequest request) {
        if (request == null || request.getPedidos() == null) {
            return ResponseEntity.badRequest().build();
        }

        EmbalagemResponse resposta = embalagemService.calcularEmbalagem(
                request.getPedidos().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(resposta);
    }
}