package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.model.dto.CardDTO;
import az.pashabank.cardzone.model.dto.NewCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card mapCardDtoToCard(CardDTO cardDTO);


    CardDTO mapCardToCardDTO(Card card);

    @Mapping(target = "pan", source = "pan")
    @Mapping(target = "customer_id", source = "customerId")
    Card mapNewCardDtoToCard(NewCardDTO newCardDTO);

}
