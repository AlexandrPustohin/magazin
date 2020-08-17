package ru.alex.springweb.app.configs;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;
//включаем защиту этим классом
//делаем бином
@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
