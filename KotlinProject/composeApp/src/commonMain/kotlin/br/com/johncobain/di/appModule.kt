package br.com.johncobain.di

import br.com.johncobain.data.ExpenseRepoImpl
import br.com.johncobain.domain.ExpenseRepository
import br.com.johncobain.presentation.ExpensesViewModel
import com.expenseApp.db.AppDatabase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase, get()) }
    factory { ExpensesViewModel(get()) }
}