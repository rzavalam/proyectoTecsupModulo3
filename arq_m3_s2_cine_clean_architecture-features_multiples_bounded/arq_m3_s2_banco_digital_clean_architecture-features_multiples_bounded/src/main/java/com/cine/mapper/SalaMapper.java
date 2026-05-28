package com.cine.mapper;

import com.cine.dto.SalaRequest;
import com.cine.dto.SalaResponse;
import com.cine.model.Sala;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalaMapper {

    SalaResponse toResponse(Sala sala);

    Sala toEntity(SalaRequest request);
}
