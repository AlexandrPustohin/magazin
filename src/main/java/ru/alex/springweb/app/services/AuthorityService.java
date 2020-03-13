package ru.alex.springweb.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.springweb.app.entities.Authority;
import ru.alex.springweb.app.entities.User;
import ru.alex.springweb.app.repositories.AuthorityRepository;
@Service
public class AuthorityService {
    private AuthorityRepository authorityRepository;
    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
    public Authority findByAuthority (String authority){
        return authorityRepository.findById(authority).get();
    }

    public void save(User username) {
        Authority authority = new Authority();
        authority.setUser(username);
        authority.setAuthority(findByAuthority("ROLE_USER").getAuthority());
        authorityRepository.save(authority);
    }
}
