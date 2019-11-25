package com.vidanova.atenas.Model;

import com.vidanova.atenas.Model.Entidades.Enfermidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class EnfermidadeRepository implements Repository<Enfermidade> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int salvar(Enfermidade enfermidade) {
        return jdbcTemplate.update(
                "INSERT INTO TB_Enfermidade VALUES (default,?,?)",
                enfermidade.getCID(),enfermidade.getNome());
    }

    @Override
    public int atualizar(Enfermidade enfermidade, int id) {
        return jdbcTemplate.update(
                "UPDATE TB_Enfermidade SET CID=?,nome=? WHERE id_Enfermidade=?",
                enfermidade.getCID(),enfermidade.getNome(),id
        );
    }

    @Override
    public ResponseEntity<Optional<Enfermidade>> encontrarPorId(int id) {
        try{
            Optional<Enfermidade> resultado =  jdbcTemplate.queryForObject("SELECT * FROM TB_Enfermidade WHERE id_Enfermidade=?",
                    new Object[]{id},
                    (resultSet,rowNum) -> Optional.of(  new Enfermidade(resultSet.getInt("id_Enfermidade"),
                            resultSet.getString("CID"),
                            resultSet.getString("nome"))));
            ResponseEntity<Optional<Enfermidade>> entity = new ResponseEntity<>(resultado,HttpStatus.OK);
            return entity;
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Optional<Enfermidade>> encontrarPorCID(String CID) {
        try{
            Optional<Enfermidade> resultado =  jdbcTemplate.queryForObject("SELECT * FROM TB_Enfermidade WHERE CID=?",
                    new Object[]{CID},
                    (resultSet,rowNum) -> Optional.of(  new Enfermidade(resultSet.getInt("id_Enfermidade"),
                            resultSet.getString("CID"),
                            resultSet.getString("nome"))));
            ResponseEntity<Optional<Enfermidade>> entity = new ResponseEntity<>(resultado,HttpStatus.OK);
            return entity;
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<Enfermidade> encontrarTodos() {
        try{
            return jdbcTemplate.query(
                "SELECT * FROM TB_Enfermidade",
                (rs,rowNum)-> new Enfermidade(
                        rs.getInt("id_Enfermidade"),
                        rs.getString("CID"),
                        rs.getString("nome")
                ));}catch (EmptyResultDataAccessException e){
            return null;
        }

    }
    @Override
    public int deletar(int id){
        return jdbcTemplate.update("DELETE FROM TB_Enfermidade WHERE id_Enfermidade=?",id);
    }
}
