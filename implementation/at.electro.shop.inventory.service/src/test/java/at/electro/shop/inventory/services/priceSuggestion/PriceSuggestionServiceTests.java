package at.electro.shop.inventory.services.priceSuggestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import at.electro.shop.inventory.services.priceSuggestion.services.RandomGenerator;

@ExtendWith(MockitoExtension.class)
public class PriceSuggestionServiceTests {
    @Mock
    private RandomGenerator randomGenerator;

    @InjectMocks
    private PriceSuggestionService sut;

    @Test
    public void shouldGenerateSmallerNumberThanOldPrice() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(0.25);
        var oldPrice = new BigDecimal(10);

        // Act
        var result = sut.getPriceSuggestion("test", oldPrice);

        // Assert
        assertEquals(7.50, result.doubleValue());
        verify(randomGenerator).generate();
    }

    @Test
    public void shouldGenerateBiggerNumberThanOldPrice() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(0.75);
        var oldPrice = new BigDecimal(10);
        // Act

        var result = sut.getPriceSuggestion("test", oldPrice);

        // Assert
        assertEquals(12.50, result.doubleValue());
        verify(randomGenerator).generate();
    }

    @Test
    public void maximumGeneratedDoubleShouldReturn15() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(1.0);
        var oldPrice = new BigDecimal(10);
        // Act

        var result = sut.getPriceSuggestion("test", oldPrice);

        // Assert
        assertEquals(15.0, result.doubleValue());
    }

    @Test
    public void randomZeroPointFiveShouldGenerate10() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(0.5);
        var oldPrice = new BigDecimal(10);
        // Act

        var result = sut.getPriceSuggestion("test", oldPrice);

        // Assert
        assertEquals(10.0, result.doubleValue());
        verify(randomGenerator).generate();
    }

    @Test
    public void randomZerShouldHalfThePrice() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(0.0);
        var oldPrice = new BigDecimal(10);
        // Act

        var result = sut.getPriceSuggestion("test", oldPrice);

        // Assert
        assertEquals(5.0, result.doubleValue());
        verify(randomGenerator).generate();
    }

    @Test
    public void shouldGenerateNewPriceBasedOnRandomOldPrice() {
        // Arrange
        when(randomGenerator.generate()).thenReturn(0.5);
        when(randomGenerator.generate(anyDouble(), anyDouble())).thenReturn(20.0);
        // Act

        var result = sut.getPriceSuggestion("test");

        // Assert
        assertEquals(20.0, result.doubleValue());
        verify(randomGenerator).generate();
        verify(randomGenerator).generate(anyDouble(), anyDouble());
    }
}
