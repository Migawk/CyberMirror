package com.cool.something.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cool.something.R
import com.cool.something.logic.Day
import com.cool.something.logic.getDay
import com.cool.something.logic.getWeather
import com.cool.something.logic.parseDate
import com.cool.something.network.ForecastDay
import com.cool.something.ui.theme.Txt

@Composable
fun WeatherTable(days: List<ForecastDay>) {
    Box(
        modifier = Modifier
            .border(3.dp, color = colorResource(R.color.blue))
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.yellow))
                    .padding(4.dp)
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
            WeatherRow(
                Day(
                    getDay(parseDate(days[1].date)).weekDay,
                    getDay(parseDate(days[1].date)).month,
                    getDay(parseDate(days[1].date)).monthDay,
                ),
                days[1].day.condition.text,
                days[1].day.maxtemp_c,
                days[1].day.avgtemp_c,
                days[1].day.totalprecip_mm,
                listOf(days[1].astro.sunrise, days[1].astro.sunset)
            )
            WeatherRow(
                Day(
                    getDay(parseDate(days[2].date)).weekDay,
                    getDay(parseDate(days[2].date)).month,
                    getDay(parseDate(days[2].date)).monthDay,
                ),
                days[2].day.condition.text,
                days[2].day.maxtemp_c,
                days[2].day.avgtemp_c,
                days[2].day.totalprecip_mm,
                listOf(days[2].astro.sunrise, days[2].astro.sunset)
            )
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
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
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