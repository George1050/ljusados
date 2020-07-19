package com.loja.moveis.service;

import com.loja.moveis.model.Compra;
import com.loja.moveis.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private CompraRepository compraRepository;

    @Autowired
    public void setCompraRepository(CompraRepository compraRepository){
        this.compraRepository = compraRepository;
    }

    public Boolean findByIdProduto(Long id){
        return compraRepository.existsById(id);
    }

    public void add(Compra compra) {
        compraRepository.save(compra);
    }
}
