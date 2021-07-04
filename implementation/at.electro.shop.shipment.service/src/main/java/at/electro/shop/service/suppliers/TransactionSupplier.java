package at.electro.shop.service.suppliers;

import at.electro.shop.service.api.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionSupplier {

  private TransactionSupplier() {

  }

  public static List<Transaction> transactions() {
    List<Transaction> transactions = new ArrayList<>();
    Transaction transaction = new Transaction();
    transaction.setBuyer("5a48c43f-4c24-4ee5-bf75-430f83153349");
    transaction.setSeller("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
    transaction.setCurrency("EUR");
    transaction.setCreatedAt("2021-06-04T23:38:59.279897");
    transaction.setModifiedAt("2021-06-04T23:38:59.279897");
    transaction.setPaymentMethod("PAYMENT_ON_DELIVERY");
    transaction.setAddress(AddressSupplier.addressMock());
    transaction.setProduct(ProductSupplier.productA().getId());

    transactions.add(transaction);
    return transactions;
  }
}
