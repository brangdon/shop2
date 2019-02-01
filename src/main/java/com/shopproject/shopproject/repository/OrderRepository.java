package com.shopproject.shopproject.repository;

import com.shopproject.shopproject.domain.Order;
import com.shopproject.shopproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 24.01.2019.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

//    @Query("SELECT * FROM orders t WHERE t.user_id=user_id")
    Optional<List<Order>> findOdersByUserId(Long userId);

}
