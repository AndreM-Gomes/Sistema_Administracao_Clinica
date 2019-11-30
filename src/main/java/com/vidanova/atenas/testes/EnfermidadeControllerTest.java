package com.vidanova.atenas.testes;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.service.EnfermidadeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.junit.Assert.*;
//TODO ADICIONAR COMENTARIO AOS METODOS
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
    }

    @Test
    public void verificar_se_lista_todos() throws Exception{
        //Realizando requisição
        String uri = "/enfermidade";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Enfermidade[] enfermidades = super.mapFromJson(content,Enfermidade[].class);
        assertTrue(enfermidades.length > 0);


    }

    @Test
    public void verificar_se_criado() throws Exception{
        //Realizando requisição
        String uri = "/enfermidade";
        Enfermidade enfermidade = new Enfermidade(3,"A99","Doença teste1");
        String jsonEntrada = super.mapToJson(enfermidade);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonEntrada)).andReturn();

        //Verificando resposta e status da requisição
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED,HttpStatus.resolve(status));
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"");

        //Verificando se o objeto foi realmente criado
        MvcResult getResult = mvc.perform(MockMvcRequestBuilders.get("/enfermidade/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(mapToJson(enfermidade),getResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void verificar_se_apagado() throws Exception{
        //Criando cenário
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (4,'A99','TESTETeste')");

        //Realizando requisição
        String uri = "/enfermidade/4";
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResultDelete.getResponse().getStatus();
        assertEquals(HttpStatus.OK,HttpStatus.resolve(status));

        //garantindo que objeto foi apagado
        MvcResult mvcResultGet = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int statusGet = mvcResultGet.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND,HttpStatus.resolve(statusGet));
    }

    @Test
    public void verificar_se_atualizado() throws  Exception{
        //Realizando requisição
        String uri = "/enfermidade/1";
        Enfermidade enfermidade = new Enfermidade(1,"A99","Doença teste1");
        String jsonEntrada = super.mapToJson(enfermidade);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonEntrada)).andReturn();
        //Verificando resposta
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK,HttpStatus.resolve(status));
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"");

        //Verificando se objeto foi atualizado
        MvcResult getResult = mvc.perform(MockMvcRequestBuilders.get(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(mapToJson(enfermidade),getResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void verificar_pesquisa_porCID() throws Exception{
        //Realizando requisição - Preparar cenário
        String uri = "/enfermidade";
        Enfermidade enfermidade = new Enfermidade(5,"A50","Doença Teste teste");
        String jsonEntrada = super.mapToJson(enfermidade);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonEntrada)).andReturn();
        //Verificando resposta
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED,HttpStatus.resolve(status));

        //Realizando requisição - Realizar pesquisa teste
        uri ="/enfermidade?nome=Doença Teste teste";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals("[" + mapToJson(enfermidade)+"]",mvcResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }
    @After
    public void limpar(){
        jdbcTemplate.update("DELETE FROM TB_Enfermidade");
    }
}
