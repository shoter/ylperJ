package com.shoter.ylper.api;

import com.shoter.ylper.core.Bookings.BookingRepository;
import com.shoter.ylper.core.Bookings.BookingRepositoryImpl;
import com.shoter.ylper.core.Bookings.BookingService;
import com.shoter.ylper.core.Bookings.BookingServiceImpl;
import com.shoter.ylper.core.Cars.*;
import com.shoter.ylper.core.Demands.DemandRepository;
import com.shoter.ylper.core.Demands.DemandRepositoryImpl;
import com.shoter.ylper.core.Demands.DemandService;
import com.shoter.ylper.core.Demands.DemandServiceImpl;
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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Configuration
public class AppConfig implements WebMvcConfigurer {

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
    public CarRepository carRepository(Session session) {return new CarRepositoryImpl(session);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public BookingRepository bookingRepository(Session session) {return new BookingRepositoryImpl(session);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public DemandRepository demandRepository(Session session) { return new DemandRepositoryImpl(session);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public CarService carService(CarRepository carRepository) { return new CarServiceImpl(carRepository);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public DemandService demandService(DemandRepository demandRepository, UserRepository userRepository) {
        return new DemandServiceImpl(demandRepository, userRepository);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public BookingService bookingService(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository) {
        return new BookingServiceImpl(bookingRepository, carRepository, userRepository);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public UserService userService(UserRepository userRepository)
    {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public CarInnersRepository carInnersRepository(Session session)
    {
        return new CarInnersRepositoryImpl(session);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Validator validator()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }



}
