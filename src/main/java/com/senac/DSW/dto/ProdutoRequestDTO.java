package com.senac.DSW.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
   
   @NotBlank  String nome,
   @NotNull  Double preco,
   @NotNull  Long categoriaId
) {
    
}
