package com.vidanova.atenas.controller;

import com.vidanova.atenas.model.entidades.PlanoSaude;
import com.vidanova.atenas.service.GenericService;
import com.vidanova.atenas.service.PlanoSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plano-saude")
public class PlanoSaudeController extends CRUDController<PlanoSaude> {

    @Autowired
    PlanoSaudeService planoSaudeService;

    public PlanoSaudeController(GenericService<PlanoSaude> service) {
        super(service);
    }

    @GetMapping
    List<PlanoSaude> listarTodosPorParametrosExatos(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String numPlan,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo
    ){
        return planoSaudeService.pesquisaPorParametrosExatos(id,numPlan,nome,tipo);
    }
}
