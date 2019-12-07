package com.vidanova.atenas.testes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidanova.atenas.AtenasApplication;
import com.vidanova.atenas.model.GenericRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AtenasApplication.class)
@WebAppConfiguration
public abstract class AbstractTest<T> {
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;
    GenericRepository<T> repository;

    protected void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected T mapFromJson(String json,Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,clazz);
    }

    protected void verificar_se_criado(String uri, T entidade, String idEntidade) throws Exception{
        //Realizando requisição
        String jsonEntrada = mapToJson(entidade);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonEntrada)).andReturn();

        //Verificando resposta e status da requisição
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED,HttpStatus.resolve(status));
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"");

        //Verificando se o objeto foi realmente criado
        MvcResult getResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/" +idEntidade)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(mapToJson(entidade),getResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }
    protected void verificar_se_lista_todos(String uri,Class<T> clazz) throws Exception{
        //Realizando requisição
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        T[] entidades = (T[]) mapFromJson(content,clazz);
        assertTrue(entidades.length > 0);
        assertEquals(200,status);
    }
    protected void verificar_se_apagado(String uri, String idEntidade) throws Exception{
        //Criando cenário
        //Realizando requisição
        uri = uri + "/" + idEntidade;
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResultDelete.getResponse().getStatus();
        assertEquals(HttpStatus.OK,HttpStatus.resolve(status));

        //garantindo que objeto foi apagado
        MvcResult mvcResultGet = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int statusGet = mvcResultGet.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND,HttpStatus.resolve(statusGet));
    }
    protected void verificar_se_atualizado(String uri, T entidade, String idEntidade) throws  Exception{
        //Realizando requisição
        uri = uri +  "/" + idEntidade;
        String jsonEntrada = mapToJson(entidade);
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

        assertEquals(mapToJson(entidade),getResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }
    protected void verificar_pesquisa_parametros_exatos(String uri,T entidade) throws Exception{
        //Realizando requisição - Preparar cenário
        String jsonEntrada = mapToJson(entidade);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonEntrada)).andReturn();
        //Verificando resposta
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED,HttpStatus.resolve(status));

        //Realizando requisição - Realizar pesquisa teste
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals("[" + mapToJson(entidade)+"]",
                mvcResult.getResponse().getContentAsString(Charset.defaultCharset())
        );
    }

}
