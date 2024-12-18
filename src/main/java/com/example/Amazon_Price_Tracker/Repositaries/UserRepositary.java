package com.example.Amazon_Price_Tracker.Repositaries;

import com.example.Amazon_Price_Tracker.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Long> {
     User findByEmail(String email);
}
