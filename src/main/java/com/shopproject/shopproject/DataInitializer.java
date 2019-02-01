package com.shopproject.shopproject;

import com.shopproject.shopproject.domain.Order;
import com.shopproject.shopproject.domain.Product;
import com.shopproject.shopproject.repository.OrderRepository;
import com.shopproject.shopproject.repository.ProductRepository;
import com.shopproject.shopproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.shopproject.shopproject.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository users;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User("user", this.passwordEncoder.encode("password"), Arrays.asList("ROLE_USER"));


        this.users.save(new User("admin", this.passwordEncoder.encode("password"), Arrays.asList("ROLE_USER", "ROLE_ADMIN")));
        Product p1 = new Product("title1", "author1", Double.valueOf(12.2), "description1", Arrays.asList("category1", "category2"), "photo1");
        Product p2 = new Product("title2", "author2", Double.valueOf(34.4), "description2", Arrays.asList("category1", "category2", "category3"), "photo2");
        Product p3 = new Product("title3", "author3", Double.valueOf(32.6), "description3", Arrays.asList("category3"), "photo3");

        this.productRepository.save(p1);
        this.productRepository.save(p2);
        this.productRepository.save(p3);

        this.users.save(u1);

        HashSet<Product> products = new HashSet<>();
        products.add(p1);
        products.add(p2);
//        Order o1= new Order(u1.getId(), "28.01", products);
//        Order o1= new Order(u1.getId(), "28.01", new HashSet<Product> (Arrays.asList(p1)));
//        Set<Order> set = Stream.of(p1,p2).collect(Collectors.toSet(HashSet::new));
//        this.orderRepository.save(o1);
    }
}
