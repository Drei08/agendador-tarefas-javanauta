package com.javanauta.angendador_tarefas.business.mapper;

import com.javanauta.angendador_tarefas.business.dto.TarefasDTO;
import com.javanauta.angendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdadeConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
