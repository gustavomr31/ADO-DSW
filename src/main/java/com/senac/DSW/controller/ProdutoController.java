package com.senac.DSW.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.senac.DSW.service.ProdutoService;
import com.senac.DSW.dto.ProdutoRequestDTO;
import com.senac.DSW.dto.ProdutoResponseDTO;

@RestController 
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping 
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(@RequestBody ProdutoRequestDTO dto) { // Usa ProdutoRequestDTO
        return produtoService.criar(dto);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) { // Usa ProdutoRequestDTO
        return produtoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { 
        produtoService.excluir(id);
    }
}