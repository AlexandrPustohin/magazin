package ru.alex.springweb.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.springweb.app.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findOneByTitle(String title);
}
