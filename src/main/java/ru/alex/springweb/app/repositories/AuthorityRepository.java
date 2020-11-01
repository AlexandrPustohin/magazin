package ru.alex.springweb.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.springweb.app.entities.Authority;



@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findOneByAuthority(String authority);
}
