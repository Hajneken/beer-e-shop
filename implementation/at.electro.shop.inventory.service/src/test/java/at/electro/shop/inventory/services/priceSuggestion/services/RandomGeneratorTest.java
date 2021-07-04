package at.electro.shop.inventory.services.priceSuggestion.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomGeneratorTest {
    private RandomGenerator sut;

    @BeforeEach
    public void setup() {
        sut = new RandomGenerator();
    }

    @Test
    public void generateShouldReturnNumber() {
        // Arrange

        // Act
        var result = sut.generate();

        // Assert
        assertNotNull(result);
    }

    @Test
    public void generateShouldReturnNumberInBetween() {
        // Arrange

        // Act
        var result = sut.generate(10, 15);

        // Assert
        assertNotNull(result);
    }
}
