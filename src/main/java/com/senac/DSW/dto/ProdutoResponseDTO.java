package com.senac.DSW.dto;

public record ProdutoResponseDTO(

    Long id,
    String nome,
    Double preco,
    Long categoriaId
) {
    
}
