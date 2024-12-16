package br.com.johncobain

import androidx.compose.ui.window.ComposeUIViewController
import br.com.johncobain.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(){
    startKoin{
        modules(appModule())
    }.koin
}