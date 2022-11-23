package behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

sealed class AuthorizationState

object UnAuthorized : AuthorizationState()

class Authorized(val username: String) : AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = UnAuthorized
    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is UnAuthorized -> false
        }
    val username: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.username
                is UnAuthorized -> "unknown"
            }
        }

    fun loginUser(username: String) {
        state = Authorized(username)
    }

    fun logoutUser() {
        state = UnAuthorized
    }

    override fun toString() = "User $username is logged in: $isAuthorized"
}

class StateTest {
    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        assertThat(authorizationPresenter.username).isEqualTo("admin")
        authorizationPresenter.logoutUser()
        assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
        println(authorizationPresenter)
    }
}