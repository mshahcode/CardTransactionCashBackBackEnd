package az.pashabank.cardzone.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilteringCardDTO {
    @Min(value = 0)
    private Double minBalance;
    @Min(value = 0)
    private Double maxBalance;
}
