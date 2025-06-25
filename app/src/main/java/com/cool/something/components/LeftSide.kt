package com.cool.something.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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

@Composable
fun LeftSide() {
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("yyyy, MMMM")

    val today = getDay(calendar)
    val formattedDate = formatter.format(calendar.time)

    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val tomorrow = getDay(calendar)

    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val dayAfterTomorrow = getDay(calendar)

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .border(width = 3.dp, color = Color(0xFFF8E602))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(4.dp)
        ) { // global
            Column { // current date and calendar
                Txt(
                    formattedDate,
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontSize = 20,
                    bold = true
                )
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
                AdvancedElement(
                    { Txt("€", fontSize = 20, bold = true, color = Color(0xFF2A252B),) },
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween) {
                        Txt("10,46 :-")
                        Txt("48,01 ₴")
                    } },
                    false
                )
                AdvancedElement(
                    { Txt("\$", fontSize = 20, bold = true, color = Color(0xFF2A252B),) },
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween) {
                        Txt("10,46 :-")
                        Txt("48,01 ₴")
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
                    { Row ( horizontalArrangement = Arrangement.SpaceBetween) {
                        Txt("03:36")
                        Txt("---")
                        Txt("22:10")
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
            .scale(0.8f)
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
            .scale(0.8f)
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