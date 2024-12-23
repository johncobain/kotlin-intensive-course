package br.com.johncobain.presentation

import br.com.johncobain.domain.ExpenseRepository
import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

sealed class ExpensesUiState {
    object Loading: ExpensesUiState()
    data class  Success(val expenses: List<Expense>, val total: Double): ExpensesUiState()
    data class Error(val message: String): ExpensesUiState()
}

class ExpensesViewModel(private val repo: ExpenseRepository): ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getExpenseList()
    }

    private fun getExpenseList(){
        viewModelScope.launch {
            try {
                delay(1000)
                val expenses = repo.getAllExpenses()
                _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })
            } catch (e: Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun updateExpenseList(){
        try {
            val expenses = repo.getAllExpenses()
            _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })
        } catch (e: Exception){
            _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error")
        }
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch {
            try {
                repo.addExpense(expense)
                updateExpenseList()
            } catch (e: Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun editExpense(expense: Expense){
        viewModelScope.launch {
            try {
                repo.editExpense(expense)
                updateExpenseList()
            } catch (e: Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteExpense(id: Long){
        viewModelScope.launch {
            try {
                repo.deleteExpense(id)
                updateExpenseList()
            } catch (e: Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getExpenseWithId(id: Long): Expense? {
        return (_uiState.value as? ExpensesUiState.Success)?.expenses?.firstOrNull { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repo.getCategories()
    }
}