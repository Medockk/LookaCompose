package com.s.looka.core.network

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.s.looka.core.network.api.ConnectivityManagerChecker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private val duration = 6.seconds

    @Before
    fun initialization() { hiltRule.inject() }

    @Inject
    lateinit var connectivityManagerChecker: ConnectivityManagerChecker

    private fun p(message: String) {
        println("--------------- NetworkTesting ---------------------\n$message")
    }

    @Test
    fun useAppContext() = runBlocking {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.s.looka.core.network.test", appContext.packageName)

        p("Lalala testing")

        val job = launch(Dispatchers.IO) {
            connectivityManagerChecker.observeConnectionState()
                .collect {
                    p("Connected state:$it")
                }
        }

        p("Set airplane mode enabled")
        NetworkTestUtils.setAirplaneModeEnabled(true)
        delay(duration)

        p("Set airplane mode disabled")
        NetworkTestUtils.setAirplaneModeEnabled(false)
        delay(duration)

        p("Disable Internet")
        NetworkTestUtils.setWifiEnabled(false)
        NetworkTestUtils.setMobileDataEnabled(false)
        delay(duration)

        p("Set wifi enabled")
        NetworkTestUtils.setWifiEnabled(true)
        delay(duration)

        p("Set wifi disabled and mobile data enable")
        NetworkTestUtils.setWifiEnabled(false)
        NetworkTestUtils.setMobileDataEnabled(true)
        delay(duration)

        job.cancel()
    }
}