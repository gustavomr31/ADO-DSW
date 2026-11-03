package com.senac.DSW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.DSW.model.Produto;

public interface ProdutoRepository extends JpaRepository< Produto, Long > {
    
}
