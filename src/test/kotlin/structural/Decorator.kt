package structural

import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine: making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine: making large coffee")
    }
}

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) :
    CoffeeMachine by coffeeMachine {

    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: making large coffee")
    }

    fun makeMilkCoffee() {
        println("Enhanced coffee machine: making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Adding milk")
    }
}

class DecoratorTest {
    @Test
    internal fun testDecorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

        enhancedMachine.makeSmallCoffee()
        enhancedMachine.makeLargeCoffee()
        enhancedMachine.makeSmallCoffee()
        enhancedMachine.makeMilkCoffee()

    }
}