package br.com.johncobain.di

import br.com.johncobain.data.ExpenseRepoImpl
import br.com.johncobain.domain.ExpenseRepository
import br.com.johncobain.presentation.ExpensesViewModel
import com.expenseApp.db.AppDatabase
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase) }
    factory { ExpensesViewModel(get()) }
}