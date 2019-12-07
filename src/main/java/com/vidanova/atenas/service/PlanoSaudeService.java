package com.vidanova.atenas.service;

import com.vidanova.atenas.model.GenericRepository;
import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.model.entidades.PlanoSaude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.GenericServlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlanoSaudeService implements GenericService<PlanoSaude> {

    @Autowired
    GenericRepository<PlanoSaude> repository;

    @Override
    public int salvar(PlanoSaude entity) {
        return repository.salvar(entity);
    }

    @Override
    public int atualizar(PlanoSaude entity, int id) {
        return repository.atualizar(entity, id);
    }

    @Override
    public ResponseEntity<Optional<PlanoSaude>> encontrarPorId(int id) {
        return repository.encontrarPorId(id);
    }

    @Override
    public List<PlanoSaude> encontrarTodos() {
        return repository.encontrarTodos();
    }

    @Override
    public int deletar(int id) {
        return repository.deletar(id);
    }

}
