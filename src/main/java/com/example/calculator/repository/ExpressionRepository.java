package com.example.calculator.repository;

import com.example.calculator.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpressionRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);
}
