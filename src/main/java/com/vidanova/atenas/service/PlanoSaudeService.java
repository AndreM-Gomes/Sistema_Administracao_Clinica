package com.vidanova.atenas.service;

import com.vidanova.atenas.model.GenericRepository;
import com.vidanova.atenas.model.entidades.PlanoSaude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
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

    public List<PlanoSaude> pesquisaPorParametrosExatos(Integer id, String numPlan, String nome, String tipo) {
        Map<String, String> parametrosPesquisa = new HashMap<>();
        if (id != null) {
            parametrosPesquisa.put("id_Plan_Saude", String.valueOf(id));
        }
        if (numPlan != null) {
            parametrosPesquisa.put("num_Plan", numPlan);
        }
        if (nome != null) {
            parametrosPesquisa.put("nome", nome);
        }
        if (tipo != null) {
            parametrosPesquisa.put("tipo", tipo);
        }
        return repository.pesquisaPorParametrosExatos(
                parametrosPesquisa,
                (rs, rowNum) -> new PlanoSaude(rs.getInt("id_Plan_Saude"),
                        rs.getString("num_Plan"),
                        rs.getString("nome"),
                        rs.getString("tipo")),
                "TB_Plan_Saude");
    }
}
