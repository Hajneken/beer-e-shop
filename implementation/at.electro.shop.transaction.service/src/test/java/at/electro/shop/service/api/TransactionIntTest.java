package at.electro.shop.service.api;


import static io.restassured.RestAssured.given;

import at.electro.shop.service.api.models.TransactionResponse;
import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(OrderAnnotation.class)
public class TransactionIntTest {

  @LocalServerPort
  private Integer port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Order(1)
  @DisplayName("Should get all transactions")
  public void shouldGetAllTransactions() {
    TransactionResponse[] response = getTransactions();
    Assertions.assertEquals(1, response.length);

    TransactionResponse transaction = response[0];
    Assertions.assertEquals("008865a4-8348-11eb-8dcd-0242ac130003", transaction.getUuid());
    Assertions.assertEquals("6d8ea0ea-1162-4739-87b4-6729ef7f1a20", transaction.getBuyer());
    Assertions.assertEquals("5a48c43f-4c24-4ee5-bf75-430f83153349", transaction.getSeller());
  }

  @Test
  @Order(2)
  @DisplayName("Should create a transaction for each product in the transaction request")
  public void shouldCreateATransactions() {
    createTransactions();

    TransactionResponse[] response = getTransactions();
    Assertions.assertEquals(5, response.length);

    TransactionResponse transaction = response[0];
    Assertions.assertEquals("008865a4-8348-11eb-8dcd-0242ac130003", transaction.getUuid());
    Assertions.assertEquals("6d8ea0ea-1162-4739-87b4-6729ef7f1a20", transaction.getBuyer());
    Assertions.assertEquals("5a48c43f-4c24-4ee5-bf75-430f83153349", transaction.getSeller());

    for (int i = 1; i < response.length; i++) {
      TransactionResponse createdTransaction = response[i];
      Assertions.assertNotEquals("008865a4-8348-11eb-8dcd-0242ac130003", createdTransaction.getUuid());
      Assertions.assertEquals("5a48c43f-4c24-4ee5-bf75-430f83153349", createdTransaction.getBuyer());
      Assertions.assertEquals("6d8ea0ea-1162-4739-87b4-6729ef7f1a20", createdTransaction.getSeller());
    }
  }

  @Test
  @Order(3)
  @DisplayName("Should get all the users transactions")
  public void shouldGetAllUserTransactions() {
    TransactionResponse[] response = given().contentType(ContentType.JSON)
        .when()
          .get("/api/v1/users/5a48c43f-4c24-4ee5-bf75-430f83153349/transactions")
        .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
        .as(TransactionResponse[].class);

    Assertions.assertEquals(5, response.length);
  }

  @Test
  @Order(4)
  @DisplayName("Should get all the buyer transactions")
  public void shouldGetAllBuyerTransactions() {
    TransactionResponse[] response = given().contentType(ContentType.JSON)
        .when()
          .get("/api/v1/buyers/5a48c43f-4c24-4ee5-bf75-430f83153349/transactions")
        .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
          .as(TransactionResponse[].class);

    Assertions.assertEquals(4, response.length);
  }

  @Test
  @Order(5)
  @DisplayName("Should get all the sellers transactions")
  public void shouldGetAllSellersTransactions() {
    TransactionResponse[] response = given().contentType(ContentType.JSON)
        .when()
        .get("/api/v1/sellers/5a48c43f-4c24-4ee5-bf75-430f83153349/transactions")
          .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
          .as(TransactionResponse[].class);

    Assertions.assertEquals(1, response.length);
  }

  @Test
  @Order(6)
  @DisplayName("Should calculate seller profits")
  public void shouldCalculateSellerProfits() {
    Assertions.assertEquals(10082, getProfit().intValue());
    createTransactions();
    Assertions.assertEquals(20165, getProfit().intValue());
  }

  private void createTransactions() {
    given().contentType(ContentType.JSON)
        .body(TransactionSupplier.transactionRequest())
          .when()
        .post("/api/v1/transactions")
          .then()
          .statusCode(HttpStatus.NO_CONTENT.value());
  }

  private BigDecimal getProfit(){
    return given().contentType(ContentType.JSON)
        .when()
          .get("/api/v1/sellers/6d8ea0ea-1162-4739-87b4-6729ef7f1a20/profits")
        .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
          .as(BigDecimal.class);
  }

  private TransactionResponse[] getTransactions() {
    TransactionResponse[] response = given().contentType(ContentType.JSON)
        .when()
          .get("/api/v1/transactions")
        .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
          .as(TransactionResponse[].class);

    return response;
  }
}