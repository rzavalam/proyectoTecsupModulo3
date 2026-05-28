package com.cine.mapper;

import com.cine.dto.ClienteRequest;
import com.cine.dto.ClienteResponse;
import com.cine.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);

    @Mapping(target = "idCliente", ignore = true)
    Cliente toEntity(ClienteRequest request);
}
