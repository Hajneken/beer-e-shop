package at.electro.shop.bff.api.inventory.models.inventory;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private String seller;
    private String buyer;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private TransactionAddress transactionAddress;
    private String paymentMethod;
    private List<TransactionProduct> transactionProducts = new ArrayList<>();

    public String getSeller() {
        return seller;
    }

//    Vendor
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

    public TransactionAddress getAddress() {
        return transactionAddress;
    }

    public void setAddress(TransactionAddress transactionAddress) {
        this.transactionAddress = transactionAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<TransactionProduct> getProducts() {
        return transactionProducts;
    }

    public void setProducts(List<TransactionProduct> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }
}