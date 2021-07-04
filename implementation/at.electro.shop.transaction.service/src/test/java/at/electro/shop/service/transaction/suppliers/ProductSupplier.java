package at.electro.shop.service.transaction.suppliers;

import at.electro.shop.service.api.models.ProductRequest;
import java.math.BigDecimal;
import java.util.List;

public class ProductSupplier {

  private ProductSupplier() {

  }

  private static ProductRequest productA() {
    ProductRequest product = new ProductRequest();
    product.setId("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
    product.setPrice(BigDecimal.valueOf(29.21));
    return product;
  }

  private static ProductRequest productB() {
    ProductRequest product = new ProductRequest();
    product.setId("4c68e51d-04d5-4c3d-b943-79e7fcaea92f");
    product.setPrice(BigDecimal.valueOf(10021.23));
    return product;
  }

  private static ProductRequest productC() {
    ProductRequest product = new ProductRequest();
    product.setId("8de5155a-e36b-4b3b-83d2-631e07df9078");
    product.setPrice(BigDecimal.valueOf(10.00));
    return product;
  }

  private static ProductRequest productD() {
    ProductRequest product = new ProductRequest();
    product.setId("dfc6d5d8-3d15-4d8a-a29a-01f5894e122e");
    product.setPrice(BigDecimal.valueOf(22.21));
    return product;
  }

  public static List<ProductRequest> productsMock() {
    return List.of(productA(), productB(), productC(), productD());
  }
}
