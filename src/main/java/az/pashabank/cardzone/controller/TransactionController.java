package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.NewTransactionDTO;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransaction(@RequestBody @Valid NewTransactionDTO newTransactionDTO, @PathVariable Long id){
        this.transactionService.addTransaction(newTransactionDTO,id);
    }
}
