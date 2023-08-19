package az.pashabank.cardzone.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
        if (cardNumber == null || cardNumber.isBlank()) {
            return false;
        }

        // Check if the card number has exactly 16 digits
        if (cardNumber.length() != 16) {
            return false;
        }

        // Perform Luhn algorithm validation
        int nDigits = cardNumber.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cardNumber.charAt(i) - '0';

            if (isSecond) {
                d = d * 2;
                if (d > 9) {
                    d = d - 9;
                }
            }

            nSum += d;
            isSecond = !isSecond;
        }

        return nSum % 10 == 0;
    }
}
