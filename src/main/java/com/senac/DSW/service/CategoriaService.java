package com.senac.DSW.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.senac.DSW.dto.CategoriaRequestDTO;
import com.senac.DSW.dto.CategoriaResponseDTO;
import com.senac.DSW.model.Categoria;
import com.senac.DSW.repository.CategoriaRepository;

@Service 
public class CategoriaService {
    
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaResponseDTO> listar() {
        List<Categoria> categorias = repository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaResponseDTO(categoria.getId(), categoria.getNome()))
                .toList();
    }

    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        Categoria c = new Categoria();
        c.setNome(dto.nome());
        c = repository.save(c);
        return new CategoriaResponseDTO(c.getId(), c.getNome());
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        c.setNome(dto.nome());
        c = repository.save(c);
        return new CategoriaResponseDTO(c.getId(), c.getNome());
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}


