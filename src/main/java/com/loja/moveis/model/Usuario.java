package com.loja.moveis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "O login não pode estar vazio.")
    @Size(min = 6, max = 24, message = "Login deve ter no minimo 6 caracteris.")
    String login;

    @NotEmpty(message = "Email é obrigatorio.")
    @Email(message = "Email incorreto.")
    String email;

    @NotEmpty(message = "Senha é obrigatorio.")
    String senha;

    @NotEmpty(message = "Rua é obrigatorio.")
    String rua;

    @NotEmpty(message = "Bairro é obrigatorio.")
    String bairro;

    @NotNull(message = "O Número é obrigatório.")
    Integer numero;

    @NotNull(message = "o acesso não pode estar vazio")
    Boolean acesso;

    public Usuario(){
        this.acesso = false;
    }

}
