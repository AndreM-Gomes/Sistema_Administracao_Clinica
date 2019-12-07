package com.vidanova.atenas.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GenericRepository<T> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public abstract int salvar(T entity);

    public abstract int atualizar(T entity, int id);

    public abstract ResponseEntity<Optional<T>> encontrarPorId(int id);

    public abstract List<T> encontrarTodos();

    public abstract int deletar(int id);

    public List<T> pesquisaPorParametrosExatos(Map<String, String> parametrosPesquisa,
                                               RowMapper<T> rowMapper,
                                               String nomeTabela) {
        StringBuilder sql = new StringBuilder(60);
        Map<String, Object> sqlParametros = new HashMap<>();
        sql.append("SELECT * FROM " + nomeTabela);
        if (parametrosPesquisa.size() == 0) {
            return encontrarTodos();
        } else {
            sql.append(" WHERE ");
            parametrosPesquisa.forEach((chave, valor) -> {
                sql.append(chave).append("= :").append(chave).append(" AND ");
                sqlParametros.put(chave, parametrosPesquisa.get(chave));
            });
            sql.delete(sql.length() - 4, sql.length());
            try {
                return jdbcTemplate.query(String.valueOf(sql), sqlParametros, rowMapper);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
    }
}
