package behavioural

import org.junit.jupiter.api.Test

class ChatUser(
    private val mediator: Mediator,
    private val userName: String
) {
    fun send(msg: String) {
        println("$userName: Sending message: $msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$userName: Receiving message: $msg")
    }
}

class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {
        users.filter { it != user }
            .forEach { it.receive(msg) }
    }

    fun addUser(user: ChatUser): Mediator =
        apply { users.add(user) }
}

class MediatorTest {
    @Test
    fun testMediator() {
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Alice")
        val bob = ChatUser(mediator, "Bob")
        val carol = ChatUser(mediator, "Carol")

        mediator
            .addUser(alice)
            .addUser(bob)
            .addUser(carol)

        carol.send("Hi everyone")
    }
}