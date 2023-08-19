package az.pashabank.cardzone.mapper

import az.pashabank.cardzone.model.dto.NewTransactionDTO
import spock.lang.Specification

class TransactionMapperTest extends Specification {
    def "MapNewTransactionDtoToTransaction"() {
        given:
        NewTransactionDTO newTransactionDTO = new NewTransactionDTO("DEBIT",200.0,true);

        when:
        var result = TransactionMapper.INSTANCE.mapNewTransactionDtoToTransaction(newTransactionDTO)

        then:
        result.getAmount() == newTransactionDTO.getAmount()
        result.getType() == newTransactionDTO.getType()
        result.getHas_cashback() == newTransactionDTO.getHasCashback()
    }
}
