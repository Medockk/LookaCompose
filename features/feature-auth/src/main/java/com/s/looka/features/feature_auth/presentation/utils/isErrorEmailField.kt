package com.s.looka.features.feature_auth.presentation.utils

internal fun isErrorEmailField(
    email: String,
    isEmailValid: Boolean?,
    isFieldInFocus: Boolean
): Boolean? {
    return (isEmailValid == false).let { isError ->
        if (isFieldInFocus) {
            if (email.isNotBlank()) isError
            else null
        }
        else {
            if (email.isBlank()) null
            else isError
        }
    }
}