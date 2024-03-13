package com.example

import com.example.model.TodoItem
import com.example.model.repository.InMemoryTodoRepository
import com.example.model.repository.TodoRepository
import com.example.plugins.*
import com.example.route.todoRoute
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    todoRoute()
}
