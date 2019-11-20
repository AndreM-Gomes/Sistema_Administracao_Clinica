package com.vidanova.atenas.Controller;

import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDController<T> {

    private final Repository<T> repository;

    public CRUDController(Repository<T> repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<T> listarTodos(){
        return repository.encontrarTodos();
    }

    @GetMapping(value="/{id}")
    public Optional<T> findById(@PathVariable("id") int id){
        return repository.encontrarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int criar(@RequestBody T t){
        return repository.salvar(t);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable("id") int id,@RequestBody T t){
       repository.atualizar(t,id);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable("id")int id){
       repository.deletar(id);
    }
}