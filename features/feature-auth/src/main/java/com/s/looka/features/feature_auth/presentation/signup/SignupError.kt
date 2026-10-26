package com.s.looka.features.feature_auth.presentation.signup

import androidx.annotation.StringRes
import com.s.looka.core.common.RootError
import com.s.looka.features.feature_auth.R

internal sealed interface SignupError: RootError {

    @get:StringRes
    val type: Int

    data object EmptyFullName: SignupError {
        override val type: Int
            get() = R.string.error_empty_full_name
    }
    data object EmptyEmail: SignupError {
        override val type: Int
            get() = R.string.error_empty_email
    }
    data object EmptyPassword: SignupError {
        override val type: Int
            get() = R.string.error_empty_password
    }
    data object InvalidEmail: SignupError {
        override val type: Int
            get() = R.string.error_invalid_email
    }
}