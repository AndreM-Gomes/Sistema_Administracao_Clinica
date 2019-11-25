package com.vidanova.atenas.Controller;

import com.vidanova.atenas.Model.EnfermidadeRepository;
import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enfermidade")
public class EnfermidadeController extends CRUDController<Enfermidade> {

    @Autowired
    EnfermidadeRepository repository;

    public EnfermidadeController(Repository<Enfermidade> repository) {
        super(repository);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Enfermidade>> findById(@PathVariable String id) {
        if (id.matches("^[A-Z]\\d{2}$")){
           return repository.encontrarPorCID(id);
        }
        else if(id.matches("^\\d")){
            return repository.encontrarPorId(Integer.parseInt(id));
        }else{
            return null;
        }

    }
}
