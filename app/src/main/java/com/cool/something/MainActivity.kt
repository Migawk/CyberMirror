
package com.cool.something

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.cool.something.components.LeftSide
import com.cool.something.components.RightSide
import com.cool.something.network.CommonWeatherResponse
import com.cool.something.network.Weather
import com.cool.something.ui.theme.Txt
import kotlinx.coroutines.delay
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            var weather by remember { mutableStateOf<CommonWeatherResponse?>(null) }
            var error by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                val wethClient = Weather()
                while(true) {
                    try {
                        weather = wethClient.getWeather(cityName)
                    } catch (e: Exception) {
                        error = e.toString()
                    }

                    val h: Long = 1000 * 60 * 60
                    delay(h)
                }
            }
            if(error != null) Txt(error!!)
            Row (
                modifier = Modifier
                    .background(Color(0xFF2A252B))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if(weather != null) {
                    LeftSide(weather!!.forecast.forecastday[0])
                    RightSide(weather!!)
                }
            }
        }
    }
}