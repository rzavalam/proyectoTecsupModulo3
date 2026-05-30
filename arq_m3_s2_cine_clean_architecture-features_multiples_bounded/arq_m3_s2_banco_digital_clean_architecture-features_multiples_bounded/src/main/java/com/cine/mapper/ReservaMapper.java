package com.cine.mapper;

import com.cine.dto.ReservaRequest;
import com.cine.dto.ReservaResponse;
import com.cine.model.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, FuncionMapper.class})
public interface ReservaMapper {

    ReservaResponse toResponse(Reserva reserva);

    @Mapping(target = "idReserva", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "funcion", ignore = true)
    Reserva toEntity(ReservaRequest request);
}
