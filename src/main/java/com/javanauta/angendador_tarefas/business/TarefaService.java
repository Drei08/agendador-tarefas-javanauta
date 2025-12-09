package com.javanauta.angendador_tarefas.business;

import com.javanauta.angendador_tarefas.business.dto.TarefasDTO;
import com.javanauta.angendador_tarefas.business.mapper.TarefaUpdadeConverter;
import com.javanauta.angendador_tarefas.business.mapper.TarefasConverter;
import com.javanauta.angendador_tarefas.infrastructure.entity.TarefasEntity;
import com.javanauta.angendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.angendador_tarefas.infrastructure.exceptions.ResourceNotFoundExcepetion;
import com.javanauta.angendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.angendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdadeConverter tarefaUpdadeConverter;


    public TarefasDTO gravarTarefas(String token, TarefasDTO dto){

        String email = jwtUtil.extractEmailToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto);

        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                            LocalDateTime dataFinal){

        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){

        String email = jwtUtil.extractEmailToken(token.substring(7));

        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        }catch (ResourceNotFoundExcepetion e){
            throw new ResourceNotFoundExcepetion("Erro ao deletar tarefa por id, id inexistente",
                    e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundExcepetion("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch(ResourceNotFoundExcepetion e){
            throw new ResourceNotFoundExcepetion("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundExcepetion("Tarefa não encontrada " + id));
            tarefaUpdadeConverter.updateTarefas(dto, entity);
            return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch(ResourceNotFoundExcepetion e){
            throw new ResourceNotFoundExcepetion("Erro ao alterar status da tarefa " + e.getCause());
        }
    }




}
