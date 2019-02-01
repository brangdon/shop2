package com.shopproject.shopproject.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 28.01.2019.
 */
@Entity
@Table(name="orders_details")
public class OrderDetails implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
    private Product product;

//    @Column(name = "quantity", nullable = false)
//    private int quanity;

//    @ManyToOne
//    @JoinColumn(name="order_id", referencedColumnName = "order_id")
//    private Product product;
}
