package com.vidanova.atenas.model;

import com.vidanova.atenas.model.entidades.Enfermidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class EnfermidadeRepository extends GenericRepository<Enfermidade> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int salvar(Enfermidade enfermidade) {
        Map<String, Object> parametros = new HashMap<>();
        String sql = "INSERT INTO TB_Enfermidade VALUES (:id,:CID,:nome)";
        parametros.put("id", enfermidade.getId());
        parametros.put("CID", enfermidade.getCID());
        parametros.put("nome", enfermidade.getNome());

        return jdbcTemplate.update(sql, parametros);
    }

    @Override
    public int atualizar(Enfermidade enfermidade, int id) {
        String sql = "UPDATE TB_Enfermidade SET CID=:cid,nome=:nome WHERE id_Enfermidade=:id";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", enfermidade.getId());
        parametros.put("cid", enfermidade.getCID());
        parametros.put("nome", enfermidade.getNome());
        return jdbcTemplate.update(sql, parametros);
    }

    @Override
    public ResponseEntity<Optional<Enfermidade>> encontrarPorId(int id) {
        try {
            String sql = "SELECT * FROM TB_Enfermidade WHERE id_Enfermidade=:id";
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Optional<Enfermidade> resultado = jdbcTemplate.queryForObject(
                    sql,
                    parametros,
                    (resultSet, rowNum) -> Optional.of(new Enfermidade(resultSet.getInt("id_Enfermidade"),
                            resultSet.getString("CID"),
                            resultSet.getString("nome"))));
            ResponseEntity<Optional<Enfermidade>> entity = new ResponseEntity<>(resultado, HttpStatus.OK);
            if (!entity.hasBody()) {
                throw new EmptyResultDataAccessException(0);
            }
            return entity;
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Enfermidade> encontrarTodos() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM TB_Enfermidade",
                    (rs, rowNum) -> new Enfermidade(
                            rs.getInt("id_Enfermidade"),
                            rs.getString("CID"),
                            rs.getString("nome")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int deletar(int id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        return jdbcTemplate.update("DELETE FROM TB_Enfermidade WHERE id_Enfermidade=:id", parametros);
    }
}

