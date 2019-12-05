package com.vidanova.atenas.testes;

import com.vidanova.atenas.model.entidades.Enfermidade;
import com.vidanova.atenas.model.entidades.PlanoSaude;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlanoSaudeControllerTest extends AbstractTest{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Before
    public void setUp(){
        super.setUp();
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (1,'A12A15A14','Greenline','Especial')");
        jdbcTemplate.update("INSERT INTO TB_Enfermidade VALUES (2,'B12B15B14','Intermedica','Especial C Obstetrícia')");
    }


}
