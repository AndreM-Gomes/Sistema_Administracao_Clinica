package com.vidanova.atenas.controller;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.service.EnfermidadeService;
import com.vidanova.atenas.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enfermidade")
public class EnfermidadeController extends CRUDController<Enfermidade> {

    @Autowired
    EnfermidadeService enfermidadeService;

    public EnfermidadeController(GenericService<Enfermidade> service) {
        super(service);
    }

    @GetMapping
    public List<Enfermidade> listarTodosParametrosExatos(
            @RequestParam(required = false) Integer id_Enfermidade,
            @RequestParam(required = false) String CID,
            @RequestParam(required = false) String nome
    ) {
        return enfermidadeService.pesquisaPorParametrosExatos(id_Enfermidade, CID, nome);
    }
}
