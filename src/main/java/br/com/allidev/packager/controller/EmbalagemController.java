package br.com.allidev.packager.controller;

import br.com.allidev.packager.dto.EmbalagemRequestDTO;
import br.com.allidev.packager.dto.EmbalagemResponseDTO;
import br.com.allidev.packager.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EmbalagemResponseDTO> calcularEmbalagem(@RequestBody EmbalagemRequestDTO request) {
        if (request == null || request.getPedidos() == null) {
            return ResponseEntity.badRequest().build();
        }

        EmbalagemResponseDTO resposta = embalagemService.calcularEmbalagem(
                request.getPedidos().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(resposta);
    }
}