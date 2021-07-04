package at.electro.shop.inventory.services.supplier;

import at.electro.shop.inventory.services.product.models.Product;

import java.math.BigDecimal;

public class ProductSupplier {

    private ProductSupplier() {

    }

    public static Product productMock() {

        Product product = new Product();
        product.setUuid("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        product.setName("Mock Product A");
        product.setBrand("Wasted Grizzly");
        product.setImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRouHw7wONoTr2TgEYAeBtIzDbjP7C9PgC05A&usqp=CAU");
        product.setDescription("If a smashed Kashmir IPA tries to seduce a polar bear beer defined by a razor blade beer, then a Coors near an Ellis Island IPA self-flagellates. Brownie bonbon biscuit fruitcake icing lollipop dessert sweet roll.");
        product.setRating(BigDecimal.valueOf(4.6));
        product.setRatingsCount(5);
        product.setAlcoholPercent(BigDecimal.valueOf(7.3));
        product.setPrice(BigDecimal.valueOf(192.28));
        product.setCurrency("EUR");
        return product;
    }

}
