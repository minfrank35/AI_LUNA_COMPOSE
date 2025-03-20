package com.example.ai_luna_compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// FONT_FAUSTINA는 프로젝트 내에 미리 정의되어 있다고 가정합니다.
// 예: val FONT_FAUSTINA = FontFamily(Font(R.font.faustina_regular))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FONT_FAUSTINA,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FONT_FAUSTINA,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        color = Color.White
    ),
    headlineSmall = TextStyle(
        fontFamily = FONT_FAUSTINA,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = Color.White
    ),
    // 타이틀바에 사용할 텍스트 스타일
    titleLarge = TextStyle(
        fontFamily = FONT_FAUSTINA,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        color = Color.White
    ),
    labelMedium = TextStyle(
        fontFamily = FONT_FIGTREE,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        color = Color.White
    ),

)

val TypographyKorean = Typography(
    titleMedium = TextStyle(
        fontFamily = FONT_GOWUN_DODUM,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = TINT
    ),
    titleLarge = TextStyle(
        fontFamily = FONT_GOWUN_DODUM,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = Color.White
    ),
    titleSmall = TextStyle(
        fontFamily = FONT_GOWUN_DODUM,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        color = Color.White
    )
)
