package ru.alex.springweb.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alex.springweb.app.entities.Product;
import ru.alex.springweb.app.entities.User;
import ru.alex.springweb.app.services.ProductService;
import ru.alex.springweb.app.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller//контроллер типа сердце сервера :)
public class MainController {
    private UserService userService;
    private ProductService productService;
    // делаем инжекшен - заставляем спринг заинджектить ссылку на productService
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //перехватываем get запрос
    //помечаем метод аннотацией
    //
    @GetMapping("/index")// обработчик запросов для соответсвующей страницы
    public String homePage(){
        return "index";
    }

    @GetMapping("/login")// обработчик запросов для соответсвующей страницы
    public String loginPage(){
        return "login";
    }
    @GetMapping("/logout")// обработчик запросов для соответсвующей страницы
    public String logout(){
        return "login";
    }

    @GetMapping("/error")// обработчик запросов для соответсвующей страницы
    public String errorPage(){
        return "error";
    }
    @GetMapping("/admin")// обработчик запросов для соответсвующей страницы
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/reg")// обработчик запросов для соответсвующей страницы
    public String regPage(){
        return "reg";
    }

    //еще на одну страницу с данными
    @GetMapping("/shop")
    public String shopPage(Model model){//Model -данные которые будут прокидваться на страницу
        List<Product> allProduct = productService.getAllProduct();
        //в model добавляем атрибут в которм будет наш объект
        model.addAttribute("prod", allProduct);
        return "shop";
    }
    @GetMapping("/details/{id}")
    //Model -данные которые будут прокидваться на страницу
    //достаем id из URL и используем для  поиска или еще чего
    public String detailesPage(Model model, @PathVariable("id") Long id){
        Product selectProduct = productService.getProductById(id);
        //в model добавляем атрибут в которм будет наш объект
        model.addAttribute("selectProd", selectProduct);
        return "details";
    }
    @GetMapping("/find_by_title")
     public String findByTitle(Model model, @RequestParam("title") String title){
        Product selectProduct = productService.getProductByTitle(title);
        //в model добавляем атрибут в которм будет наш объект
        model.addAttribute("selectProd", selectProduct);
        return "details";
    }

    @GetMapping("/products/delete/{id}")//URL
    public  String deleteProductById(@PathVariable("id") Long id){//выцепляем id из URL
        productService.deleteProductById(id);
        //перенаправим на нужную страниу (shop)
        //выпролнится метод shopPage
        return "redirect:/shop";
    }



}
