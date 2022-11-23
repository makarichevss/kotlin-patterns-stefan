package behavioural

import org.junit.jupiter.api.Test

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

val lowerCaseFormatter = {it: String -> it.toLowerCase()}
val upperCaseFormatter = {it: String -> it.toUpperCase()}

class StrategyTest {
    @Test
    fun strategyTest() {
        val inputString = "LOREM ipsum DOLOR sit amet"
        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.printString(inputString)
        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.printString(inputString)
    }
}