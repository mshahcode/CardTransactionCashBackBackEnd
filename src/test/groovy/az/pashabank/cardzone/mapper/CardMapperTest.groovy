package az.pashabank.cardzone.mapper

import az.pashabank.cardzone.client.CashbackClient
import az.pashabank.cardzone.client.CashbackResponse
import az.pashabank.cardzone.dao.entity.Card
import az.pashabank.cardzone.model.dto.CardDTO
import az.pashabank.cardzone.model.dto.NewCardDTO
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

class CardMapperTest extends Specification {

    def "MapCardDtoToCard"() {
        given:
        CardDTO cardDTO = new CardDTO(1L, "1234567891234567", 1, 20.0);

        when:
        def car = CardMapper.INSTANCE.mapCardDtoToCard(cardDTO)

        then:
        car.getPan() == "1234567891234567"
    }

    def "mapCardToCardDTO"(){
        given:
        LocalDateTime t = LocalDateTime.of(2015,
                1, 29, 19, 30, 40);
        Card card = new Card(1L, "1234567891234567", 1, 20.0, t ,null);

        when:
        CardDTO result = CardMapper.INSTANCE.mapCardToCardDTO(card)

        then:
        result.getPan() == "1234567891234567"
    }

    def "mapNewCardDtoToCard"(){
        given:
        NewCardDTO newCardDTO = new NewCardDTO("1234567891234567",1);

        when:
        Card result = CardMapper.INSTANCE.mapNewCardDtoToCard(newCardDTO)

        then:
        result.getPan() == "1234567891234567"

    }
}
