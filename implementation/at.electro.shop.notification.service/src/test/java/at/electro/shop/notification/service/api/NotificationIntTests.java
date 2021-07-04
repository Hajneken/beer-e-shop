package at.electro.shop.notification.service.api;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import at.electro.shop.notification.service.api.NotificationIntTestModels.NotificationIntTestModels;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingRequest;
import at.electro.shop.notification.service.productMarking.api.models.ProductMarkingResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(OrderAnnotation.class)
public class NotificationIntTests {
    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    @DisplayName("Should get all product Markings per user")
    public void shouldGetAllProductMarkingsPerUser() {
        // Arrange

        // Act
        var response = getProductMarkingsByUser(NotificationIntTestModels.user);

        // Assert
        Assertions.assertEquals(2, response.length);
        var first = response[0];
        Assertions.assertEquals(NotificationIntTestModels.product, first.getProduct());
        Assertions.assertEquals(NotificationIntTestModels.user, first.getUser());
        Assertions.assertEquals(NotificationIntTestModels.email, first.getEmailAddress());
        Assertions.assertEquals(NotificationIntTestModels.uuid, first.getUuid());
        Assertions.assertEquals(new BigDecimal(NotificationIntTestModels.price), first.getPrice());
    }

    @Test
    @Order(2)
    @DisplayName("Should post a new ProductMarking")
    public void souldAddProductMarking() {
        // Arrange

        // Act
        ProductMarkingResponse response = addProductMarking(NotificationIntTestModels.getValidProductMarkginRequest());

        // Assert
        var get = getProductMarkingsByUser(response.getUser());
        Assertions.assertNotNull(get);
        Assertions.assertEquals(1, get.length);
        Assertions.assertNotNull(response.getUuid());
        Assertions.assertEquals("6d8ea0ea-1162-4739-87b4-6729ef7f1a20", response.getProduct());
        Assertions.assertEquals("5a48c43f-4c24-4ee5-bf75-430f83153349", response.getUser());
        Assertions.assertEquals("emailAddress", response.getEmailAddress());
        Assertions.assertEquals(new BigDecimal(20.3), response.getPrice());
    }

    @Test
    @Order(3)
    @DisplayName("Should Delete ProductMarking By Uuid")
    public void shouldDeleteAProductMarkingByUuid() {
        // Arrange

        // Act
        deleteProductMarking(NotificationIntTestModels.uuid);

        // Assert
        var response = getProductMarkingsByUser(NotificationIntTestModels.user);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.length);
    }

    @Test
    @Order(4)
    @DisplayName("Should Return empty array")
    public void shouldReturnEmptyArray() {
        // Arrange

        // Act
        var response = getProductMarkingsByUser("§BABA");

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(0, response.length);
    }

    private void deleteProductMarking(String id) {
        given().contentType(ContentType.JSON).when().delete("/api/v1/productmarking/" + id).then()
                .statusCode(HttpStatus.NO_CONTENT.value()).extract().body();
    }

    private ProductMarkingResponse addProductMarking(ProductMarkingRequest request) {
        ProductMarkingResponse response = given().contentType(ContentType.JSON).body(request).when()
                .post("/api/v1/productmarking").then().statusCode(HttpStatus.OK.value()).extract().body()
                .as(ProductMarkingResponse.class);

        return response;
    }

    private ProductMarkingResponse[] getProductMarkingsByUser(String id) {
        var response = given().contentType(ContentType.JSON).when().get("/api/v1/productmarking/user/" + id).then()
                .statusCode(HttpStatus.OK.value()).extract().body().as(ProductMarkingResponse[].class);
        return response;
    }
}
