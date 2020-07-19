package com.loja.moveis.controller;

import com.loja.moveis.model.Produto;
import com.loja.moveis.model.Usuario;
import com.loja.moveis.service.UsuarioService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/addusuario")
    public String getPageUsuario(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "addusuario";
    }

    @RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
    public String addUsuario(@ModelAttribute Usuario usuario){
        if(usuario == null) {
            return "addusuario";
        }
        usuarioService.add(usuario);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String getPageLogin(Model model,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null){
            return "redirect:/";
        }
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "login";
    }

    @RequestMapping(value = "/validarLogin", method = RequestMethod.POST)
    public String validarLogin(@ModelAttribute Usuario usuario, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            var id = usuarioService.findByLoginAndPassword(usuario.getLogin(), usuario.getSenha());
            if(id == null){
                response.setStatus(400);
                return "/login";
            }
            session.setAttribute("id", id);
            response.setStatus(200);

        }
        return "redirect:/";
    }

    @RequestMapping("/Logout")
    public String logoutSistema(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
