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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cool.something.R
import com.cool.something.logic.getWeather
import com.cool.something.ui.theme.Txt
import java.util.Calendar
import androidx.compose.runtime.*
import com.cool.something.network.CommonWeatherResponse

@Composable
fun RightSide(weather: CommonWeatherResponse) {

    Box (
        modifier = Modifier
            .border(width = 3.dp, color = Color(0xFF00F0FF))
            .padding(8.dp)
    ) {
            Column(
                verticalArrangement = Arrangement
                    .spacedBy(8.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
            ) { // global
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FaceWeather(weather!!)
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    WeatherTable(weather!!.forecast.forecastday)
                }
//                Box(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    NewsFeed()
//                }
                Box(
                    modifier = Modifier.weight(2f)
                ) {
                    InfoField()
                }
            }
    }
}

@Composable
fun FaceWeather(weather: CommonWeatherResponse) {
    val today = weather.current
    val days = weather.forecast.forecastday

    Row (
        modifier = Modifier
            .background(colorResource(R.color.blue_transparent))
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .background(colorResource(R.color.blue))
                .fillMaxHeight()
                .weight(0.35f),
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = getWeather(today.condition.text),
                    contentDescription = "",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Column {
                    val calendar = Calendar.getInstance()
                    val currentHour = calendar.get(Calendar.HOUR)
                    val amPm = if(calendar.get(Calendar.AM_PM) == 1) "PM" else "AM"
                    Txt(
                        "At $currentHour $amPm",
                        color = colorResource(id = R.color.bg)
                    )
                    Txt(
                        "${today.temp_c}°",
                        bold = true,
                        color = colorResource(id = R.color.bg)
                    )

                    Txt(
                        "Feels like ${today.feelslike_c}°",
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
                    days[0].hour[5].condition.text,
                    days[0].hour[5].temp_c,
                    days[0].hour[5].feelslike_c,
                    days[0].hour[5].precip_mm,
                    if(currentHour > 0 && currentHour <= 9) timing else null
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Mid",
                    days[0].hour[12].condition.text,
                    days[0].hour[12].temp_c,
                    days[0].hour[12].feelslike_c,
                    days[0].hour[12].precip_mm,
                    if(currentHour >= 10 && currentHour <= 13) timing else colorResource(R.color.blue)
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Evening",
                    days[0].hour[15].condition.text,
                    days[0].hour[15].temp_c,
                    days[0].hour[15].feelslike_c,
                    days[0].hour[15].precip_mm,
                    if(currentHour >= 14 && currentHour <= 19) timing else null
                )
            }
            Box (
                modifier = Modifier.weight(1f)
            ) {
                WeatherCell(
                    "Night",
                    days[0].hour[21].condition.text,
                    days[0].hour[21].temp_c,
                    days[0].hour[21].feelslike_c,
                    days[0].hour[21].precip_mm,
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