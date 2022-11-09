package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class Shape : Cloneable {
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class Rectangle : Shape() {
    override fun draw() {
        println("Inside Rectangle::draw() function")
    }

    init {
        type = "Rectangle"
    }
}

class Square : Shape() {
    override fun draw() {
        println("Inside Square::draw() function")
    }

    init {
        type = "Square"
    }
}

object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        shapeMap["1"] = Rectangle()
        shapeMap["2"] = Square()
    }

    fun getShape(shapeId: String): Shape {
        return shapeMap[shapeId]?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testPrototype() {
        ShapeCache.loadCache()
        val clonedShape1 = ShapeCache.getShape("1")
        val clonedShape2 = ShapeCache.getShape("2")
        clonedShape1.draw()
        clonedShape2.draw()
        assertThat(clonedShape1.type).isEqualTo("Rectangle")
        assertThat(clonedShape2.type).isEqualTo("Square")
    }
}