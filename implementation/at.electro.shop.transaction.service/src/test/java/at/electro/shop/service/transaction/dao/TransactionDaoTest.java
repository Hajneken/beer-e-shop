

package at.electro.shop.service.transaction.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import at.electro.shop.service.transaction.dao.persistence.EventStore;
import at.electro.shop.service.transaction.dao.persistence.TransactionRepository;
import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TransactionDaoTest {

  @Mock
  private TransactionMapper transactionMapper;

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private EventStore eventStore;

  @InjectMocks
  private TransactionDao transactionDao;

  @Test
  @DisplayName("Should get empty transactions list")
  public void shouldGetUserTransactions() {
    Mockito.when(transactionRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
    List list = transactionDao.fetchAll(Pageable.unpaged());
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  @DisplayName("Should get seller transactions list")
  public void shouldGetSellerTransactions() {
    final String seller = "6d8ea0ea-1162-4739-87b4-6729ef7f1a20";

    Page<TransactionEntity> transactionEntities = new PageImpl<>(List.of(TransactionSupplier.transactionEntityMock()));
    Mockito.when(transactionRepository.findBySeller(eq(seller), any(Pageable.class))).thenReturn(transactionEntities);
    Mockito.when(transactionMapper.toDtoList(any())).thenReturn(List.of(TransactionSupplier.transactions()));

    List list = transactionDao.getSeller(seller, Pageable.unpaged());
    Assertions.assertEquals(1, list.size());
  }

  @Test
  @DisplayName("Should get buyer transactions list")
  public void shouldGetBuyerTransactions() {
    final String buyer = "6d8ea0ea-1162-4739-87b4-6729ef7f1a20";

    Page<TransactionEntity> transactionEntities = new PageImpl<>(List.of(TransactionSupplier.transactionEntityMock()));
    Mockito.when(transactionRepository.findByBuyer(eq(buyer), any(Pageable.class))).thenReturn(transactionEntities);
    Mockito.when(transactionMapper.toDtoList(any())).thenReturn(List.of(TransactionSupplier.transactions()));

    List list = transactionDao.getBuyer(buyer, Pageable.unpaged());
    Assertions.assertEquals(1, list.size());
  }

  @Test
  @DisplayName("Should create transactions list")
  public void shouldCreateTransactions() {
    Mockito.when(transactionMapper.toDtoList(any())).thenReturn(List.of(TransactionSupplier.transactions()));

    List list = transactionDao.create(List.of(TransactionSupplier.transactions()));

    verify(transactionRepository, times(1)).saveAll(any());
    verify(eventStore, times(1)).publish(any());

    Assertions.assertEquals(1, list.size());
  }

  @Test
  @DisplayName("Should calculate seller profits when no profit was found")
  public void shouldCalculateSellerProfitsWhenNoProfitIsFound() {
    BigDecimal profits = transactionDao.getProfits("sellerId");
    Assertions.assertEquals(BigDecimal.ZERO, profits);
  }

  @Test
  @DisplayName("Should calculate seller profits")
  public void shouldCalculateSellerProfits() {
    Mockito.when(transactionRepository.calculateProfit("sellerId")).thenReturn(BigDecimal.TEN);

    BigDecimal profits = transactionDao.getProfits("sellerId");

    Assertions.assertEquals(BigDecimal.TEN, profits);
  }
}