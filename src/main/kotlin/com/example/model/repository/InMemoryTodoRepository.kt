package com.example.model.repository

import com.example.model.TodoDraft
import com.example.model.TodoItem

class InMemoryTodoRepository: TodoRepository {
    private val todoList = mutableListOf<TodoItem>(
//        TodoItem(1, "Do task 1", true),
//        TodoItem(2, "Do task 2", false),
//        TodoItem(3, "Do task 3", false),
//        TodoItem(4, "Do task 4", true),
//        TodoItem(5, "Do task 5", true)
    )
    override fun getTodos(): List<TodoItem> {
        return todoList
    }

    override fun getTodoById(id: Int): TodoItem? {
        return todoList.firstOrNull{it.id == id}
    }

    override fun addTodo(todoDraft: TodoDraft): TodoItem {
        val todo = TodoItem(
            id = todoList.size + 1,
            title = todoDraft.title,
            done = todoDraft.done
        )
        todoList.add(todo)
        return todo
    }

    override fun removeTodo(id: Int): Boolean {
        return todoList.removeIf { it.id == id }
    }

    override fun updateTodo(id: Int, todoDraft: TodoDraft): Boolean {
        val todo = todoList.firstOrNull{it.id == id}
            ?: return false
        todo.title = todoDraft.title
        todo.done = todoDraft.done
        return true
    }
}