package com.banco.accounts.infrastructure.persistence.mapper;

import com.banco.accounts.domain.model.EstadoFuncion;
import com.banco.accounts.domain.model.FuncionCine;
import com.banco.accounts.domain.model.TipoFuncion;
import com.banco.accounts.infrastructure.persistence.entity.FuncionCineEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    @Mapping( target = "tipoFuncion", expression = "java(funcionCine.getTipoFuncion().name())")
    @Mapping( target = "estado",expression = "java(funcionCine.getEstado().name())")
    FuncionCineEntity toEntity(FuncionCine funcionCine);

    default FuncionCine toDomain(FuncionCineEntity entity) {

        if (entity == null) {
            return null;
        }

        TipoFuncion tipoFuncion = TipoFuncion.valueOf(entity.getTipoFuncion());
        EstadoFuncion estado = EstadoFuncion.valueOf(entity.getEstado());

        // Usa constructor en lugar de setters
        return new FuncionCine(
                entity.getFuncionCineId(),
                entity.getSalaId(),
                entity.getPeliculaId(),
                entity.getHorarioInicio(),
                entity.getPrecio(),
                tipoFuncion,
                estado
        );
    }
}
