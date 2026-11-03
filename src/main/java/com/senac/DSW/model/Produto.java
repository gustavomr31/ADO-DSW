package com.senac.DSW.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class Produto {
    
   
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    
    @ManyToOne 
    @JoinColumn (name = "categoria_id")
    private Categoria categoria;
   
   
    public Produto() {
    }

    public Produto(String nome, Double preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public Double getPreco() {
        return preco;
    }


    public void setPreco(Double preco) {
        this.preco = preco;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    } 

}
