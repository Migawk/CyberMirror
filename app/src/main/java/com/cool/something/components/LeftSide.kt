package com.cool.something.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cool.something.logic.getDay
import com.cool.something.ui.theme.Txt
import java.text.SimpleDateFormat
import java.util.Calendar
import com.cool.something.R
import com.cool.something.network.Rates
import androidx.compose.runtime.*
import com.cool.something.logic.formatFloat
import com.cool.something.network.Exchanger
import com.cool.something.network.ForecastDay
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun LeftSide(day: ForecastDay) {
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("yyyy, MMMM")

    val today = getDay(calendar)
    val formattedDate = formatter.format(calendar.time)

    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val tomorrow = getDay(calendar)

    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val dayAfterTomorrow = getDay(calendar)

    var usd by remember { mutableStateOf<Rates?>(null) }
    var eur by remember { mutableStateOf<Rates?>(null) }

    var time by remember { mutableStateOf<Calendar?>(null) }

    LaunchedEffect(Unit) {
        val exchClient = Exchanger()

        while(true) {
            usd = exchClient.getValue("USD").conversion_rates
            eur = exchClient.getValue("EUR").conversion_rates

            val h: Long = 1000 * 60 * 60
            delay(h * 12) // half of the day.
        }
    }
    LaunchedEffect(Unit) {
        while(true){
            time = Calendar.getInstance()
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .border(width = 3.dp, color = Color(0xFFF8E602))
            .padding(4.dp)
            .fillMaxWidth(0.25f)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight(),
        ) { // global
            Column {
                Row ( // current date and time
                    modifier = Modifier
                        .background(Color(0xFF00F0FF))
                        .fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row (
                        modifier = Modifier
                            .background(Color(0xFFF8E602))
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Txt(
                            "${String.format("%02d", time?.get(Calendar.HOUR_OF_DAY))}:${String.format("%02d", time?.get(Calendar.MINUTE))}",
                            color = Color(0xFF2A252B),
                            fontSize = 36,
                            bold = true
                        )
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(Color(0xAA4C484D), shape = CircleShape)
                        )
                        Txt(
                            String.format("%02d", time?.get(Calendar.SECOND)),
                            color = Color(0xAA4C484D),
                            fontSize = 28,
                        )
                    }
                    Txt(
                        formattedDate,
                        color = Color(0xFF2A252B),
                        fontSize = 20,
                        bold = true,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column {
                    UnitElement(
                        today.monthDay.toString(),
                        today.weekDay,
                        "Just a common day",
                        true
                    )
                    UnitElement(
                        tomorrow.monthDay.toString(),
                        tomorrow.weekDay,
                        "Just a common day"
                    )
                    UnitElement(
                        dayAfterTomorrow.monthDay.toString(),
                        dayAfterTomorrow.weekDay,
                        "Just a common day"
                    )
                }
            }
            Column { // Currencies
                if(eur != null) AdvancedElement(
                    { Txt("€", fontSize = 20, bold = true, color = Color(0xFF2A252B)) },
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Txt("${formatFloat(eur!!.SEK)} :-")
                        Txt("${formatFloat(eur!!.UAH)} ₴")
                    } },
                    false
                )
                if(usd != null ) AdvancedElement(
                    { Txt("\$", fontSize = 20, bold = true, color = Color(0xFF2A252B)) },
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Txt("${formatFloat(usd!!.SEK)} :-")
                        Txt("${formatFloat(usd!!.UAH)} ₴")
                    } },
                    false
                )
                AdvancedElement(
                    {
                        Image(
                            painter = painterResource(R.drawable.sun),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)

                        )
                    },
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Txt(day.astro.sunrise)
                        Txt("---")
                        Txt(day.astro.sunset)
                    } }
                )
            }
        }
    }
}

@Composable
fun UnitElement(ico: String, title: String, description: String, isPrimary:Boolean = false) {
    var globalMod: Modifier = Modifier
    if(!isPrimary) {
        globalMod = Modifier
            .scale(0.9f)
            .alpha(0.4f)
    }
    Row (
        modifier = globalMod.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFF8E602))
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Txt(
                ico,
                color = Color(0xFF2A252B),
                fontSize = 24,
                bold = true
            )
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Txt(title, fontSize = 20, bold = true)
            Txt(description, fontSize = 12, bold = true)
        }
    }
}
@Composable
fun AdvancedElement(ico: @Composable () -> Unit, content: @Composable () -> Unit, isPrimary:Boolean = true) {
    var globalMod: Modifier = Modifier
    if(!isPrimary) {
        globalMod = Modifier
            .scale(0.9f)
            .alpha(0.4f)
    }

    Row (
        modifier = globalMod.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFF8E602))
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            ico()
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            content()
        }
    }
}