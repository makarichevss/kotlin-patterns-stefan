package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

sealed class Country {
    object Canada : Country()
}

object Spain : Country()
object Greece : Country()
data class USA(val someProperty: String) : Country()

class Currency (val code: String)

object CurrencyFactory {
    fun currencyCountry(country: Country): Currency {
        return when (country) {
            is Spain, is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            is Country.Canada -> Currency("CAD")
        }
    }
}

class FactoryTest {

    @Test
    fun testFactory() {
        val greekCurrency = CurrencyFactory.currencyCountry(Greece).code
        val usaCurrency = CurrencyFactory.currencyCountry(Greece).code
        println("Greek currency: $greekCurrency")
        println("USA currency: $usaCurrency")
        assertThat(greekCurrency).isEqualTo("EUR")
    }
}
