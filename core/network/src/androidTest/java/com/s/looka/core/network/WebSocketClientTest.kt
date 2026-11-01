package com.s.looka.core.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.s.looka.core.network.api.WebSocketClient
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class WebSocketClientTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun initialization() { hiltRule.inject() }

    @Inject
    lateinit var webSocketClient: WebSocketClient

    @Test
    fun connectTest() = runBlocking {

        launch {
            webSocketClient.state.collect { state ->
                println("WebSocketState: $state")
            }
        }

        launch {
            webSocketClient.incomingMessage.collect { message ->
                println("WebSocketMessage: $message")
            }
        }

        webSocketClient.connect("wss://echo.websocket.org")
        webSocketClient.send("Lalala")
    }
}