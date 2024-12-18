package br.com.johncobain.routes

import br.com.johncobain.data.model.Expense
import br.com.johncobain.data.model.MessageResponse
import br.com.johncobain.data.model.expenses
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.expensesRouting(){
    get("/expenses"){
        call.respond(status = HttpStatusCode.OK, expenses)
    }

    get("/expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find { it.id == id }
        if(id == null || expense == null){
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@get
        }
        call.respond(HttpStatusCode.OK, expense)
    }

    post("/expenses"){
        val expense = call.receive<Expense>()
        val lastExpenseId = if(expenses.isEmpty()){
            expense.id
        }else{
            expenses.maxOf { it.id } + 1
        }
        expenses.add(expense.copy(id = lastExpenseId))
        call.respond(HttpStatusCode.OK, MessageResponse("Expense added successfully"))
    }

    put("expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = call.receive<Expense>()
        if(id == null || id !in 0 until expenses.size){
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@put
        }
        val index = expenses.indexOfFirst { it.id == id }
        expenses[index] = expense.copy(id = id)
        call.respond(HttpStatusCode.OK, MessageResponse("Expense updated successfully"))
    }

    delete("expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = expenses.find { it.id == id }
        if(id == null || expense == null){
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@delete
        }
        expenses.removeIf { it.id == id }
        call.respond(HttpStatusCode.OK, MessageResponse("Expense removed successfully"))
    }
}