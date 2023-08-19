package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT t from Transaction t where t.has_cashback = TRUE ")
    List<Transaction> getTransactionsWithCashBack();


}
