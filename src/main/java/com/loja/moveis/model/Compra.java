package com.loja.moveis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Compra {
    @Id
    Long id_produto;
    Double preco;
    String data;
    Long id_cliente;
    Boolean status;

}
