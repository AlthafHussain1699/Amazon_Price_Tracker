package com.example.Amazon_Price_Tracker.Services;


import com.example.Amazon_Price_Tracker.Entities.PriceHistory;
import com.example.Amazon_Price_Tracker.Entities.Product;
import com.example.Amazon_Price_Tracker.Repositaries.PriceHistoryRepositary;
import com.example.Amazon_Price_Tracker.Repositaries.ProductRepositary;
import com.example.Amazon_Price_Tracker.Services.AmazonScraper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class PriceHistoryUpdater {

    private final ProductRepositary productRepository;
    private final PriceHistoryRepositary priceHistoryRepository;
    private final AmazonScraper amazonScraper;

    public PriceHistoryUpdater(ProductRepositary productRepository, PriceHistoryRepositary priceHistoryRepository) {
        this.productRepository = productRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.amazonScraper = new AmazonScraper();
    }

    // Scheduled to run every day at midnight
    @Scheduled(cron = "*/10 * * * * ?")
    public void updatePriceHistory() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            Map<String, String> details = amazonScraper.scrapeProductDetails(product.getUrl());

            if (!details.isEmpty()) {
                String price = details.get("price");
                PriceHistory priceHistory = new PriceHistory();
                priceHistory.setPrice(Double.parseDouble(price.replace("$", "").replace(",", "")));
                priceHistory.setTimestamp(LocalDateTime.now());
                priceHistory.setProductId(product.getId());
                priceHistoryRepository.save(priceHistory);
            }
        }
    }
}

