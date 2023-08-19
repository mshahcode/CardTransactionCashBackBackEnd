package az.pashabank.cardzone.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CashbackClient {
    private final RestTemplate restTemplate = new RestTemplate();


    public double getCashbackAmount(double transactionAmount) {
        String url = "https://cardzone-cashback-api-c2f5b8105e2b.herokuapp.com/api/cashback?transactionAmount=" + transactionAmount;
        CashbackResponse response = restTemplate.getForObject(url, CashbackResponse.class);
        if (response != null) {
            return response.getCashbackAmount();
        }
        return 0.0;
    }
}
