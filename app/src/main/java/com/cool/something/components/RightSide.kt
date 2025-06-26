package com.cool.something.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cool.something.R
import com.cool.something.logic.getWeather
import com.cool.something.ui.theme.Txt
import java.util.Calendar

@Composable
fun RightSide() {
    Box (
        modifier = Modifier
            .border(width = 3.dp, color = Color(0xFF00F0FF))
            .padding(8.dp)
    ) {
        Column (
            verticalArrangement = Arrangement
                .spacedBy(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
        ) { // global   
            Box(
                modifier = Modifier.weight(1f)
            ) {
                FaceWeather()
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                WeatherTable()
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                NewsFeed()
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                InfoField()
            }
        }
    }
}

@Composable
fun FaceWeather() {
    Row (
        modifier = Modifier
            .background(colorResource(R.color.blue_transparent))
            .fillMaxSize()
    ){
        Box (
            modifier = Modifier
                .background(colorResource(R.color.blue))
                .fillMaxHeight()
                .weight(0.35f),
        ){
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = getWeather("sun"),
                    contentDescription = "",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Column {
                    Txt(
                        "At 12 PM",
                        color = colorResource(id = R.color.bg)
                    )
                    Txt(
                        "23°",
                        bold = true,
                        color = colorResource(id = R.color.bg)
                    )

                    Txt(
                        "Feels like 23°",
                        color = colorResource(id = R.color.bg)
                    )
                }
            }
        }
        Row (
            modifier = Modifier.weight(0.65f)
        ) {
            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val timing = colorResource(R.color.yellow)

            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Morning",
                    "cloud",
                    23.7f,
                    24.7f,
                    24f,
                    if(currentHour > 0 && currentHour <= 9) timing else null
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Mid",
                    "sun",
                    23.7f,
                    24.7f,
                    24f,
                    if(currentHour >= 10 && currentHour <= 13) timing else colorResource(R.color.blue)
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Evening",
                    "thunder",
                    23.7f,
                    24.7f,
                    24f,
                    if(currentHour >= 14 && currentHour <= 19) timing else null
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Night",
                    "moon",
                    23.7f,
                    24.7f,
                    24f,
                    if(currentHour >= 20 && currentHour <= 24) timing else colorResource(R.color.blue)
                )
            }
        }
    }
}

@Composable
fun WeatherCell(
    time: String,
    ico: String,
    temp: Float,
    feels: Float,
    precipiation: Float,
    bg: Color?
) {
    val displayBg = bg ?: Color(0x00000000)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(displayBg)
            .fillMaxSize()
            .padding(vertical = 12.dp),
    ) {
        Txt(
            time,
            bold = true,
            color = colorResource(R.color.bg)
            )
        Image(
            painter = getWeather(ico),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        Column {
            Txt("${temp.toString()}°", bold = true, color = colorResource(R.color.bg))
            Txt("${feels.toString()}°", color = colorResource(R.color.bg))
        }
        Txt("${precipiation.toString()}\nmm", color = colorResource(R.color.bg))
    }
}