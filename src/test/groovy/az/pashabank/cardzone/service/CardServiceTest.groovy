package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.entity.Card
import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.mapper.CardMapper
import az.pashabank.cardzone.model.dto.CardDTO
import az.pashabank.cardzone.model.dto.NewCardDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import spock.lang.Specification
import org.springframework.data.domain.PageRequest


class CardServiceTest extends Specification {

    // Dependencies ( will be mocked )
    private CardRepository cardRepository;
    private CardMapper cardMapper;

    // Class to be tested
    private CardService cardService;

    def setup(){
		cardMapper = Mock(CardMapper)
        cardRepository = Mock(CardRepository)
        cardService = new CardService(cardRepository, cardMapper)
    }

    def "getAllCard_shouldReturnAllCardsWithCorrectPagination"() {
        // Test setup
        given:
        int page = 0
        int size = 10
        PageRequest pageable = PageRequest.of(page, size)

        List<Card> cards = [new Card(), new Card()]
        Page<Card> cardPage = new PageImpl<>(cards, pageable, cards.size())

        when:
        def result = this.cardService.getAllCard(page, size)

        then:
        1 * this.cardRepository.findAllBy(pageable) >> cardPage
        2 * this.cardMapper.mapCardToCardDTO(_)
        result.getTotalElements() == cardPage.getTotalElements()
    }

    def "GetCardById"() {
        given:
        Long id = 1L;
        Card card = new Card();
        card.setId(id);
        card.setBalance(100.5)
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(id);
        cardDTO.setBalance(100.5)

        when:
        def result = cardService.getCardById(id)

        then:
            1 * cardRepository.findById(id) >> Optional.of(card)
            1 * cardMapper.mapCardToCardDTO(_) >> cardDTO
        result.getId() == 1L
        result.getBalance() == 100.5

    }

    def "should add new card"() {
        given:
        NewCardDTO newCardDTO = new NewCardDTO();
        Card card = new Card();

        when:
        cardService.addNewCard(newCardDTO)

        then:
        1 * this.cardMapper.mapNewCardDtoToCard(newCardDTO) >> card
        1 * cardRepository.save(card)
        card.getBalance() == 0.0
    }

    def "should delete card by ID"() {
        given:
        Long id = 1L
        Card card = new Card();
        card.setId(id);

        when:
        cardService.deleteCard(id)

        then:
        1 * cardRepository.findById(id) >> Optional.of(card)

        then:
        card.getTransactions() == null

        then:
        1 * cardRepository.delete(card)
    }
}
