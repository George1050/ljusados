package com.loja.moveis.service;

import com.loja.moveis.model.Produto;
import com.loja.moveis.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    public Produto get(Long id){
        return produtoRepository.getOne(id);
    }

    public void add(Produto produto){
        produtoRepository.save(produto);
    }

    public void remover(Long id){
        produtoRepository.deleteById(id);
    }
}
