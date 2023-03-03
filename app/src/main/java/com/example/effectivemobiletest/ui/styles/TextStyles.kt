package com.example.effectivemobiletest.ui.styles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.effectivemobiletest.R

fun StyleTextDefault(
    fontSize: TextUnit = 16.sp,
    align: TextAlign = TextAlign.Start,
    color: Color = Color(0, 0, 0),
    fontWeight: FontWeight = FontWeight.Normal
) = TextStyle(
    fontWeight = fontWeight,
    fontSize = fontSize,
    color = color,
    textAlign = align,
    fontFamily = FontFamily(Font(R.font.montserratmedium))
)
