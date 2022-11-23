package behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento() = Memento(state)
    fun restoreMemento(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = arrayListOf<Memento>()

    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restoreState(index: Int): Memento = mementoList[index]
}

class Initiator

class MementoTest {
    @Test
    fun testMemento() {
        val originator = Originator("initial state")
        val caretaker = CareTaker()
        caretaker.saveState(originator.createMemento())

        originator.state = "state 1"
        caretaker.saveState(originator.createMemento())

        originator.state = "state 2"
        caretaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")
        assertThat(originator.state).isEqualTo("state 2")

        originator.restoreMemento(caretaker.restoreState(1))
        assertThat(originator.state).isEqualTo("state 1")
        println("Current state is ${originator.state}")
    }
}

