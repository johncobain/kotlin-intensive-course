package br.com.johncobain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.johncobain.ui.ExpensesScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.PreComposeApp

@Composable
@Preview
fun App() {
    PreComposeApp{

        val colors = getColorsTheme()

        AppTheme {
            ExpensesScreen(onExpenseClick = {})
        }
    }
}
