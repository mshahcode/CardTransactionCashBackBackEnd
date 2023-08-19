package az.pashabank.cardzone.model.dto;


import az.pashabank.cardzone.validator.ValidCardNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCardDTO {
    @NotBlank(message = "Card number is required")
    @Size(min = 16, max = 16, message = "16 digits only")
    @ValidCardNumber
    private String pan;
    private Long customerId;
}
