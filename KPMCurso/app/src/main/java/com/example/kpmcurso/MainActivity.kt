package com.example.kpmcurso

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

            var isTextClicked by remember { mutableStateOf(false) }
            Log.d("Click", "$isTextClicked")
            KPMCursoTheme {
                Card(
                    modifier = Modifier.padding(top = 50.dp).clickable {
                        isTextClicked = !isTextClicked
                        Log.d("Click", "$isTextClicked")
                    },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(15.dp),
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.padding(10.dp),
                            painter = if(isTextClicked) painterResource(id = R.drawable.baseline_person_24) else painterResource(id = R.drawable.baseline_mouse_24),
                            contentDescription = "Person Icon",
                        )
                        CustomText(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.Gray).padding(10.dp),
                            text = if(isTextClicked) "My name is!" else "Slim Shady!",
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
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Person Icon",
            )
            CustomText(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Gray),
                text = "Hello!",
                fontSize = 30.sp
            )
        }
    }
}