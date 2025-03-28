package com.example.ai_luna_compose.ui.screen.error

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.TitleBar
import com.example.ai_luna_compose.ui.common.TitleBarType
import com.example.ai_luna_compose.ui.screen.MainActivity
import com.example.ai_luna_compose.ui.theme.FONT_GOWUN_DODUM

class ErrorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(45.dp))


                        Text(
                            "예상치 못한 오류가 발생했습니다.",
                            fontFamily = FONT_GOWUN_DODUM,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "해당문제가 지속되는 경우 개발자에게 문의 해주세요.",
                            fontFamily = FONT_GOWUN_DODUM,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 17.dp, end = 17.dp, bottom = 15.dp)
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            val mainIntent = Intent(this@ErrorActivity, MainActivity::class.java)
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(mainIntent)
                            finish()
                        }
                        .padding(17.dp)
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "확인",
                        fontFamily = FONT_GOWUN_DODUM,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
            }
        }
    }


}