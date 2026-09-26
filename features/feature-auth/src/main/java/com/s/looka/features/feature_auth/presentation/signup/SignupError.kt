package com.s.looka.features.feature_auth.presentation.signup

import com.s.looka.core.common.AppError
import com.s.looka.features.feature_auth.R

internal data object SignupError {
    data object EmptyFullName: AppError.Local<Int> {
        override val type: Int
            get() = R.string.error_empty_full_name
    }
    data object EmptyEmail: AppError.Local<Int> {
        override val type: Int
            get() = R.string.error_empty_email
    }
    data object EmptyPassword: AppError.Local<Int> {
        override val type: Int
            get() = R.string.error_empty_password
    }
    data object InvalidEmail: AppError.Local<Int> {
        override val type: Int
            get() = R.string.error_invalid_email
    }
}