package io.fergueiredo.todo

data class ToDoResponse (
    val id: String,
    val name: String,
    val description: String,
    val status: String
)
