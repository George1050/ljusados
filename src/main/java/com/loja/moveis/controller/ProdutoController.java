package com.loja.moveis.controller;

import com.loja.moveis.model.Produto;
import com.loja.moveis.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProdutoController {

    private ProdutoService produtoService;

    @Autowired
    public void setProdutoService(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @RequestMapping("/")
    public String getHome(Model model){
        List<Produto> produtoList = produtoService.findAll();
        model.addAttribute("produtoList", produtoList);
        return "index";
    }
    //lembrar de juntar essas funções
    @RequestMapping("/adm")
    public String getAdm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("acesso") == null){
            return "redirect:/";
        }
        List<Produto> produtoList = produtoService.findAll();
        model.addAttribute("produtoList", produtoList);
        return "adm";
    }

    @RequestMapping("/cadastrar")
    public String getPageCadastrar(Model model){
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ModelAndView addProduto(@Valid @ModelAttribute Produto produto, BindingResult bindingResult){
        ModelAndView modelAndView;
        //Testa se esta vindo de cadastrar e se possue erros
        if(produto.getId() == null && bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("cadastrar");
            modelAndView.addObject("produto", produto);
            return modelAndView;
        }
        //Testa se esta vindo de alterar e se possue erros
        if(bindingResult.hasErrors()){
            modelAndView = new ModelAndView("redirect:/alterar/"+produto.getId());
            modelAndView.addObject("produto", produto);
            return modelAndView;
        }
        modelAndView = new ModelAndView("redirect:/adm");
        produtoService.add(produto);
        return modelAndView;
    }

    @RequestMapping("/alterar/{id}")
    public ModelAndView alterarProduto(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("alterar");
        var produto = produtoService.get(id);

        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @RequestMapping("/remover/{id}")
    public String removerProduto(@PathVariable(name = "id") Long id){
        produtoService.remover(id);
        return "redirect:/adm";
    }

    @RequestMapping("/produto/{id}")
    public ModelAndView infoProduto(@PathVariable(name = "id") Long id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("produto");
        var produto = produtoService.get(id);
        HttpSession session = request.getSession();
        session.setAttribute("tempProduto", produto);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

}
