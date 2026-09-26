package com.s.looka.features.feature_auth.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun LinkText(
    unlinkedText: String,
    linkedText: LinkedText,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(unlinkedText)

            withLink(
                link = LinkAnnotation.Clickable(
                    tag = linkedText.label,
                    linkInteractionListener = { linkedText.onClick?.invoke() },
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = LookaTheme.colors.primary900,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                )
            ) {
                append(linkedText.label)
            }
        },
        style = LookaTheme.typography.b1Regular,
        color = LookaTheme.colors.primary500,
        modifier = modifier
    )
}