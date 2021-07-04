package at.electro.shop.service.suppliers;

import at.electro.shop.service.api.models.Product;

import java.math.BigDecimal;

public class ProductSupplier {

  private ProductSupplier() {

  }

  public static Product productA() {
    Product product = new Product();
    product.setId("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
    product.setPrice(BigDecimal.valueOf(29.21));
    return product;
  }

  public static Product productB() {
    Product product = new Product();
    product.setId("4c68e51d-04d5-4c3d-b943-79e7fcaea92f");
    product.setPrice(BigDecimal.valueOf(10021.23));
    return product;
  }
}
