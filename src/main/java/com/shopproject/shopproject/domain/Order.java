package com.shopproject.shopproject.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @NotEmpty
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;

    //    @NotEmpty
    private String order_date;

//    @OneToMany
//    @JoinTable(name = "orders_details",
//            joinColumns = {@JoinColumn(name = "order_id")})

    @ManyToMany
    @JoinTable(name = "orders_details",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    public Order() {
    }

    public Order(Long user_id, String order_date, List<Product> products) {
//        this.id = id;
        this.userId = user_id;
        this.order_date = order_date;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return userId;
    }

    public void setUser_id(Long user_id) {
        this.userId = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    //    @OneToMany
//    @JoinTable(name = "orders_details",
//            joinColumns = {@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

