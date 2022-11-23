package behavioural

import org.junit.jupiter.api.Test

interface Command {
    fun execute()
}

class OrderAddCommand(val id: Long) : Command {
    override fun execute() {
        println("Adding order with id: $id")
    }
}

class OrderPayCommand(val id: Long) : Command {
    override fun execute() {
        println("Paying order with id: $id")
    }
}

class CommandProcessor {
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command) : CommandProcessor =
        apply { queue.add(command) }

    fun processCommands() : CommandProcessor = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}

class CommandTest {
    @Test
    fun commandTest() {
        CommandProcessor()
            .addToQueue(OrderAddCommand(1))
            .addToQueue(OrderAddCommand(2))
            .addToQueue(OrderPayCommand(2))
            .addToQueue(OrderPayCommand(1))
            .processCommands()
    }
}
