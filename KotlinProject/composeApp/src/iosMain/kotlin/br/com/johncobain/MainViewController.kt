package br.com.johncobain

import androidx.compose.ui.window.ComposeUIViewController
import br.com.johncobain.data.DatabaseDriverFactory
import br.com.johncobain.di.appModule
import com.expenseApp.db.AppDatabase
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(){
    startKoin{
        modules(appModule(AppDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}