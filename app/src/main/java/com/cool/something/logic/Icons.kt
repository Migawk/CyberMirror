package com.cool.something.logic
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.cool.something.R

@Composable
fun getWeather(weatherIndicator: String): Painter {
    val weather = weatherIndicator.trim().lowercase()

    var res = when (weather) {
        "sunny" -> R.drawable.sun
        "mist" -> R.drawable.cloud
        "overcast" -> R.drawable.cloud
        else -> R.drawable.question
    }

    if(weather.contains("cloud")) res = R.drawable.cloud
    if(weather.contains("snow")) res = R.drawable.snow
    if(weather.contains("rain")) res = R.drawable.rain
    if(weather.contains("thunder")) res = R.drawable.thunder

    return painterResource(res)
}