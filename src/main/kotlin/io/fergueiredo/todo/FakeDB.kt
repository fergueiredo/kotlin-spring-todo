package io.fergueiredo.todo

import org.slf4j.LoggerFactory
import java.util.stream.Collectors

object FakeDB {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val toDos: MutableMap<String, ToDoResponse> = HashMap<String, ToDoResponse>()

    fun add(newToDo: ToDoResponse) {
        logger.info("Storing To Do: '${newToDo.id}'")
        toDos.put(newToDo.id, newToDo)
    }

    fun getAll() : List<ToDoResponse> {
        logger.info("Retrieving all To Dos.")
        return toDos.values.stream().collect(Collectors.toList())
    }

    fun get(id: String) : ToDoResponse? {
        logger.info("Retrieving To Do: '$id'.")
        return toDos[id]
    }

    fun remove(id: String) : ToDoResponse? {
        logger.info("Removing To Do: '$id'.")
        return toDos.remove(id)
    }
}