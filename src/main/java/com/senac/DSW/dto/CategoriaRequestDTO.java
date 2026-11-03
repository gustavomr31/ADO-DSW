package com.senac.DSW.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
    
    @NotBlank String nome
) {
    
}
