package at.electro.shop.service.controller;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.transaction.services.TransactionService;
import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

  @Mock
  private TransactionService transactionService;

  @InjectMocks
  private TransactionController transactionController;

  @Captor
  private ArgumentCaptor<Pageable> pageable;

  @Captor
  private ArgumentCaptor<String> id;

  @Test
  @DisplayName("Should get transactions with correct params")
  public void shouldGetTransactions() {
    transactionController.get(3, 100, "date");

    Mockito.verify(transactionService, Mockito.times(1)).get(pageable.capture());

    Pageable actual = pageable.getValue();
    Assertions.assertEquals(3, actual.getPageNumber());
    Assertions.assertEquals(100, actual.getPageSize());
    Assertions.assertEquals("date: ASC", actual.getSort().toString());
  }

  @Test
  @DisplayName("Should get seller transactions with correct params")
  public void shouldGetSellerTransactions() {
    transactionController.getSellerTransactions("sellerId", 3, 100, "date");

    Mockito.verify(transactionService, Mockito.times(1))
        .getSellerTransactions(id.capture(), pageable.capture());

    String sellerId = id.getValue();
    Pageable actual = pageable.getValue();
    Assertions.assertEquals("sellerId", sellerId);
    Assertions.assertEquals(3, actual.getPageNumber());
    Assertions.assertEquals(100, actual.getPageSize());
    Assertions.assertEquals("date: ASC", actual.getSort().toString());
  }

  @Test
  @DisplayName("Should get buyer transactions with correct params")
  public void shouldGetBuyerTransactions() {
    transactionController.getSellerTransactions("buyerId", 100, 3, "date");

    Mockito.verify(transactionService, Mockito.times(1))
        .getSellerTransactions(id.capture(), pageable.capture());

    String buyer = id.getValue();
    Pageable actual = pageable.getValue();
    Assertions.assertEquals("buyerId", buyer);
    Assertions.assertEquals(100, actual.getPageNumber());
    Assertions.assertEquals(3, actual.getPageSize());
    Assertions.assertEquals("date: ASC", actual.getSort().toString());
  }

  @Test
  @DisplayName("Should get user transactions with correct params")
  public void shouldGetUserTransactions() {
    transactionController.getUserTransactions("userId", 100, 3, "date");

    Mockito.verify(transactionService, Mockito.times(1))
        .getUserTransactions(id.capture(), pageable.capture());

    String user = id.getValue();
    Pageable actual = pageable.getValue();
    Assertions.assertEquals("userId", user);
    Assertions.assertEquals(100, actual.getPageNumber());
    Assertions.assertEquals(3, actual.getPageSize());
    Assertions.assertEquals("date: ASC", actual.getSort().toString());
  }

  @Test
  @DisplayName("Should post transactions with correct params")
  public void shouldCreateTransactions() {
    TransactionRequest given = TransactionSupplier.transactionRequest();
    transactionController.post(given);

    Mockito.verify(transactionService, Mockito.times(1)).post(given);
  }

  @Test
  @DisplayName("Should call get profits")
  public void shouldGetProfits() {
    transactionController.getProfits("sellerId");

    Mockito.verify(transactionService, Mockito.times(1)).getProfits("sellerId");
  }
}