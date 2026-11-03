package com.senac.DSW.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.senac.DSW.dto.CategoriaRequestDTO;
import com.senac.DSW.dto.CategoriaResponseDTO;
import com.senac.DSW.service.CategoriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping 
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar();

    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping 
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public CategoriaResponseDTO Criar(@RequestBody @Valid CategoriaRequestDTO dto) {
        return categoriaService.criar(dto);
    }

    @PutMapping ("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto) {
        return categoriaService.atualizar(id, dto);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus (org.springframework.http.HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}
