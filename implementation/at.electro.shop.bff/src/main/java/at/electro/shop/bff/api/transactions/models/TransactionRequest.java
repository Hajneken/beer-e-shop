package at.electro.shop.bff.api.transactions.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRequest {

    private String seller;
    private String buyer;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Address address;
    private PaymentMethod paymentMethod;
    private List<TransactionProduct> products = new ArrayList<>();

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<TransactionProduct> getProducts() {
        return products;
    }

    public void setProducts(List<TransactionProduct> products) {
        this.products = products;
    }
}