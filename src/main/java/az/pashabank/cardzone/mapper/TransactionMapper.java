package az.pashabank.cardzone.mapper;


import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.model.dto.CardDTO;
import az.pashabank.cardzone.model.dto.NewTransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    public static final TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "has_cashback",source = "hasCashback")
    @Mapping(target = "type",source = "type")
    @Mapping(target = "amount",source = "amount")
    public abstract Transaction mapNewTransactionDtoToTransaction(NewTransactionDTO newTransactionDTO);

}
