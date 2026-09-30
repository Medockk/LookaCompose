package com.s.looka.core.common.utils

import android.util.Patterns

class EmailValidator {

    companion object {
        fun isValidate(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}