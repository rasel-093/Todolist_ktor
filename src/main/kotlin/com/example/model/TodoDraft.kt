package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoDraft(
    var title: String,
    var done: Boolean
)
