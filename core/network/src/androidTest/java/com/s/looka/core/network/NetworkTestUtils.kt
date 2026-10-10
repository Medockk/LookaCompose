package com.s.looka.core.network

import android.os.ParcelFileDescriptor
import androidx.test.platform.app.InstrumentationRegistry

object NetworkTestUtils {

    /**
     * Переключает состояние Wi-Fi (true - включить, false - выключить)
     */
    fun setWifiEnabled(enabled: Boolean) {
        val state = if (enabled) "enable" else "disable"
        executeShellCommand("svc wifi $state")
    }

    /**
     * Переключает состояние Мобильного интернета (true - включить, false - выключить)
     */
    fun setMobileDataEnabled(enabled: Boolean) {
        val state = if (enabled) "enable" else "disable"
        executeShellCommand("svc data $state")
    }

    /**
     * Переключает режим "В самолете" (быстрый способ выключить вообще всю сеть)
     */
    fun setAirplaneModeEnabled(enabled: Boolean) {
        val state = if (enabled) "true" else "false"
        val code = if (enabled) "1" else "0"
        val modeState = if (enabled) "enabled" else "disable"

        // for android 10+
        executeShellCommand("cmd connectivity airplane-mode $modeState")

        // for oldest android version
        executeShellCommand("settings put global airplane_mode_on $code")
        executeShellCommand("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state $state")
    }

    private fun executeShellCommand(command: String) {
        val pfd = InstrumentationRegistry.getInstrumentation()
            .uiAutomation
            .executeShellCommand(command)

        // Считываем вывод до конца, чтобы команда гарантированно выполнилась в ОС
        ParcelFileDescriptor.AutoCloseInputStream(pfd).use { stream ->
            stream.readBytes()
        }
    }
}