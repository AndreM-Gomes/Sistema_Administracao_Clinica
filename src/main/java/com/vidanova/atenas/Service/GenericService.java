package com.vidanova.atenas.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface GenericService<T>{
    int salvar(T entity);
    int atualizar(T entity, int id);
    ResponseEntity<Optional<T>> encontrarPorId(int id);
    List<T> encontrarTodos();
    int deletar(int id);
}
