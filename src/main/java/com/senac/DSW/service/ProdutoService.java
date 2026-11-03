package com.senac.DSW.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.senac.DSW.repository.CategoriaRepository;
import com.senac.DSW.repository.ProdutoRepository;
import com.senac.DSW.dto.ProdutoRequestDTO;
import com.senac.DSW.dto.ProdutoResponseDTO;
import com.senac.DSW.model.Categoria;
import com.senac.DSW.model.Produto;

@Service 
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll().stream()
                .map(p -> new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getId()))
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getId());
    }
     public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Categoria cat = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));

        Produto p = new Produto();
        p.setNome(dto.nome());
        p.setPreco(dto.preco());
        p.setCategoria(cat);
        p = produtoRepository.save(p);

        return new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getId());
    }

    public ProdutoResponseDTO atualizar( Long id, ProdutoRequestDTO dto) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Categoria cat = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));

        p.setNome(dto.nome());
        p.setPreco(dto.preco());
        p.setCategoria(cat);
        p = produtoRepository.save(p);

        return new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getId());
    }

    public void excluir(Long id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        produtoRepository.delete(p);
    }

}
