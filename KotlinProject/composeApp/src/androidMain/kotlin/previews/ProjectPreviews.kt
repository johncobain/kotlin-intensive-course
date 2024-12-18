package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.johncobain.data.ExpenseManager
import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory
import br.com.johncobain.presentation.ExpensesUiState
import br.com.johncobain.ui.AllExpensesHeader
import br.com.johncobain.ui.ExpensesItem
import br.com.johncobain.ui.ExpensesScreen
import br.com.johncobain.ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpensesTotalHeader(total = 1028.80)
    }
}

@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseItemPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ExpensesItem(expense = ExpenseManager.fakeExpenseList[0], onExpenseClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState.Success(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1052.2
        ), onExpenseClick = {}, onDeleteExpense = {})
}