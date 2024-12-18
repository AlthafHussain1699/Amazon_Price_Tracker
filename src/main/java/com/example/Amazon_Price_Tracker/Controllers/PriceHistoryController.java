package com.example.Amazon_Price_Tracker.Controllers;


import com.example.Amazon_Price_Tracker.Entities.PriceHistory;
import com.example.Amazon_Price_Tracker.Repositaries.PriceHistoryRepositary;
import com.example.Amazon_Price_Tracker.Repositaries.ProductRepositary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/price-history")
public class PriceHistoryController {

    private final PriceHistoryRepositary priceHistoryRepository;
    private final ProductRepositary productRepository;

    public PriceHistoryController(PriceHistoryRepositary priceHistoryRepository, ProductRepositary productRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
        this.productRepository = productRepository;
    }

    // Get price history of a product between timestamps
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PriceHistory>> getPriceHistoryBetweenTimestamps(
            @PathVariable Long productId,
            @RequestParam("start") LocalDateTime startTimestamp,
            @RequestParam("end") LocalDateTime endTimestamp) {
        if (!productRepository.existsById(productId)) {
            return ResponseEntity.notFound().build();
        }
        List<PriceHistory> priceHistories = priceHistoryRepository.findByProductIdAndTimestampBetween(
                productId, startTimestamp, endTimestamp);
        return ResponseEntity.ok(priceHistories);
    }
}

