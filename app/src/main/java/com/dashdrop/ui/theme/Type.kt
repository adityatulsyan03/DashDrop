package com.dashdrop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dashdrop.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val rubikFontFamily = FontFamily(
    Font(R.font.rubik_black,FontWeight.Black),
    Font(R.font.rubik_bold,FontWeight.Bold),
    Font(R.font.rubik_extrabold,FontWeight.ExtraBold),
    Font(R.font.rubik_italic,FontWeight.Normal,FontStyle.Italic),
    Font(R.font.rubik_light,FontWeight.Light),
    Font(R.font.rubik_medium,FontWeight.Medium),
    Font(R.font.rubik_regular,FontWeight.Normal),
    Font(R.font.rubik_semibold,FontWeight.SemiBold)
)
val rubikBlackStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Black
)
val rubikBoldStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Bold
)
val rubikExtraBoldStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.ExtraBold
)
val rubikItalicStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontStyle = FontStyle.Italic
)
val rubikLightStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Light
)
val rubikMediumStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Medium
)
val rubikRegularStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal
)
val rubikSemiBoldStyle = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.SemiBold
)