package com.vidanova.atenas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface GenericService<T>{

    int salvar(T entity);
    int atualizar(T entity, int id);
    ResponseEntity<Optional<T>> encontrarPorId(int id);
    List<T> encontrarTodos();
    int deletar(int id);


}
