package com.vidanova.atenas.testes;

import com.vidanova.atenas.model.entidades.PlanoSaude;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PlanoSaudeControllerTest extends AbstractTest {

    private final String uriRecurso = "/plano-saude";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        super.setUp();
        jdbcTemplate.update("INSERT INTO TB_Plan_Saude VALUES (1,'A12A15A14','Greenline','Especial')");
        jdbcTemplate.update("INSERT INTO TB_Plan_Saude VALUES (2,'B12B15B14','Intermedica','Especial C Obstetrícia')");
        jdbcTemplate.update("INSERT INTO TB_Plan_Saude VALUES (4,'656565','Bradesco','Especial Senior')");
    }

    @Test
    public void verificar_se_lista_todos() throws Exception {
        super.verificar_se_lista_todos("/plano-saude", PlanoSaude[].class);
    }

    @Test
    public void verificar_se_criado() throws Exception {
        super.verificar_se_criado(uriRecurso,
                new PlanoSaude(3,
                        "11565465432",
                        "Samed",
                        "Global com quarto"), "3");
    }

    public void verificar_se_apagado() throws Exception {
        super.verificar_se_apagado(uriRecurso, "1");
    }

    @Test
    public void verificar_se_atualizado() throws Exception {
        PlanoSaude planoSaude = new PlanoSaude(4,
                "65654613",
                "Bradesco",
                "Especial Senior");
        super.verificar_se_atualizado(uriRecurso, planoSaude, "4");
    }

    @Test
    public void verificar_pesquisa_parametros_exatos() throws Exception {
        PlanoSaude planoSaude = new PlanoSaude(5, "A12A15A14", "Greenline Premium", "Especial");
        super.verificar_pesquisa_parametros_exatos(uriRecurso + "?nome=Greenline Premium&tipo=Especial", planoSaude);
    }

    @After
    public void limpar() {
        jdbcTemplate.update("DELETE FROM TB_Plan_Saude");
    }


}
