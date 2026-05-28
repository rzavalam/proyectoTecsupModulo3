package com.cine.funcionescine.infrastructure.adapters.inbound;

import com.cine.funcionescine.domain.model.Sala;
import com.cine.funcionescine.domain.ports.outbound.SalaRepositoryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaRepositoryPort salaRepository;

    public SalaController(SalaRepositoryPort salaRepository) {
        this.salaRepository = salaRepository;
    }

    @PostMapping
    public ResponseEntity<SalaResponse> registrar(@RequestBody SalaRequest request) {
        Sala sala = new Sala(request.id(), request.nombre(), request.capacidad());
        salaRepository.guardar(sala);
        return ResponseEntity.ok(new SalaResponse(sala.getId(), sala.getNombre(), sala.getCapacidad()));
    }

    record SalaRequest(String id, String nombre, int capacidad) {}
    record SalaResponse(String id, String nombre, int capacidad) {}
}
