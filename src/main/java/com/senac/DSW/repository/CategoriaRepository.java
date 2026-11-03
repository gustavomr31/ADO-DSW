package com.senac.DSW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.DSW.model.Categoria;


public interface CategoriaRepository extends JpaRepository< Categoria, Long > {
    
}
