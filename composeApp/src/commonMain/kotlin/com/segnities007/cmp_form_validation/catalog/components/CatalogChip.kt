package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
internal fun CatalogChip(
    label: String,
    containerColor: Color,
    contentColor: Color,
    textStyle: TextStyle,
) {
    Surface(
        modifier = Modifier.wrapContentWidth(),
        shape = MaterialTheme.shapes.small,
        color = containerColor,
    ) {
        Text(
            text = label,
            modifier =
                Modifier.padding(
                    horizontal = CatalogTokens.ChipHorizontalPadding,
                    vertical = CatalogTokens.ChipVerticalPadding,
                ),
            style = textStyle,
            color = contentColor,
        )
    }
}
