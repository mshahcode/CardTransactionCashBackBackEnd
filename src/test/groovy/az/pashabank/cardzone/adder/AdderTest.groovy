package az.pashabank.cardzone.adder

import az.pashabank.cardzone.Adder
import az.pashabank.cardzone.Multiplier
import spock.lang.Specification

class AdderTest extends Specification {

    void checksum(){
        when: ""
        Adder adder = new Adder()
        def summation = adder.add(1,2)
        def mapper = ["Mika":1,"Aydin":2]

        then:
        summation == 3
    }

    def "Adding two numbers"(){

        when:
        Adder adder = new Adder(1,2,3,5)
        def summation = adder.add(1,2)

        then:
        summation == 3
    }

    def "Multiplication and Summation combined code"(){
        when:
        Adder adder = new Adder()
        Multiplier multiplier = new Multiplier()

        then:
        multiplier.multiply(adder.add(1,2),2) == 6

        and:
        multiplier.multiply(adder.add(3,2),2) == 10

    }
}
