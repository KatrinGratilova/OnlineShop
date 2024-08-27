package org.katrin.onlineshop.controller;

import lombok.AllArgsConstructor;
import org.katrin.onlineshop.model.ProductEntity;
import org.katrin.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@AllArgsConstructor

@Controller()
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String getByName(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
        model.addAttribute("products", productService.getByName(Optional.ofNullable(name)));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductInfo(@PathVariable int id, Model model) {
        ProductEntity product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());

        return "product-info";
    }

    @PostMapping("/products/create")
    public String save(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                       @RequestParam("file3") MultipartFile file3, ProductEntity productEntity, Principal principal) throws IOException {
        productService.save(principal, productEntity, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("products/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
