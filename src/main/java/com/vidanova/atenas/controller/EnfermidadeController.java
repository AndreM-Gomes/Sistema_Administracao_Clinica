package com.vidanova.atenas.controller;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.service.EnfermidadeService;
import com.vidanova.atenas.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enfermidade")
public class EnfermidadeController extends CRUDController<Enfermidade> {

    @Autowired
    EnfermidadeService enfermidadeService;

    public EnfermidadeController(GenericService<Enfermidade> service) {
        super(service);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Enfermidade>> findById(@PathVariable String id) {
        return enfermidadeService.encontrarPorCIDouID(id);
    }
}
