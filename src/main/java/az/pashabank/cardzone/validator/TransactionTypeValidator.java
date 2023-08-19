package az.pashabank.cardzone.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class TransactionTypeValidator implements ConstraintValidator<ValidTransactionType, String> {
    @Override
    public boolean isValid(String type, ConstraintValidatorContext context) {
        List<String> validTypes = Arrays.asList("DEBIT","CREDIT");
        return validTypes.contains(type);
    }
}
