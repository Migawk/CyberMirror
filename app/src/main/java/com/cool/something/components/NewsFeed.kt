package com.cool.something.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cool.something.R
import com.cool.something.ui.theme.Txt

@Composable
fun NewsFeed() {
    Box(
        modifier = Modifier
            .border(3.dp, color = colorResource(R.color.blue))
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.yellow))
                    .padding(4.dp),
            ) {
                Txt(
                    "News",
                    color = colorResource(R.color.bg),
                    bold = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Row {
                Column (
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    NewsElement(
                        painterResource(R.drawable.cloud),
                        "Nyh",
                        "22/06/25"
                    )
                    NewsElement(
                        painterResource(R.drawable.cloud),
                        "Nyheter om ukrainare",
                        "22/06/25",
                        true
                    )
                }
            }
        }
    }
}

@Composable
fun NewsElement(
    logo: Painter,
    title: String,
    date: String,
    isBold: Boolean = false
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(colorResource(R.color.blue))
                    .padding(4.dp)
            ) {
                Image(
                    painter = logo,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            Txt(title, bold = isBold)
        }
        Txt(date, bold = isBold)
    }
}