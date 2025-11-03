package com.vizu.eventmanager.application.controller;

import com.vizu.eventmanager.application.controller.dto.request.EventRequestDTO;
import com.vizu.eventmanager.application.controller.dto.response.EventResponseDTO;
import com.vizu.eventmanager.domain.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @Operation(summary = "Retorna uma lista de eventos ativos", description = "Lista paginada de eventos")
    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> listAllActives(Pageable pageable) {
        return ResponseEntity.ok(service.listAllActives(pageable));
    }

    @Operation(summary = "Busca evento por ID", description = "Retorna os detalhes de um evento específico pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Cria um novo evento", description = "Cria um evento com os dados fornecidos no corpo da requisição.",
            responses = {
                    @ApiResponse(description = "Evento criado com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = EventResponseDTO.class))),
                    @ApiResponse(description = "Erro de validação", responseCode = "400", content = @Content),
            })
    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody EventRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(summary = "Atualiza um evento existente", description = "Atualiza os dados de um evento existente pelo ID informado.",
            responses = {
                @ApiResponse(description = "Evento deletado com sucesso", responseCode = "204", content = @Content),
                @ApiResponse(description = "Evento não encontrado", responseCode = "404", content = @Content),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EventRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Deleta um evento", description = "Remove um evento pelo ID informado.",
            responses = {
                    @ApiResponse(description = "Evento deletado com sucesso", responseCode = "204", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}