package com.cool.something.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun Txt(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    bold: Boolean = false,
    color: Color = Color(0xFFFFFFFF)
) {
    Text(
        text,
        color = color,
        modifier = modifier,
        fontSize = fontSize.sp,
        fontWeight = if(bold) FontWeight.Bold else FontWeight.Normal
    )
}