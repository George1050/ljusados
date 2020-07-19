package com.loja.moveis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Campo nome não pode estar vazio")
    @NotEmpty(message = "Campo nome não pode estar vazio")
    String nome;

    @NotNull(message = "Url da Imagem não pode estar vazio")
    @NotEmpty(message = "Url da Imagem não pode estar vazio")
    String urlImagem;

    @NotNull(message = "Campo estado não pode estar nulo")
    @NotEmpty(message = "É necessario informar o estado do produto")
    String estado;

    @NotNull(message = "Campo nome não pode ser nulo")
    @NotEmpty(message = "É necessario informar a Categoria do produto")
    String categoria;

    @NotNull(message = "Descrição não pode ser nulo")
    String descricao;

    @NotNull(message = "Por favor informe um preço para o produto")
    Double preco;

    Boolean estoque;

}
