package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.model.dto.NewTransactionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T21:00:38+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl extends TransactionMapper {

    @Override
    public Transaction mapNewTransactionDtoToTransaction(NewTransactionDTO newTransactionDTO) {
        if ( newTransactionDTO == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.has_cashback( newTransactionDTO.getHasCashback() );
        transaction.type( newTransactionDTO.getType() );
        transaction.amount( newTransactionDTO.getAmount() );

        return transaction.build();
    }
}
