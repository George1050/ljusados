package com.loja.moveis.controller;

import com.loja.moveis.model.Produto;
import com.loja.moveis.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/adm")
    public String getAdm(Model model) {
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
    public String addProduto(@ModelAttribute Produto produto){
        if(produto == null){
            return "/cadastrar";
        }
        produtoService.add(produto);
        return "redirect:/adm";
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

}
