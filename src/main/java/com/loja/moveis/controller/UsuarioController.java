package com.loja.moveis.controller;

import com.loja.moveis.model.Usuario;
import com.loja.moveis.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

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
    public ModelAndView addUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("addusuario");
            modelAndView.addObject("usuario",usuario);
            return modelAndView;
        }
        usuarioService.add(usuario);
        return modelAndView;
    }

    @RequestMapping("/alterarConta/{id}")
    public ModelAndView alterarConta(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("cadastrar");
        var usuario = usuarioService.get(id);

        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    @RequestMapping("/apagarConta/{id}")
    public String apagarConta(@PathVariable(name = "id") Long id){
        usuarioService.remover(id);
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
            var usuarioVerificado = usuarioService.findByLoginAndPassword(usuario.getLogin(), usuario.getSenha());
            if(usuarioVerificado == null){
                response.setStatus(400);
                return "login";
            }
            session.setAttribute("login", usuario.getLogin());
            session.setAttribute("id", usuarioVerificado.getId());
            response.setStatus(200);
            if(usuarioVerificado.getAcesso() == true){
                session.setAttribute("acesso", usuarioVerificado.getAcesso());
                return "redirect:/adm";
            }

        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logoutSistema(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        Calendar c = Calendar.getInstance();
        String hora = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
        String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        Cookie ultSessao = new Cookie("ultSessao", hora+"/"+dia);
        ultSessao.setMaxAge(((60*60)*24)*180);
        response.addCookie(ultSessao);
        session.invalidate();
        return "redirect:/";
    }

}
