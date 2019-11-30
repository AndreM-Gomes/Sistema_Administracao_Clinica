package com.vidanova.atenas.model;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenericRepository<T> {
    int salvar(T entity);
    int atualizar(T entity, int id);
    ResponseEntity<Optional<T>> encontrarPorId(int id);
    List<T> encontrarTodos();
    int deletar(int id);
    List<T> pesquisaPorParametrosExatos(Map<String,String> parametros);
}
