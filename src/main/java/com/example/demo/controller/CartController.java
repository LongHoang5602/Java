package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.services.ProductService;
import com.example.demo.services.ShoppingCartService;

@Controller
@RequestMapping("shopping-cart")
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("")
    public String viewCarts(Model model) {
        model.addAttribute("CartItem", shoppingCartService.getAllItem());
        return "cart/view";
    }

    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Integer id) {

        Long productIdLong = Long.valueOf(id);
        Product product = productService.getProductById(productIdLong);
        if (product != null) {
            CartItem item = new CartItem();
            Integer getId = Integer.parseInt(Long.toString(product.getId()));
            item.setProductId(getId);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQty(1);
            shoppingCartService.add(item);
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("clear")
    public String clearCart() {
        shoppingCartService.clear();
        return "redirect:/shopping-cart/";
    }

    @GetMapping("del/{id}")
    public String removeCart(@PathVariable("id") Integer id) {
        shoppingCartService.remove(id);
        return "redirect:/shopping-cart/";
    }

    @PostMapping("update/{id}")
    public String updateCart(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty) {
        shoppingCartService.update(id, qty);
        return "redirect:/shopping-cart/";
    }

}
