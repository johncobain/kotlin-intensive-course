import br.com.johncobain.data.ExpenseManager
import br.com.johncobain.data.ExpenseRepoImpl
import br.com.johncobain.model.Expense
import br.com.johncobain.model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {

    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepoImpl(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        // Given
        val expenseList = mutableListOf<Expense>()

        // When
        expenseList.addAll(repo.getAllExpenses())

        // Then
        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense(){
        // Given
        val expenseList = repo.getAllExpenses()

        // When
        repo.addExpense(Expense(amount = 4.5, category = ExpenseCategory.CAR, description = "Gasoline"))

        // Then
        assertContains(expenseList, expenseList.find { it.id == 7L })
    }

    @Test
    fun edit_expense(){
        // Given
        val expenseListBefore = repo.getAllExpenses()

        // When
        val newExpenseId = 7L
        repo.addExpense(Expense(amount = 4.5, category = ExpenseCategory.CAR, description = "Gasoline"))

        assertNotNull(expenseListBefore.find { it.id == newExpenseId })

        val updatedExpense = Expense(id = newExpenseId, amount = 80.0, category = ExpenseCategory.OTHER, description = "Clothes")
        repo.editExpense(updatedExpense)

        // Then
        val expenseListAfter = repo.getAllExpenses()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_categories(){
        // Given
        val categoryList = mutableListOf<ExpenseCategory>()

        // When
        categoryList.addAll(repo.getCategories())

        // Then
        assertTrue(categoryList.isNotEmpty())

    }

    @Test
    fun check_all_categories() {
        // Given
        val allCategories = listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
        )

        val repoCategories = repo.getCategories()

        // Then
        assertEquals(allCategories, repoCategories)
    }
}
