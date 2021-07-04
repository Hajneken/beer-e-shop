package at.electro.shop.service.transaction.mapper;

import at.electro.shop.service.api.models.TransactionRequest;
import at.electro.shop.service.api.models.TransactionResponse;
import at.electro.shop.service.transaction.dao.models.TransactionEntity;
import at.electro.shop.service.transaction.models.Transaction;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface TransactionMapper {

  @Mapping(source = "address", target = "address")
  TransactionEntity toDao(Transaction transaction);

  @Mapping(source = "address", target = "address")
  Transaction toDto(TransactionEntity transactionDto);

  @Mapping(source = "address", target = "address")
  TransactionResponse toApi(Transaction transactions);

  @Mapping(source = "address", target = "address")
  Transaction toDto(TransactionRequest transaction);

  List<TransactionResponse> toApiList(List<Transaction> transactions);

  List<Transaction> toDtoList(List<TransactionEntity> transactions);

  List<TransactionEntity> toDaoList(List<Transaction> transactions);
}
