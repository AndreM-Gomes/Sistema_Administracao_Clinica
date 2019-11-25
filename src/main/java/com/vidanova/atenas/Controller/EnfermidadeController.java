package com.vidanova.atenas.Controller;

import com.vidanova.atenas.Model.EnfermidadeRepository;
import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import com.vidanova.atenas.Service.EnfermidadeService;
import com.vidanova.atenas.Service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
