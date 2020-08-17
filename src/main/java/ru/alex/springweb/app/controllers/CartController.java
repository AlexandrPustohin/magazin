package ru.alex.springweb.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alex.springweb.app.entities.User;
import ru.alex.springweb.app.services.OrderService;
import ru.alex.springweb.app.services.UserService;
import ru.alex.springweb.app.utils.ShoppingCart;

import java.security.Principal;

//контроллер корзины
@Controller
@RequestMapping("/cart")//для обработки всех запросов, начинающихся с /cart
public class CartController {
    private ShoppingCart cart;
    private OrderService orderService;
    private UserService userService;
    @Autowired//заинжектим корзину
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.cart = shoppingCart;
    }
    @Autowired//заинжектим заявку
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired//заинжектим пользователей
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")//обработвыем основной запрос
    public String showCart(Model model){
        model.addAttribute("items", cart.getItems());
        return "cart";//возвращаем страницу
    }
    @GetMapping("/add/{id}")//обрабатывам добавление в корзину
    public String addToCart(Model model, @PathVariable("id") Long id){
        cart.addProductById(id);//добавил в корзину (через контроллер)
        System.out.println("добавили продукт");
        return "redirect:/shop";//возвращаем обратно
                                //хотя тут можно запросить кол-во

    }
    @GetMapping("/create_order")//создаем ордер
    public String createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        orderService.createOrderFromItems(user, cart.getItems());
        return "redirect:/shop";
    }
}
