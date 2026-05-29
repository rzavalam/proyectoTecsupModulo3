package com.cine.mapper;

import com.cine.dto.PeliculaRequest;
import com.cine.dto.PeliculaResponse;
import com.cine.model.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    PeliculaResponse toResponse(Pelicula pelicula);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Pelicula toEntity(PeliculaRequest request);
}
