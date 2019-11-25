package com.vidanova.atenas.Service;

import com.vidanova.atenas.Model.EnfermidadeRepository;
import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class EnfermidadeService implements GenericService<Enfermidade> {

    @Autowired
    private EnfermidadeRepository enfermidadeRepository;

    public int salvar(Enfermidade enfermidade){
        return enfermidadeRepository.salvar(enfermidade);
    }
    public int atualizar(Enfermidade enfermidade,int id){
        return enfermidadeRepository.atualizar(enfermidade,id);
    }

    @Override
    public ResponseEntity<Optional<Enfermidade>> encontrarPorId(int id) {
        return enfermidadeRepository.encontrarPorId(id);
    }

    @Override
    public List<Enfermidade> encontrarTodos() {
        return enfermidadeRepository.encontrarTodos();
    }

    @Override
    public int deletar(int id) {
        return 0;
    }

    public ResponseEntity<Optional<Enfermidade>> encontrarPorCIDouID(String id){
        if (id.matches("^[A-Z]\\d{2}$")){
            return enfermidadeRepository.encontrarPorCID(id);
        }
        else if(id.matches("^\\d")){
            return enfermidadeRepository.encontrarPorId(Integer.parseInt(id));
        }else{
            return null;
        }
    }

}
