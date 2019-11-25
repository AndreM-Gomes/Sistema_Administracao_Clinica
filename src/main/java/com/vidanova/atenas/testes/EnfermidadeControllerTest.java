package com.vidanova.atenas.testes;

import com.vidanova.atenas.model.entidades.Enfermidade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnfermidadeControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void listarEnfermidades() throws Exception{
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
    public void criar() throws Exception{
        String uri = "/enfermidade";
        Enfermidade enfermidade = new Enfermidade(10,"A99","Doença teste");
        String jsonEntrada = super.mapToJson(enfermidade);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(jsonEntrada)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED,HttpStatus.resolve(status));
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"");
    }
}
