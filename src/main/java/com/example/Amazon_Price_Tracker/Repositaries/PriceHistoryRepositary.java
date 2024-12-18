package com.example.Amazon_Price_Tracker.Repositaries;



import com.example.Amazon_Price_Tracker.Entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceHistoryRepositary extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductId(Long productId);

    List<PriceHistory> findByProductIdAndTimestampBetween(Long productId, LocalDateTime startTimestamp, LocalDateTime endTimestamp);
}

