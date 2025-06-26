package com.cool.something.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.cool.something.R
import com.cool.something.ui.theme.Txt
import com.cool.something.*

@Composable
fun InfoField() {
    Box (
        Modifier
            .border(3.dp, color = colorResource(R.color.blue))
            .fillMaxSize()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.qr_code),
                    contentDescription = "",
                    modifier = Modifier.size(96.dp)
                )
                Column {
                    Row {
                        Txt("name:  ")
                        Txt(wifiName, bold = true)
                    }
                    Row {
                        Txt("password:  ")
                        Txt(wifiPassword, bold = true)
                    }
                    Row {
                        Txt("owner:  ")
                        Txt(wifiOwner, bold = true)
                    }
                }
            }
            Column (
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.End
            ) {
                Txt(
                    address,
                    align = TextAlign.Right
                )
                Txt(
                    postal,
                    align = TextAlign.Right
                )
            }
        }
    }
}