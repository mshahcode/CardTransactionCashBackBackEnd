package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.model.dto.CardDTO;
import az.pashabank.cardzone.model.dto.NewCardDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-20T21:00:38+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public Card mapCardDtoToCard(CardDTO cardDTO) {
        if ( cardDTO == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.id( cardDTO.getId() );
        card.pan( cardDTO.getPan() );
        card.customer_id( cardDTO.getCustomer_id() );
        card.balance( cardDTO.getBalance() );

        return card.build();
    }

    @Override
    public CardDTO mapCardToCardDTO(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDTO.CardDTOBuilder cardDTO = CardDTO.builder();

        cardDTO.id( card.getId() );
        cardDTO.pan( card.getPan() );
        cardDTO.customer_id( card.getCustomer_id() );
        cardDTO.balance( card.getBalance() );

        return cardDTO.build();
    }

    @Override
    public Card mapNewCardDtoToCard(NewCardDTO newCardDTO) {
        if ( newCardDTO == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.pan( newCardDTO.getPan() );
        card.customer_id( newCardDTO.getCustomerId() );

        return card.build();
    }
}
