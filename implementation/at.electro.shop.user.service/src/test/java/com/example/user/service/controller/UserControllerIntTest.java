package com.example.user.service.controller;


import com.example.user.service.api.model.cart.CartDto;
import com.example.user.service.api.model.cart.ProductDto;
import com.example.user.service.api.model.user.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    @DisplayName("Should get all Users")
    public void shouldGetAllUsers() {
        UserDto[] response = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(UserDto[].class);
        assertEquals(14, response.length);
    }

    @Test
    @Order(2)
    @DisplayName("Should get one Users")
    public void shouldGetOneUsers() {
        UserDto user = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(UserDto.class);

        assertEquals(user.getFirstName(), "Johannes");
    }


    @Test
    @Order(3)
    @DisplayName("Should get cart of one Users")
    public void shouldGetCartOfOneUsers() {
        CartDto cart = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003/cart")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(CartDto.class);

        assertEquals(cart.getUuid(), "ff80818179cd38450179cd384a450001");
    }

    @Test
    @Order(4)
    @DisplayName("Should get add product to cart")
    public void shouldAddProductToCartOfOneUsers() {
        CartDto cart = given().contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003/cart/product/abc")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(CartDto.class);

        List<ProductDto> productDtoList = cart.getProducts();
        boolean isAdded = false;
        for (ProductDto p : productDtoList) {
            if (p.getId().equals("abc")) {
                isAdded = true;
                break;
            }
        }

        assertTrue(isAdded);
    }


    @Test
    @Order(4)
    @DisplayName("Should get deleted product to cart")
    public void shouldDeletedroductToCartOfOneUsers() {
        given().contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003/cart/product/abc");


        CartDto cart = given().contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003/cart/product/abc")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(CartDto.class);

        List<ProductDto> productDtoList = cart.getProducts();
        boolean isDeleted = true;
        for (ProductDto p : productDtoList) {
            if (p.getId().equals("abc")) {
                isDeleted = false;
                break;
            }
        }

        assertTrue(isDeleted);
    }

    @Test
    @Order(5)
    @DisplayName("Should throw 404 statuscode when product not present in cart")
    public void shouldThrow404StatusCodeWhenDeletedProductWhichIsNotPresent() {
            int statusCode = given().contentType(ContentType.JSON)
                    .when()
                    .delete("/api/v1/users/008865a4-8348-11eb-8dcd-0242ac130003/cart/product/xyz")
                    .getStatusCode();
            assertEquals(statusCode, 404);
    }

    @Test
    @Order(6)
    @DisplayName("Should throw 404 statuscode when user not present")
    public void shouldThrow404StatusCodeWhenUserIsNotPresent() {
        int statusCode = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/users/008865a4-8348-11eb-8dcd")
                .getStatusCode();
        assertEquals(statusCode, 404);
    }

}
