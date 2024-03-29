package at.electro.shop.service.api.models;

import at.electro.shop.service.transaction.models.enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

  private String uuid;
  private String seller;
  private String buyer;
  private String product;
  private BigDecimal price;
  private String currency;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private PaymentMethod paymentMethod;
  private AddressRequest address;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(LocalDateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public AddressRequest getAddress() {
    return address;
  }

  public void setAddress(AddressRequest address) {
    this.address = address;
  }
}