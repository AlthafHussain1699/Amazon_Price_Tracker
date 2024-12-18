package com.example.Amazon_Price_Tracker.Repositaries;

import com.example.Amazon_Price_Tracker.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositary extends JpaRepository<Product, Long> {

}
