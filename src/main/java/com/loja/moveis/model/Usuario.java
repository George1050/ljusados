package com.loja.moveis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String login;
    String email;
    String senha;
    String rua;
    String bairro;
    Integer numero;
    Boolean acesso;

    public Usuario(){
        this.acesso = false;
    }

}
