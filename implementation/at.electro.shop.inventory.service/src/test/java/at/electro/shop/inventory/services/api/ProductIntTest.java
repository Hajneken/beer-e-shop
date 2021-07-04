package at.electro.shop.inventory.services.api;


import at.electro.shop.inventory.services.product.api.models.ProductResponse;
import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(OrderAnnotation.class)
public class ProductIntTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    @DisplayName("Should put updated product rating")
    public void shouldPutUpdatedProductRating() {
        given().contentType(ContentType.JSON)
                .body(ProductTestModels.getProductResponse())
                .when()
                .put("/api/v1/products/321f737d-f8dd-4c9d-aca8-b8c6164a1dd1/rating")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(ProductResponse.class);
        ;

        ProductResponse[] response = getProducts();
        ProductResponse product = response[0];
        Assertions.assertEquals("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1", product.getUuid());
        Assertions.assertEquals(BigDecimal.valueOf(4.6), product.getRating().setScale(1, RoundingMode.HALF_UP));
        Assertions.assertEquals(6, product.getRatingsCount());
    }


    private ProductResponse[] getProducts() {
        ProductResponse[] productResponse = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(ProductResponse[].class);

        return productResponse;
    }
}