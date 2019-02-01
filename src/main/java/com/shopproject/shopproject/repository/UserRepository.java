package com.shopproject.shopproject.repository;

/**
 * Created by admin on 22.01.2019.
 */


import com.shopproject.shopproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
