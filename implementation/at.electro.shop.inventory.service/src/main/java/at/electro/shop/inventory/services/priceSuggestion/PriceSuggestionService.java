package at.electro.shop.inventory.services.priceSuggestion;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import at.electro.shop.inventory.services.priceSuggestion.services.RandomGenerator;

@Service
public class PriceSuggestionService {
    private final RandomGenerator randomGenerator;

    public PriceSuggestionService(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public BigDecimal getPriceSuggestion(String productName, BigDecimal oldPrice) {
        var rand = randomGenerator.generate();
        var factor = new BigDecimal(rand - 0.5);
        var newPrice = oldPrice.multiply(factor);
        newPrice = oldPrice.add(newPrice);
        return newPrice;
    }

    public BigDecimal getPriceSuggestion(String productName) {
        var randomOldPrice = randomGenerator.generate(2, 20);
        return getPriceSuggestion(productName, new BigDecimal(randomOldPrice));
    }

}
