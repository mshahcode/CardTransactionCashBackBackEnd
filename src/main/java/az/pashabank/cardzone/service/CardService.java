package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDTO;
import az.pashabank.cardzone.model.dto.FilteringCardDTO;
import az.pashabank.cardzone.model.dto.NewCardDTO;
import az.pashabank.cardzone.model.exception.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public Page<CardDTO> getAllCard(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var allCards = this.cardRepository.findAllBy(pageable);
        return allCards.map(this.cardMapper::mapCardToCardDTO);
    }

    public CardDTO getCardById(Long id) throws CardNotFoundException {
        Card card = this.cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("No such card exists with id: " + id));
        return this.cardMapper.mapCardToCardDTO(card);
    }

    public void addNewCard(NewCardDTO newCardDTO){
        Card card = this.cardMapper.mapNewCardDtoToCard(newCardDTO);
        card.setBalance(0.0);
        this.cardRepository.save(card);
    }

    public void deleteCard(Long id){
        Card card = this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        card.setTransactions(null);
        this.cardRepository.delete(card);
    }


    public Page<CardDTO> getFilteredCard(int page, int size, FilteringCardDTO filteringCardDTO) {
        Pageable pageable = PageRequest.of(page, size);
        var allCards = this.cardRepository.findAllByFiltered(filteringCardDTO.getMinBalance(),
                filteringCardDTO.getMaxBalance(),pageable);
        return allCards.map(this.cardMapper::mapCardToCardDTO);
    }


}
