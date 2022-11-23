package behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(
    val token: String?,
    var next: HandlerChain? = null
) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nAuthorization: $token"
            .let { next?.addHeader(it) ?: it }
}

class ContentTypeHeader(
    val contentType: String,
    var next: HandlerChain? = null
) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nContentType: $contentType"
            .let { next?.addHeader(it) ?: it }
}

class BodyPayloadHeader(
    val bodyPayload: String,
    val next: HandlerChain? = null
) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\n$bodyPayload"
            .let { next?.addHeader(it) ?: it }
}

class ChainOfResponsibilityTest {
    @Test
    fun chainOfResponsibilityTest() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader(
            "Body: {\"username\" = \"John\"}")
        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader
        val messageWithAuthentication = authenticationHeader.addHeader(
            "Headers with authentication")
        println(messageWithAuthentication)

        val messageWithoutAuthentication = contentTypeHeader.addHeader(
            "Headers without authentication")
        println(messageWithoutAuthentication)

        assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with authentication
                Authorization: 123456
                ContentType: json
                Body: {"username" = "John"}
            """.trimIndent()
        )
    }
}