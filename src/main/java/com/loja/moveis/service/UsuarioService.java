package com.loja.moveis.service;

import com.loja.moveis.model.Usuario;
import com.loja.moveis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService{


    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByLoginAndPassword(String login, String senha){
        List<Usuario> usuarioList = findAll();

        for (var i:usuarioList) {
            if(i.getLogin().equals(login) && i.getSenha().equals(senha)){
                return i;
            }
        }
        return null;
    }

    public String findById(Long id){
        Usuario usuario = usuarioRepository.getOne(id);
        String endereco = usuario.getRua()+ "," + usuario.getBairro() + "," + usuario.getNumero();
        return endereco;
    }

    public void add(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario get(Long id){ return usuarioRepository.getOne(id); }
    public void remover(Long id){
        usuarioRepository.deleteById(id);
    }
}
