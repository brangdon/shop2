package com.shopproject.shopproject.web;

import com.shopproject.shopproject.domain.Order;
import com.shopproject.shopproject.domain.Product;
import com.shopproject.shopproject.domain.User;
import com.shopproject.shopproject.repository.OrderRepository;
import com.shopproject.shopproject.repository.ProductRepository;
import com.shopproject.shopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by admin on 24.01.2019.
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:3000", "http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000"})
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/all", produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping(path = "/add2")
    public @ResponseBody
    String addOrder2(@RequestBody Order order) {

        orderRepository.save(order);

        String json = "[{\"message\":\"success2\"}}]";
        return json;
    }

    @PostMapping("/addauth")
    public @ResponseBody String addOrderAuth(@AuthenticationPrincipal UserDetails userDetails, @RequestBody List<Product> products){

        Optional<User> u = userRepository.findByUsername(userDetails.getUsername());

        Order order = new Order(u.get().getId(), new Date().toString(), products);
        orderRepository.save(order);

        String json = "[{\"message\":\"success\"}}]";
        return json;

    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addOrder() {

        Product p = new Product((long) 2, "title1", "author1", Double.valueOf(12.2), "description1", Arrays.asList("category1", "category2"), "photo1");
        Product p2 = new Product((long) 3, "title2", "author2", Double.valueOf(34.4), "description2", Arrays.asList("category1", "category2", "category3"), "photo2");
        List<Product> products = new ArrayList<>();
        products.add(p);
        products.add(p);
        products.add(p2);
        Order order = new Order(new Long(5), new Date().toString(), products);
        orderRepository.save(order);

        String json = "[{\"message\":\"success\"}}]";
        return json;
    }

    @GetMapping(path = "/{id}")
    public Order getOrder(@PathVariable(value = "id") Long orderID) {
        Optional<Order> opt = orderRepository.findById(orderID);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @GetMapping(path = "/user/{id}")
    public List<Order> getOrderByUserID(@PathVariable(value = "id") Long userId) {
        Optional<List<Order>> opt = orderRepository.findOdersByUserId(userId);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }
}