package com.cine.mapper;

import com.cine.dto.ReservaAsientoRequest;
import com.cine.dto.ReservaAsientoResponse;
import com.cine.model.ReservaAsiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReservaMapper.class, FuncionMapper.class})
public interface ReservaAsientoMapper {

    ReservaAsientoResponse toResponse(ReservaAsiento reservaAsiento);

    @Mapping(target = "idReservaAsiento", ignore = true)
    @Mapping(target = "reserva", ignore = true)
    @Mapping(target = "funcion", ignore = true)
    ReservaAsiento toEntity(ReservaAsientoRequest request);
}
