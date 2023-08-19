package az.pashabank.cardzone.model.dto;


import az.pashabank.cardzone.validator.ValidTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTransactionDTO {
    @NotBlank(message = "Type can not be empty")
    @ValidTransactionType
    private String type;
    @NotNull
    @DecimalMin(value = "0.0")
    private Double amount;
    private Boolean hasCashback;
}
