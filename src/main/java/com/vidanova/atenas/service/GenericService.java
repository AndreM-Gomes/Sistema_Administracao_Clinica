package com.vidanova.atenas.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface GenericService<T> {

    int salvar(T entity);

    int atualizar(T entity, int id);

    ResponseEntity<Optional<T>> encontrarPorId(int id);

    List<T> encontrarTodos();

    int deletar(int id);


}
