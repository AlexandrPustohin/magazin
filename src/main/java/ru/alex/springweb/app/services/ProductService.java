package ru.alex.springweb.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.springweb.app.entities.Product;
import ru.alex.springweb.app.repositories.ProductRepository;

import java.util.List;

@Service//сервис - как бы прослойка между контролером и репозитарием
public class ProductService {
    private ProductRepository productRepository;
    @Autowired//указывает что необходимо заинжектить (создать ссылку на объект репозитория
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).get() ;   //.get(id.intValue()-1);
    }
    public Product getProductByTitle(String title){
        return  productRepository.findOneByTitle(title);
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

}
