package structural

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

open class Equipment (
    open val price: Int,
    val nameEq: String
)

open class Composite(name: String) : Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.sumOf { it.price }

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class Computer : Composite("PC")
class Processor : Equipment(1000, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Composite("Memory")
class ROM : Equipment(100, "ROM")

class CompositeTest {
    @Test
    fun compositeTest() {
        val memory = Memory()
            .add(ROM())
        val pc = Computer()
            .add(memory)
            .add(Processor())
            .add(HardDrive())
        println("PC price: ${pc.price}")
        assertThat(pc.nameEq).isEqualTo("PC")
        assertThat(pc.price).isEqualTo(1350)
    }
}
