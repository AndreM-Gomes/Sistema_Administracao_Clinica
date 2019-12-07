package com.vidanova.atenas.model;

import com.vidanova.atenas.model.entidades.PlanoSaude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@org.springframework.stereotype.Repository
public class PlanoSaudeRepository extends GenericRepository<PlanoSaude> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlanoSaudeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int salvar(PlanoSaude planoSaude) {
        Map<String, Object> sqlParametros = new HashMap<>();
        sqlParametros.put("id", planoSaude.getId_Plan_Saude());
        sqlParametros.put("numPlan", planoSaude.getNum_Plan());
        sqlParametros.put("nome", planoSaude.getNome());
        sqlParametros.put("tipo", planoSaude.getTipo());
        return jdbcTemplate.update("INSERT INTO TB_Plan_Saude VALUES (:id,:numPlan,:nome,:tipo)",
                sqlParametros);
    }

    @Override
    public int atualizar(PlanoSaude planoSaude, int id) {
        Map<String, Object> sqlParametros = new HashMap<>();
        sqlParametros.put("numPlan", planoSaude.getNum_Plan());
        sqlParametros.put("nome", planoSaude.getNome());
        sqlParametros.put("tipo", planoSaude.getTipo());
        sqlParametros.put("id", id);
        String sql = "UPDATE TB_Plan_Saude SET num_Plan=:numPlan,nome=:nome,tipo=:tipo WHERE id_Plan_Saude=:id";
        return jdbcTemplate.update(sql, sqlParametros);
    }

    @Override
    public ResponseEntity<Optional<PlanoSaude>> encontrarPorId(int id) {
        String sql = "SELECT * FROM TB_Plan_Saude WHERE id_Plan_Saude=:id";
        RowMapper<Optional<PlanoSaude>> rowMapper =
                (resultSet, rn) -> Optional.of(new PlanoSaude(
                        resultSet.getInt("id_Plan_Saude"),
                        resultSet.getString("num_Plan"),
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"))
                );
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);

        Optional<PlanoSaude> resultado = jdbcTemplate.queryForObject(sql, parametros, rowMapper);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    public List<PlanoSaude> encontrarTodos() {
        return jdbcTemplate.query("SELECT * FROM TB_Plan_Saude",
                (resultSet, rn) -> new PlanoSaude(resultSet.getInt("id_Plan_Saude"),
                        resultSet.getString("num_Plan"),
                        resultSet.getString("nome"),
                        resultSet.getString("tipo")));
    }

    @Override
    public int deletar(int id) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        return jdbcTemplate.update("DELETE FROM TB_Plan_Saude WHERE id_Plan_Saude=:id", parametros);
    }
}
