package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(
    val id: Int,
    var title: String,
    var done: Boolean
)
