package br.com.johncobain.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.johncobain.data.ExpenseManager
import br.com.johncobain.data.ExpenseRepoImpl
import br.com.johncobain.getColorsTheme
import br.com.johncobain.presentation.ExpensesViewModel
import br.com.johncobain.ui.ExpensesScreen
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class){
        ExpensesViewModel(ExpenseRepoImpl(ExpenseManager))
    }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ){
        scene(route = "/home"){
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState){ expense->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{Id}"){ backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("Id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithId(id) }

            // create ExpensesDetailScreen
        }
    }

}