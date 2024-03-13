package com.example.model.repository

import com.example.model.TodoDraft
import com.example.model.TodoItem

interface TodoRepository {
    fun getTodos(): List<TodoItem>

    fun getTodoById(id: Int): TodoItem?

    fun addTodo(todoDraft: TodoDraft): TodoItem

    fun removeTodo(id: Int): Boolean

    fun updateTodo(id: Int, todoDraft: TodoDraft): Boolean
}