package com.banco.accounts.infrastructure.web.controller;

import com.banco.accounts.application.dto.CrearFuncionCommand;
import com.banco.accounts.application.usecase.ConsultarFuncionesDisponiblesUseCase;
import com.banco.accounts.application.usecase.CrearFuncionUseCase;
import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.model.TipoFuncion;
import com.banco.accounts.infrastructure.web.dto.CrearFuncionRequest;
import com.banco.accounts.infrastructure.web.dto.FuncionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@RequiredArgsConstructor
public class FuncionController {

    private final CrearFuncionUseCase crearFuncionUseCase;
    private final ConsultarFuncionesDisponiblesUseCase consultarFuncionesDisponiblesUseCase;



    @PostMapping
    public ResponseEntity<FuncionResponse> crearFuncion(
            @RequestBody CrearFuncionRequest request) {

        CrearFuncionCommand command = new CrearFuncionCommand(
                request.getSalaId(),
                request.getPeliculaId(),
                request.getHorarioInicio(),
                request.getPrecio(),
                TipoFuncion.valueOf(request.getTipoFuncion())
        );

        FuncionCine funcion = crearFuncionUseCase.ejecutar(command);
        FuncionResponse response = FuncionResponse.from(funcion);


        return ResponseEntity.ok(response);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<FuncionResponse>> listarFunciones( @RequestParam LocalDate fecha) {

        List<FuncionResponse> response =  consultarFuncionesDisponiblesUseCase
                        .ejecutar(fecha)
                        .stream()
                        .map(FuncionResponse::from)
                        .toList();

        return ResponseEntity.ok(response);
    }
}
