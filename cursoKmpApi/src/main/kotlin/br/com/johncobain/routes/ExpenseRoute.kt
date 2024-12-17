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
        if(expenses.isEmpty()){
            call.respondText { "No expenses found" }
        }else{
            call.respond(status = HttpStatusCode.OK, expenses)
        }
    }

    get("/expenses/{id}"){
        val id = call.parameters["id"]?.toLongOrNull()
        if(id == null || id !in 0 until expenses.size){
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@get
        }
        call.respond(HttpStatusCode.OK, expenses[id.toInt()])
    }

    post("/expenses"){
        val expense = call.receive<Expense>()
        expenses.add(expense)
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
}