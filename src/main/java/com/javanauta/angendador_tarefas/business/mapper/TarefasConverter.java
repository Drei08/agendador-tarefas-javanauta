package com.javanauta.angendador_tarefas.business.mapper;

import com.javanauta.angendador_tarefas.business.dto.TarefasDTO;
import com.javanauta.angendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
