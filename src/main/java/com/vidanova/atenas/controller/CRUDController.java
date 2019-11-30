package com.vidanova.atenas.controller;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public abstract class CRUDController<T> {

    private final GenericService<T> service;

    public CRUDController(GenericService<T> service) {
        this.service = service;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<T>> findById(@PathVariable("id") int id){
        return service.encontrarPorId(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<T> criar(@Valid @RequestBody T t){
        service.salvar(t);
        return new ResponseEntity<T>(HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@Valid @PathVariable("id") int id,@RequestBody T t){
        service.atualizar(t,id);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable("id")int id){
        service.deletar(id);
    }

}
