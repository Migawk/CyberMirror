package com.cool.something.logic

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun formatFloat(float: Float): String {
    return String.format("%.2f", float)
}