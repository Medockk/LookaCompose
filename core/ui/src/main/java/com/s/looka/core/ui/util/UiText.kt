package com.s.looka.core.ui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {

    data class DynamicString(val value: String): UiText
    data class Resource(
        @field:androidx.annotation.StringRes
        val id: Int,
        val args: List<Any> = emptyList()
    ): UiText {
        constructor(@StringRes id: Int, vararg args: Any): this(id, args.toList())
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> this.value
            is Resource -> context.getString(id, args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is Resource -> stringResource(id, args)
        }
    }
}