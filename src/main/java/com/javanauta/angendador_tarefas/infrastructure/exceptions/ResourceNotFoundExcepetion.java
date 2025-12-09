package com.javanauta.angendador_tarefas.infrastructure.exceptions;

public class ResourceNotFoundExcepetion extends RuntimeException{

    public ResourceNotFoundExcepetion(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundExcepetion(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
