package az.pashabank.cardzone.scheduler;

import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashbackScheduler {

    private final TransactionService service;


    @Scheduled(cron = "0 0 0 * * *")
    public void addCashbackToCards(){
        this.service.processTransactionCashBacks();
    }


}
