package br.com.johncobain.domain

import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory

interface ExpenseRepository {
  suspend fun getAllExpenses(): List<Expense>
  suspend fun addExpense(expense: Expense)
  suspend fun editExpense(expense: Expense)
  fun getCategories(): List<ExpenseCategory>
  suspend fun deleteExpense(id: Long)
}