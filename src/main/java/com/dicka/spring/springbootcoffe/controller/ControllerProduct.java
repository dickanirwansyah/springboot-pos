package com.dicka.spring.springbootcoffe.controller;

import com.dicka.spring.springbootcoffe.entity.Product;
import com.dicka.spring.springbootcoffe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerProduct {

    private final ProductRepository productRepository;

    @Autowired
    public ControllerProduct(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @RequestMapping(value = "/product/{productId}")
    public String product(@PathVariable("productId")String productId, Model model){
        model.addAttribute("product", productRepository.findOne(Long.parseLong(productId)));
        return "product";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productList(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    public String saveProduct(@RequestBody Product product){
        productRepository.save(product);
        return product.getProductId().toString();
    }
}
