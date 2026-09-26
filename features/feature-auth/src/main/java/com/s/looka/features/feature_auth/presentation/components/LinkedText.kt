package com.s.looka.features.feature_auth.presentation.components

data class LinkedText(
    val label: String,
    val onClick: (() -> Unit)? = null,
)