package io.fergueiredo.todo

data class UpdateToDoRequest (
    val name: String,
    val description: String,
    val status: String
)
