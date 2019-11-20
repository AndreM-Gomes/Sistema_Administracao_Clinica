package com.vidanova.atenas.Controller;

import com.vidanova.atenas.Model.Entidades.Enfermidade;
import com.vidanova.atenas.Model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enfermidade")
public class EnfermidadeController extends CRUDController<Enfermidade> {

    public EnfermidadeController(Repository<Enfermidade> repository) {
        super(repository);
    }
}
