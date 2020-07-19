package com.loja.moveis.controller;

import com.loja.moveis.model.Compra;
import com.loja.moveis.model.Produto;
import com.loja.moveis.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CompraController {

    private CompraService compraService;

    @Autowired
    public void setCompraService(CompraService compraService){
        this.compraService = compraService;
    }

    @RequestMapping("/carrinho")
    public String addCarrinho(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Produto produto = (Produto) session.getAttribute("tempProduto");

        //
        List<Produto> produtoList = (ArrayList<Produto>) session.getAttribute("carrinho");

        if (produtoList == null){
            produtoList = new ArrayList<>();
        }

        produtoList.add(produto);

        session.setAttribute("carrinho", produtoList);

        model.addAttribute("lista", produtoList);

        return "carrinho";
    }

    @RequestMapping("/finalizarCompra")
    public String finalizarCompra(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Long id_usuario = ((Long) session.getAttribute("id"));
        Calendar c = Calendar.getInstance();
        String data = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH));

        List<Produto> carrinho = ((List<Produto>) session.getAttribute("carrinho"));
        if(!(carrinho == null)) {
            response.setStatus(400);
            return "/";
        }
        for (var i:carrinho) {
            Compra compra = new Compra(i.getId(), i.getPreco(), data, id_usuario);
            compraService.add(compra);
        }
        response.setStatus(200);
        return "redirect:/";
    }

    @RequestMapping("historico")
    public void historicoCompras(){

    }
}
