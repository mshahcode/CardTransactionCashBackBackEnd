package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CardDTO;
import az.pashabank.cardzone.model.dto.FilteringCardDTO;
import az.pashabank.cardzone.model.dto.NewCardDTO;
import az.pashabank.cardzone.model.exception.CardNotFoundException;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<CardDTO> getAllCards(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return this.cardService.getAllCard(page,size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO getCardById(@PathVariable Long id) throws CardNotFoundException {
        return this.cardService.getCardById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody @Valid NewCardDTO newCardDTO){
        this.cardService.addNewCard(newCardDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCard(@PathVariable Long id) throws Exception {
        this.cardService.deleteCard(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<CardDTO> getFilteredCards(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestBody @Valid FilteringCardDTO filteringCardDTO) {
        return this.cardService.getFilteredCard(page,size, filteringCardDTO);
    }
}
