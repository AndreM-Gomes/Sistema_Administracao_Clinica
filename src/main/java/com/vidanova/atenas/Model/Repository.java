package com.vidanova.atenas.Model;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    int salvar(T entity);
    int atualizar(T entity, int id);
    Optional<T> encontrarPorId(int id);
    List<T> encontrarTodos();
    int deletar(int id);

}
