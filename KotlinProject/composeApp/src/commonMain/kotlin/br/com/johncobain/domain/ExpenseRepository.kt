package br.com.johncobain.domain

import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory

interface ExpenseRepository {
  fun getAllExpenses(): List<Expense>
  fun addExpense(expense: Expense)
  fun editExpense(expense: Expense)
  fun getCategories(): List<ExpenseCategory>
}