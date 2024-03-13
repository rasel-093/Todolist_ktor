package com.example.route

import com.example.model.TodoDraft
import com.example.model.repository.InMemoryTodoRepository
import com.example.model.repository.TodoRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.todoRoute(){
    routing {
        val repository: TodoRepository = InMemoryTodoRepository()
        get("/"){
            call.respondText("Hello todo list!")
        }

        get("/todos"){
            call.respond(repository.getTodos())
        }

        get("/todos/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null){
                call.respond(HttpStatusCode.BadRequest, "id parameter has to be a number")
                return@get
            }else{
                val todo = repository.getTodoById(id)
                if (todo == null){
                    call.respond(HttpStatusCode.NotFound, "No todo found with id: $id")
                }else{
                    call.respond(todo)
                }
            }
        }

        post("/todos"){
            val todoDraft = call.receive<TodoDraft>()
            val todo = repository.addTodo(todoDraft)
            call.respond(todo)
        }
        put("/todos/{id}"){
            val todoDraft = call.receive<TodoDraft>()
            val id = call.parameters["id"]?.toIntOrNull()
            if(id == null){
                call.respond(HttpStatusCode.BadRequest, "id must be integer")
                return@put
            }
            val updated = repository.updateTodo(id, todoDraft = todoDraft)
            if (updated){
                call.respond(
                    HttpStatusCode.OK
                )
            }else{
                call.respond(
                    HttpStatusCode.NotFound,
                    "found no todo")
            }
        }
        delete("/todos/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if(id == null){
                call.respond(HttpStatusCode.BadRequest, "id must be integer")
                return@delete
            }
            val deleted = repository.removeTodo(id)
            if (deleted){
                call.respond(
                    HttpStatusCode.OK
                )
            }else{
                call.respond(
                    HttpStatusCode.NotFound,
                    "found no todo")
            }
        }
    }
}