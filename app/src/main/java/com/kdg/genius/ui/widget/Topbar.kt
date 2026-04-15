package com.kdg.genius.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kdg.genius.R

@Composable
fun Topbar(onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()) {
        TextButton(onClick = onClick) {
            Icon(
                painter = painterResource(R.drawable.rounded_arrow_back_ios_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}