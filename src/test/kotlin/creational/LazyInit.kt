package creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    val box by lazy { AlertBox() }

    fun showMessage(messase: String) {
        box.message = messase
        box.show()
    }
}

class Window2 {
    lateinit var box2: AlertBox

    fun showMessage(messase: String) {
        box2 = AlertBox()
        box2.message = messase
        box2.show()
    }
}

class LazyInitTest {

    @Test
    fun testLazyInit() {
        val window = Window()
        window.showMessage("Hello window")
        assertThat(window.box).isNotNull
        val window2 = Window2()
//        println(window2.box2)
        window2.showMessage("Hello window")
        assertThat(window2.box2).isNotNull
    }

}

