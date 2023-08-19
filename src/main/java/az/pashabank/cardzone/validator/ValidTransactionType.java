package az.pashabank.cardzone.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TransactionTypeValidator.class)
public @interface ValidTransactionType {
    String message() default "Type must be either DEBIT or CREDIT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
