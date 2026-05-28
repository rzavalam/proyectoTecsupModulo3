package com.cine.funcionescine.infrastructure.adapters.inbound;

import com.cine.funcionescine.domain.model.vo.HorarioFuncion;
import com.cine.funcionescine.domain.model.vo.Precio;
import com.cine.funcionescine.domain.model.vo.TipoFuncion;
import com.cine.funcionescine.domain.ports.inbound.CrearFuncionUseCase;
import com.cine.funcionescine.domain.ports.inbound.CrearFuncionUseCase.CrearFuncionCommand;
import com.cine.funcionescine.domain.ports.inbound.CrearFuncionUseCase.FuncionCreadaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    private final CrearFuncionUseCase crearFuncionUseCase;

    public FuncionController(CrearFuncionUseCase crearFuncionUseCase) {
        this.crearFuncionUseCase = crearFuncionUseCase;
    }

    @PostMapping
    public ResponseEntity<FuncionCreadaResponse> crear(@RequestBody CrearFuncionRequest request) {
        CrearFuncionCommand command = new CrearFuncionCommand(
                request.funcionId(),
                request.peliculaId(),
                request.salaId(),
                new HorarioFuncion(LocalDate.parse(request.fecha()), LocalTime.parse(request.hora())),
                new Precio(new BigDecimal(request.monto()), request.moneda()),
                TipoFuncion.valueOf(request.tipoFuncion())
        );
        FuncionCreadaResponse response = crearFuncionUseCase.crear(command);
        return ResponseEntity.ok(response);
    }

    record CrearFuncionRequest(
            String funcionId,
            String peliculaId,
            String salaId,
            String fecha,
            String hora,
            String monto,
            String moneda,
            String tipoFuncion
    ) {}
}
