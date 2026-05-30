package com.cine.mapper;

import com.cine.dto.FuncionRequest;
import com.cine.dto.FuncionResponse;
import com.cine.model.Funcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SalaMapper.class, PeliculaMapper.class})
public interface FuncionMapper {

    FuncionResponse toResponse(Funcion funcion);

    @Mapping(target = "sala", ignore = true)
    @Mapping(target = "pelicula", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Funcion toEntity(FuncionRequest request);
}
