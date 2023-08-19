package az.pashabank.cardzone.service;

import az.pashabank.cardzone.client.CashbackClient;
import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.dao.repository.TransactionRepository;
import az.pashabank.cardzone.mapper.TransactionMapper;
import az.pashabank.cardzone.model.dto.NewTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CashbackClient cashbackClient;

    public void addTransaction(NewTransactionDTO newTransactionDTO, Long id) {
        var card = this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card not found with ID: " + id));
        Transaction transaction = TransactionMapper.INSTANCE.mapNewTransactionDtoToTransaction(newTransactionDTO);
        if(Objects.equals(newTransactionDTO.getType(), "DEBIT")){
            if(card.getBalance() < newTransactionDTO.getAmount()){
                throw new IllegalArgumentException("Not enough money in card to debit: " + id);
            }
            card.setBalance(card.getBalance() - transaction.getAmount());
        }else{
            card.setBalance(card.getBalance() + transaction.getAmount());
        }
        transaction.setCard(card);
        this.transactionRepository.save(transaction);
        this.cardRepository.save(card);
    }


    public void processTransactionCashBacks() {
        var transactions = this.transactionRepository.getTransactionsWithCashBack();
        for (Transaction transaction: transactions) {
            double cashbackAmount = cashbackClient.getCashbackAmount(transaction.getAmount());
            System.out.println("Cashback amount for transaction ID " + transaction.getId() + ": " + cashbackAmount);
            Card card = transaction.getCard();
            // add cashback also as transaction
            NewTransactionDTO newTransactionDTO = new NewTransactionDTO("CREDIT",cashbackAmount,false);
            addTransaction(newTransactionDTO,card.getId());
        }
    }

}

