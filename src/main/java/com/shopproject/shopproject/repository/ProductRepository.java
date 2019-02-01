package com.shopproject.shopproject.repository;

import com.shopproject.shopproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * Created by admin on 24.01.2019.
 */

public interface ProductRepository extends CrudRepository<Product, Long> {


}
