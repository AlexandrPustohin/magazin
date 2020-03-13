package ru.alex.springweb.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.springweb.app.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
