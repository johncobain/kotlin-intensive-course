package br.com.johncobain.di

import br.com.johncobain.data.ExpenseManager
import br.com.johncobain.data.ExpenseRepoImpl
import br.com.johncobain.domain.ExpenseRepository
import br.com.johncobain.presentation.ExpensesViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

fun appModule() = module {
    single { ExpenseManager }.withOptions { createdAtStart() }
    single<ExpenseRepository> { ExpenseRepoImpl(get()) }
    factory { ExpensesViewModel(get()) }
}