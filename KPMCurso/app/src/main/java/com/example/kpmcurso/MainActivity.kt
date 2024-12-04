package com.example.kpmcurso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kpmcurso.ui.theme.KPMCursoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KPMCursoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.Gray),
                            text = "Hello!",
                            fontSize = 30.sp
                        )
                        CustomText(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.Gray),
                            text = "My Name Is",
                            fontSize = 30.sp
                        )
                        CustomText(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.Gray),
                            text = "John!",
                            fontSize = 30.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomText(modifier: Modifier, text: String, fontSize: TextUnit) {
    Text(
        modifier = modifier,
        text = text,
        color = Color.Green,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun CustomPreview() {
    KPMCursoTheme {
        CustomText(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.Gray),
            text = "Hello World!",
            fontSize = 30.sp
        )
    }
}