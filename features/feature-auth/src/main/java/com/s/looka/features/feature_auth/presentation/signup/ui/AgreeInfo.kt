package com.s.looka.features.feature_auth.presentation.signup.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_auth.R
import com.s.looka.features.feature_auth.presentation.components.LinkedText

@Composable
fun AgreeInfo(
    agreementText: List<LinkedText>,
    modifier: Modifier = Modifier,
) {
    val linkStyle = LookaTheme.typography.b2Semibold.toSpanStyle().copy(
        textDecoration = TextDecoration.Underline,
        color = LookaTheme.colors.primary900
    )

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(stringResource(R.string.agreement))
            append(" ")

            agreementText.forEachIndexed { index, agree ->
                if (index > 0) {
                    if (index == agreementText.lastIndex) {
                        append(" and ")
                    } else {
                        append(", ")
                    }
                }

                withLink(
                    link = LinkAnnotation.Clickable(
                        tag = agree.label,
                        styles = TextLinkStyles(style = linkStyle),
                        linkInteractionListener = {
                            agree.onClick?.invoke()
                        }
                    )
                ) {
                    append(agree.label)
                }
            }
        },
        style = LookaTheme.typography.b2Regular,
        color = LookaTheme.colors.primary600
    )
}