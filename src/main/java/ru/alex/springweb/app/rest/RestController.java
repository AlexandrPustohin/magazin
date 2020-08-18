package ru.alex.springweb.app.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alex.springweb.app.entities.Product;
import ru.alex.springweb.app.services.ProductService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    public ProductService productService;

    @GetMapping (value = "/prod")
    public ResponseEntity<List<Product>>  detAllProductRest(){
        List<Product> products = productService.getAllProduct();
        return products != null &&  !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/prod/{id}")
    public ResponseEntity<Product> findProdById(@PathVariable(name = "id") Long id){
        final Product product = productService.getProductById(id);


        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
