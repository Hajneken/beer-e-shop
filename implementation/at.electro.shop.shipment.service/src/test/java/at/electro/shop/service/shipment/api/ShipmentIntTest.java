package at.electro.shop.service.shipment.api;


import at.electro.shop.service.api.models.ShipmentStatus;
import at.electro.shop.service.api.models.ShipmentUpdate;
import at.electro.shop.service.suppliers.ShipmentSupplier;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(OrderAnnotation.class)
public class ShipmentIntTest {

  @LocalServerPort
  private Integer port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Order(1)
  @DisplayName("Should get all shipments")
  public void shouldGetAllShipments() {
    ShipmentUpdate[] response = getShipments();
    Assertions.assertEquals(1, response.length);

    ShipmentUpdate shipmentUpdate = response[0];
    Assertions.assertEquals("008865a4-8348-11eb-8dcd-0242ac130003", shipmentUpdate.getUuid());
    Assertions.assertEquals("4c68e51d-04d5-4c3d-b943-8903rc0ea92f", shipmentUpdate.getProduct());
  }

  @Test
  @Order(2)
  @DisplayName("Should update shipment status")
  public void shouldUpdateShipmentStatus() {
    given().contentType(ContentType.JSON)
            .body(ShipmentSupplier.shipmentUpdateMock())
            .when()
            .post("/api/v1/shipment/008865a4-8348-11eb-8dcd-0242ac130003/status/Delivered")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

    ShipmentUpdate[] response = getShipments();
    Assertions.assertEquals(1, response.length);

    ShipmentUpdate shipmentUpdate = response[0];
    Assertions.assertEquals("008865a4-8348-11eb-8dcd-0242ac130003", shipmentUpdate.getUuid());
    Assertions.assertEquals(ShipmentStatus.Delivered, shipmentUpdate.getStatus());
  }


  private ShipmentUpdate[] getShipments() {
    ShipmentUpdate[] response = given().contentType(ContentType.JSON)
        .when()
          .get("/api/v1/shipments")
        .then()
          .statusCode(HttpStatus.OK.value())
        .extract()
          .body()
          .as(ShipmentUpdate[].class);

    return response;
  }
}