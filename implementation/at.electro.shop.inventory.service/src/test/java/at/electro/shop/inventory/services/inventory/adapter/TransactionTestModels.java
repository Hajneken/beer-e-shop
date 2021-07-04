package at.electro.shop.inventory.services.inventory.adapter;

import at.electro.shop.inventory.services.inventory.api.models.Transaction;
import at.electro.shop.inventory.services.inventory.api.models.TransactionProduct;
import at.electro.shop.inventory.services.product.dao.ProductTestModels;
import at.electro.shop.inventory.services.product.models.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionTestModels {

    public static List<TransactionProduct> getTransactionProducts(){
        List<TransactionProduct> transactionProducts = new ArrayList<>();

        TransactionProduct transactionProduct = new TransactionProduct();
        transactionProduct.setId("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        transactionProduct.setPrice(BigDecimal.valueOf(3.5));

        transactionProducts.add(transactionProduct);

        return transactionProducts;
    }

    public static List<Transaction> getTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        List<TransactionProduct> transactionProducts = TransactionTestModels.getTransactionProducts();

        Transaction transaction = new Transaction();

        transaction.setBuyer("5a48c43f-4c24-4ee5-bf75-430f83153349");
        transaction.setSeller("6d8ea0ea-1162-4739-87b4-6729ef7f1a20");
        transaction.setCurrency("EUR");
        transaction.setCreatedAt(LocalDateTime.parse("2021-06-04T23:38:59.279897"));
        transaction.setModifiedAt(LocalDateTime.parse("2021-06-04T23:38:59.279897"));
        transaction.setPaymentMethod("PAYMENT_ON_DELIVERY");
        transaction.setProducts(transactionProducts);

        transactions.add(transaction);

        return transactions;
    } 
}
