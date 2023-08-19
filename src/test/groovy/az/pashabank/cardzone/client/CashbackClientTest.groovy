package az.pashabank.cardzone.client

import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class CashbackClientTest extends Specification {

    CashbackClient cashbackClient = new CashbackClient()
    def "GetCashbackAmount"() {
        given:
        Double transactionAmount = 100.0
        Double expectedCashbackAmount = 2.0

        when:
        def cashbackAmount = cashbackClient.getCashbackAmount(transactionAmount)

        then:
        cashbackAmount == expectedCashbackAmount

    }

    def "GetCashbackAmountNull"() {
        given:
        Double transactionAmount = 0
        Double expectedCashbackAmount = 0.0

        when:
        def cashbackAmount = cashbackClient.getCashbackAmount(transactionAmount)

        then:
        cashbackAmount == expectedCashbackAmount

    }
}
