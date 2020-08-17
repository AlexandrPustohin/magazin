package ru.alex.springweb.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.alex.springweb.app.entities.OrderItem;
import ru.alex.springweb.app.entities.Product;
import ru.alex.springweb.app.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component //обычный бин
//создается для каждой сессии и к ней привязана
//аннотация видимости
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    private List<OrderItem> items;
    private ProductService productService;
    @Autowired//получаем (инжектим) продуксервис
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<OrderItem> getItems() {
        return items;
    }



    @PostConstruct//метод выполниться после инициализации бина (этого)
    public void init(){
        items = new ArrayList<>();
    }
    //добавляем продукт в корзину
    public void addProductById(Long id){
        //найдем по id
        Product product = productService.getProductById(id);
        //создаем запись по продукту
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        //записываем
        items.add(orderItem);
    }
}
