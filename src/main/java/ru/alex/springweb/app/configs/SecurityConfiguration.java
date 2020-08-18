package ru.alex.springweb.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration//класс конфигурации - какие пользователи куда могут заходить
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //для подключения к БД
    private DataSource dataSource;
    @Autowired //инжектим
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //аутеннтификацию делаем через БД
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //разрешаем все запросы, но в детали только админ
        //permitAll() - для всех
        http.authorizeRequests()

                .antMatchers("/details/**").hasAnyRole("ADMIN")
                .antMatchers("/create_order**").hasAnyRole("USER,ADMIN")
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").permitAll()//тут страница где залогиниться
                .loginProcessingUrl("/authenticateTheUser");//тут проверяем пользователя НЕ РЕАЛИЗОВАНО!!!
    }
}
