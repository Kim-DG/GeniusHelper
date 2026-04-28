package com.kdg.genius.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kdg.genius.ui.theme.GeniusGold

@Composable
fun GeniusTextField(value: String, label: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange.invoke(newValue)
        },
        label = { Text(label) },
        modifier = Modifier.border(2.dp, GeniusGold, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}