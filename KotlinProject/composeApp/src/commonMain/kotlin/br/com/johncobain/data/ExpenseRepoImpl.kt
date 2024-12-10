package br.com.johncobain.data

import br.com.johncobain.domain.ExpenseRepository
import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory

class ExpenseRepoImpl: ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return ExpenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        ExpenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        ExpenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return ExpenseManager.getCategories()
    }

}