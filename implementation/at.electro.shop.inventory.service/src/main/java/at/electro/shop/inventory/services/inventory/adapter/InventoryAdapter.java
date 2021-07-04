package at.electro.shop.inventory.services.inventory.adapter;

import at.electro.shop.inventory.services.inventory.api.models.BoughtProductList;
import at.electro.shop.inventory.services.inventory.api.models.Transaction;
import at.electro.shop.inventory.services.inventory.api.models.TransactionProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryAdapter {
//    TODO
//    Transactions -> Inventory
    public List<BoughtProductList> convertTransactionToInventory(List<Transaction> transactions) {
        List<BoughtProductList> boughtProductLists = new ArrayList<>();
        for (Transaction transaction : transactions) {
            BoughtProductList boughtProductList = new BoughtProductList();
            boughtProductList.setVendor(transaction.getSeller());

            List<TransactionProduct> transactionProducts = transaction.getProducts();
            List<String> boughtProducts = new ArrayList<>();
            for (TransactionProduct transactionProduct : transactionProducts) {
                boughtProducts.add(transactionProduct.getId());
            }
            boughtProductList.setProductUuids(boughtProducts);
            boughtProductLists.add(boughtProductList);
        }
        return boughtProductLists;
    }
}
