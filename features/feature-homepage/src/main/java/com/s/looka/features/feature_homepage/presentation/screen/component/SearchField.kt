package com.s.looka.features.feature_homepage.presentation.screen.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import com.s.looka.core.ui.components.textfield.DefaultTextField
import com.s.looka.core.ui.components.textfield.DefaultTextFieldIcon
import com.s.looka.core.ui.components.textfield.DefaultTextFieldKeyboardOptions
import com.s.looka.core.ui.components.textfield.DefaultTextFieldState
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun SearchField(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
) {
    DefaultTextField(
        state = DefaultTextFieldState(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = "Search for clothes..."
        ),
        keyboardOptions = DefaultTextFieldKeyboardOptions(
            imeType = ImeAction.Search,
            onImeClick = onSearch
        ),
        icon = DefaultTextFieldIcon(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(com.s.looka.core.ui.R.drawable.ic_navigation_search_icon),
                    contentDescription = null,
                    tint = LookaTheme.colors.primary300
                )
            }
        ),
        modifier = modifier
    )
}