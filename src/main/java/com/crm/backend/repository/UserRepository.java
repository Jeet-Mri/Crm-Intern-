package com.crm.backend.repository;

import com.crm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(String role);
    User findByEmail(String email);
}
