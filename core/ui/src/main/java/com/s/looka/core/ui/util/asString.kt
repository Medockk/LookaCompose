package com.s.looka.core.ui.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.s.looka.core.common.AppError

fun AppError.asString(context: Context): String {
    return when (this) {
        is AppError.Http -> ""
        is AppError.Local<*> -> {
            when (val type = this.type) {
                is String -> type
                is Int -> {
                    try {
                        context.getString(type)
                    } catch (e: Exception) {
                        "$type"
                    }
                }
                else -> ""
            }
        }
        else -> ""
    }
}
@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun AppError.asString(): String {
    val context = LocalContext.current
    return this.asString(context)
}