package at.electro.shop.service.transaction.dao.persistence;

import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

  Page<TransactionEntity> findBySeller(String seller, Pageable pageable);

  Page<TransactionEntity> findByBuyer(String buyer, Pageable pageable);

  @Query("SELECT SUM(t.price) FROM Transactions t WHERE t.seller = ?1")
  BigDecimal calculateProfit(String seller);
}
