package at.electro.shop.service.transaction.suppliers;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import at.electro.shop.service.transaction.models.Transaction;
import at.electro.shop.service.transaction.models.enums.PaymentMethod;

import java.time.LocalDateTime;

import static at.electro.shop.service.transaction.suppliers.AddressSupplier.address;
import static at.electro.shop.service.transaction.suppliers.AddressSupplier.addressEntityMock;
import static at.electro.shop.service.transaction.suppliers.AddressSupplier.addressMock;
import static at.electro.shop.service.transaction.suppliers.ProductSupplier.productsMock;

public class TransactionSupplier {

  private TransactionSupplier() {

  }

  public static TransactionRequest transactionRequest() {
    TransactionRequest transaction = new TransactionRequest();
    transaction.setBuyer("5a48c43f-4c24-4ee5-bf75-430f83153349");
    transaction.setSeller("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    transaction.setCurrency("EUR");
    transaction.setCreatedAt(LocalDateTime.MIN);
    transaction.setModifiedAt(LocalDateTime.MAX);
    transaction.setPaymentMethod(PaymentMethod.PAYMENT_ON_DELIVERY);
    transaction.setAddress(addressMock());
    transaction.setProducts(productsMock());
    return transaction;
  }

  public static Transaction transactions() {
    Transaction transaction = new Transaction();
    transaction.setBuyer("5a48c43f-4c24-4ee5-bf75-430f83153349");
    transaction.setSeller("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    transaction.setCurrency("EUR");
    transaction.setCreatedAt(LocalDateTime.MIN);
    transaction.setModifiedAt(LocalDateTime.MAX);
    transaction.setPaymentMethod(PaymentMethod.PAYMENT_ON_DELIVERY);
    transaction.setAddress(address());
    transaction.setProduct("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    return transaction;
  }

  public static TransactionEntity transactionEntityMock() {
    TransactionEntity transaction = new TransactionEntity();
    transaction.setBuyer("5a48c43f-4c24-4ee5-bf75-430f83153349");
    transaction.setSeller("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    transaction.setCurrency("EUR");
    transaction.setCreatedAt(LocalDateTime.MIN);
    transaction.setModifiedAt(LocalDateTime.MAX);
    transaction.setPaymentMethod(PaymentMethod.PAYMENT_ON_DELIVERY);
    transaction.setAddress(addressEntityMock());
    transaction.setProduct("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    return transaction;
  }
}
