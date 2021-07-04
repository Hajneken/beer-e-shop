package at.electro.shop.service.api.models;

public enum TransactionRole {
  SELLER("Seller"),
  BUYER("Buyer"),
  ALL("All");

  private String name;

  TransactionRole(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
