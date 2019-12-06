package com.vidanova.atenas.testes;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.service.EnfermidadeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class EnfermidadeControllerTest extends AbstractTest{

    @Autowired
    JdbcTemplate jdbcTemplate;
    EnfermidadeService service;

    @Override
    @Before
    public void setUp(){
        super.setUp();
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (1,'A90','Doença teste0')");
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (2,'A91','Doença teste0')");
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (4,'A99','Doença teste0')");
    }

    @Test
    public void verificar_se_lista_todos() throws Exception{
        super.verificar_se_lista_todos("/enfermidade",Enfermidade[].class);
    }

    @Test
    public void verificar_se_criado() throws Exception{
        super.verificar_se_criado("/enfermidade",new Enfermidade(3,"A99","Doença teste1"));
    }

    @Test
    public void verificar_se_apagado() throws Exception{
        //Criando cenário
        super.verificar_se_apagado("/enfermidade", "4");
    }

    @Test
    public void verificar_se_atualizado() throws  Exception{
        Enfermidade enfermidade = new Enfermidade(1,"A99","Doença teste1");
        super.verificar_se_atualizado("/enfermidade",enfermidade, "1");
    }

    @Test
    public void verificar_pesquisa_parametros_exatos() throws Exception{
        //Realizando requisição - Preparar cenário
        Enfermidade enfermidade = new Enfermidade(5,"A50","Doença Teste teste");
        String uri ="/enfermidade?nome=Doença Teste teste";
        super.verificar_pesquisa_parametros_exatos(uri,enfermidade);
    }
    @After
    public void limpar(){
        jdbcTemplate.update("DELETE FROM TB_Enfermidade");
    }
}
