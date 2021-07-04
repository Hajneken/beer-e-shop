package at.electro.shop.service.transaction.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import at.electro.shop.service.transaction.converter.TransactionConverter;
import at.electro.shop.service.transaction.dao.TransactionDao;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @Mock
  private TransactionMapper transactionMapper;

  @Mock
  private TransactionDao transactionDao;

  @Mock
  private TransactionConverter transactionConverter;

  @InjectMocks
  private TransactionService transactionService;

  @Test
  @DisplayName("Should get all transactions")
  public void shouldGetAllTransactions() {
    transactionService.get(Pageable.unpaged());
    verify(transactionDao).fetchAll(any());
    verify(transactionMapper).toApiList(any());
  }

  @Test
  @DisplayName("Should get seller transactions")
  public void shouldGetSellerTransactions() {
    transactionService.getSellerTransactions("seller", Pageable.unpaged());
    verify(transactionDao).getSeller(eq("seller"), any());
    verify(transactionDao, never()).getBuyer(any(), any());
    verify(transactionMapper).toApiList(any());
  }

  @Test
  @DisplayName("Should get buyer transactions")
  public void shouldGetBuyerTransactions() {
    transactionService.getBuyerTransactions("buyer", Pageable.unpaged());
    verify(transactionDao, never()).getSeller(any(), any());
    verify(transactionDao).getBuyer(eq("buyer"), any());
    verify(transactionMapper).toApiList(any());
  }

  @Test
  @DisplayName("Should get user transactions")
  public void shouldGetUserTransactions() {
    transactionService.getUserTransactions("user", Pageable.unpaged());
    verify(transactionDao).getSeller(eq("user"), any());
    verify(transactionDao).getBuyer(eq("user"), any());
    verify(transactionMapper, times(2)).toApiList(any());
  }

  @Test
  @DisplayName("Should create a transaction")
  public void shouldCreateTransaction() {
    transactionService.post(TransactionSupplier.transactionRequest());
    verify(transactionDao).create(any());
    verify(transactionConverter).convert(any());
  }

  @Test
  @DisplayName("Should get seller profits")
  public void shouldGetProfits() {
    transactionService.getProfits("sellerId");
    verify(transactionDao).getProfits("sellerId");
  }
}