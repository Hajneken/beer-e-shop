package at.electro.shop.service.transaction.converter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.transaction.mapper.TransactionMapper;
import at.electro.shop.service.transaction.models.Transaction;
import at.electro.shop.service.transaction.suppliers.TransactionSupplier;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionConverterTest {

  @Mock
  private TransactionMapper transactionMapper;

  @InjectMocks
  private TransactionConverter transactionConverter;

  @Test
  @DisplayName("Should convert multiple bought products into individual transactions")
  public void shouldConvertProductsIntoIndividualTransactions() {
    when(transactionMapper.toDto(any(TransactionRequest.class))).thenReturn(new Transaction());
    List<Transaction> transactions = transactionConverter.convert(TransactionSupplier.transactionRequest());

    Assertions.assertEquals(4, transactions.size());

    verify(transactionMapper, times(4)).toDto(any(TransactionRequest.class));
  }
}