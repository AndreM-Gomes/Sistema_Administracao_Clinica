package com.vidanova.atenas.Controller;

import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import com.vidanova.atenas.Service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public abstract class CRUDController<T> {

    private final GenericService<T> service;

    public CRUDController(GenericService<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> listarTodos(){
        return service.encontrarTodos();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<T>> findById(@PathVariable("id") String id){
        return service.encontrarPorId(Integer.parseInt(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<T> criar(@Valid @RequestBody T t){
        service.salvar(t);
        return ResponseEntity.ok(t);
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
