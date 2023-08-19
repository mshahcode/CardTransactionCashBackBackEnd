package az.pashabank.cardzone.service

import az.pashabank.cardzone.client.CashbackClient
import az.pashabank.cardzone.dao.entity.Card
import az.pashabank.cardzone.dao.entity.Transaction
import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.dao.repository.TransactionRepository
import az.pashabank.cardzone.model.dto.NewTransactionDTO
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDateTime

@Title("Unit test for the Transaction Service")
@Subject(TransactionService)
class TransactionServiceTest extends Specification {

    TransactionRepository transactionRepository = Mock(TransactionRepository)
    CardRepository cardRepository = Mock(CardRepository)
    CashbackClient cashbackClient = Mock(CashbackClient)

    TransactionService transactionService = new TransactionService(transactionRepository, cardRepository, cashbackClient)

    def "should add transaction and update card balance"() {
        given:
        Long cardId = 1L
        Double amount = 100.0
        LocalDateTime t = LocalDateTime.of(2015,
                1, 29, 19, 30, 40);
        Card card = new Card(cardId, "1234567891234567", 1, 20.0, t, null);

        NewTransactionDTO newTransactionDTO = new NewTransactionDTO("DEBIT", 100, true);

        when:
        transactionService.addTransaction(newTransactionDTO, cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.of(card)
        0 * transactionRepository.save(_ as Transaction)
        0 * cardRepository.save(card)
        thrown(IllegalArgumentException)
    }

    def "No card id found for transaction"() {
        given:
        Long cardId = 1L
        NewTransactionDTO newTransactionDTO = new NewTransactionDTO("DEBIT", 100, true);

        when:
        transactionService.addTransaction(newTransactionDTO, cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.empty()
        0 * transactionRepository.save(_ as Transaction)
        0 * cardRepository.save(_)
        thrown(IllegalArgumentException)
    }

    def "transaction function testing with enough money to debit transaction"() {
        given:
        def id = 1L
        def transactionDTO = new NewTransactionDTO("DEBIT", 40.0, true)
        def card = new Card()
        card.setId(id)
        card.setBalance(250.50)

        when:
        transactionService.addTransaction(transactionDTO, id)

        then:
        1 * this.cardRepository.findById(id) >> Optional.of(card)
        1 * this.transactionRepository.save(_);
        1 * this.cardRepository.save(_);
        card.getBalance() == 210.50
    }

    def "processTransactionCashBacks"() {
        given:
        Card card = new Card()
        card.setId(1L)
        card.setBalance(10000.0)

        Transaction t1 = new Transaction(1L,"CREDIT",200.4,true,null,card)
        Transaction t2 = new Transaction(2L,"DEBIT",200.4,true,null,card)
        Transaction t3 = new Transaction(3L,"CREDIT",200.4,true,null,card)
        def transactionList = [t1,t2,t3]

        when:
        transactionService.processTransactionCashBacks()

        then:
        1 * this.transactionRepository.getTransactionsWithCashBack() >> transactionList
        3 * cashbackClient.getCashbackAmount(_) >> 2.0
        3 * this.cardRepository.findById(_) >> Optional.of(card)
        3 * this.transactionRepository.save(_);
        3 * this.cardRepository.save(_)
        card.getBalance() == 10006.0
    }

}


