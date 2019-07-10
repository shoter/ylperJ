package com.shoter.ylper.api;

import com.shoter.ylper.core.Users.UserRepository;
import com.shoter.ylper.core.Users.UserRepositoryImpl;
import com.shoter.ylper.core.Users.UserService;
import com.shoter.ylper.core.Users.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory.openSession();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public UserRepository userRepository(Session session) {
        return new UserRepositoryImpl(session);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public UserService userService(UserRepository userRepository)
    {
        return new UserServiceImpl(userRepository);
    }



}
