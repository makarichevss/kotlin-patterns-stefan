package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

object Singleton {
    init {
        println("Initialize: $this")
    }

    fun log(): Singleton =
        apply { println("Network driver: $this") }

}

class SingletonTest {

    @Test
    fun testSingleton() {
        println("Start:")
        val networkDriver1 = Singleton.log()
        val networkDriver2 = Singleton.log()
        assertThat(networkDriver1).isEqualTo(networkDriver2)
    }
}