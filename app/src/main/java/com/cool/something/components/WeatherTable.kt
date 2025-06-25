package com.cool.something.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cool.something.R
import com.cool.something.logic.Day
import com.cool.something.logic.getWeather
import com.cool.something.ui.theme.Txt

@Composable
fun WeatherTable() {
    Box(
        modifier = Modifier
            .border(3.dp, color = colorResource(R.color.blue))
            .padding(8.dp)
    ) {
        Row {
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.yellow))
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Txt(
                    "Day",
                    color = colorResource(R.color.bg),
                    bold = true,
                    modifier = Modifier.weight(1f)
                )
                Txt(
                    "Temperature",
                    color = colorResource(R.color.bg),
                    bold = true,
                    modifier = Modifier.weight(1f)
                )
                Txt(
                    "Precipiation",
                    color = colorResource(R.color.bg),
                    bold = true,
                    modifier = Modifier.weight(1f)
                )
                Txt(
                    "Suntimes",
                    color = colorResource(R.color.bg),
                    bold = true,
                    modifier = Modifier.weight(1f)
                )

            }
            Row(

            ) {
                WeatherRow(
                    Day(
                        weekDay = "Wednesday",
                        monthDay = 25,
                        month = "June"
                    ),
                    "rain",
                    18f,
                    16f,
                    20f,
                    listOf("03:44", "22:33")
                )
            }
        }
    }
}

@Composable
fun WeatherRow(
    day: Day,
    weather: String,
    temp: Float,
    feels: Float,
    precipiation: Float,
    suntimes: List<String>
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Txt(day.weekDay, bold = true)
                Txt("${day.month}, ${day.monthDay}")
            }
            Box (
                modifier = Modifier
                    .padding(4.dp)
                    .background(colorResource(R.color.blue))
            ) {
                Image(
                    painter = getWeather(weather),
                    contentDescription = "",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Txt("$temp° / $feels°", modifier = Modifier.weight(1f))
        Txt("$precipiation mm", modifier = Modifier.weight(1f))
        Txt("${suntimes[0]} --- ${suntimes[1]}", modifier = Modifier.weight(1f))
    }
}