package com.senac.DSW.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.senac.DSW.dto.ProdutoRequestDTO;
import com.senac.DSW.model.Categoria;
import com.senac.DSW.repository.CategoriaRepository;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional 
class ProdutoControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private ObjectMapper objectMapper;

    private Categoria categoriaPadrao;

    @BeforeEach 
    void setUp() {
        Categoria cat = new Categoria();
        cat.setNome("Eletrônicos");
        categoriaPadrao = categoriaRepository.save(cat);
    }

    @Test 
    void deveCriarProdutoComSucesso() throws Exception {
        ProdutoRequestDTO dto = new ProdutoRequestDTO(
                "Monitor",
                new BigDecimal("500.00"),
                categoriaPadrao.getId()
        );
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Monitor"))
                .andExpect(jsonPath("$.nomeCategoria").value("Eletrônicos"));
    }
}