package ru.alex.springweb.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.springweb.app.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
