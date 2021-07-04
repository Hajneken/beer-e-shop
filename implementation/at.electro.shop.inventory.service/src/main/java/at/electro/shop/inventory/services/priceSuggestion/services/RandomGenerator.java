package at.electro.shop.inventory.services.priceSuggestion.services;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
    private Random random = new Random();

    public double generate() {
        return random.nextDouble();
    }

    public double generate(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }
}
