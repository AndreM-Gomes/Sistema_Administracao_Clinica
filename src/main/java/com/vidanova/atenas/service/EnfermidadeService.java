package com.vidanova.atenas.service;

import com.vidanova.atenas.model.GenericRepository;
import com.vidanova.atenas.model.entidades.Enfermidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class EnfermidadeService implements GenericService<Enfermidade> {

    @Autowired
    private GenericRepository<Enfermidade> genericRepository;

    public int salvar(Enfermidade enfermidade) {
        return genericRepository.salvar(enfermidade);
    }

    public int atualizar(Enfermidade enfermidade, int id) {
        return genericRepository.atualizar(enfermidade, id);
    }

    @Override
    public ResponseEntity<Optional<Enfermidade>> encontrarPorId(int id) {
        return genericRepository.encontrarPorId(id);
    }

    @Override
    public List<Enfermidade> encontrarTodos() {
        return genericRepository.encontrarTodos();
    }

    @Override
    public int deletar(int id) {
        return genericRepository.deletar(id);
    }

    public List<Enfermidade> pesquisaPorParametrosExatos(Integer id, String CID, String nome) {
        Map<String, String> paramentrosPesquisa = new HashMap<>();
        if (id != null) {
            paramentrosPesquisa.put("id_Enfermidade", String.valueOf(id));
        }
        if (CID != null) {
            paramentrosPesquisa.put("CID", CID);
        }
        if (nome != null) {
            paramentrosPesquisa.put("nome", nome);
        }
        return genericRepository.pesquisaPorParametrosExatos(paramentrosPesquisa,
                (rs, rowNum) -> new Enfermidade(
                        rs.getInt("id_Enfermidade"),
                        rs.getString("CID"),
                        rs.getString("nome")
                ), "TB_Enfermidade");
    }

}
