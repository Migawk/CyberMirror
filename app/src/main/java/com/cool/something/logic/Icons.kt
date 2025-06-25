package com.cool.something.logic
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.cool.something.R

@Composable
fun getWeather(weather: String): Painter {
    val res = when (weather) {
        "sun" -> R.drawable.sun
        "moon" -> R.drawable.moon
        "cloud" -> R.drawable.cloud
        "thunder" -> R.drawable.thunder
        "snow" -> R.drawable.snow
        else -> R.drawable.question
    }

    return painterResource(res)
}