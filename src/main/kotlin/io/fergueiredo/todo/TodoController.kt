package io.fergueiredo.todo

import io.fergueiredo.idgen.IdGen
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class TodoController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/todo")
    fun createToDo(@RequestBody newToDo: ToDoRequest): ResponseEntity<ToDoResponse> {
        logger.info("## Creating new To Do ${newToDo}")
        val response = ToDoResponse(
            id = IdGen.newId(),
            name = newToDo.name,
            description = newToDo.description,
            status = "TODO"
        )

        logger.info("Saving $response")
        FakeDB.add(response)

        logger.info("Returning stored To Do: '${response.id}'")
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id)
            .toUri()

        return ResponseEntity.created(uri).body(response)
    }

    @GetMapping("/todo")
    fun retrieveToDos(): ResponseEntity<List<ToDoResponse>> {
        logger.info("## Listing To Dos.")
        val toDos = FakeDB.getAll()

        logger.info("Retrieving ${toDos.size} to dos.")
        return ResponseEntity.ok(toDos)
    }

    @GetMapping("/todo/{id}")
    fun retrieveToDo(@PathVariable id: String): ResponseEntity<ToDoResponse> {
        logger.info("## Retrieving To Do: '$id'")
        val toDo = FakeDB.get(id)

        if (toDo == null) {
            logger.warn("ToDo '$id' was not found.")
            return ResponseEntity.notFound().build()
        }

        logger.info("Retrieving ${toDo}.")
        return ResponseEntity.ok(toDo)
    }

    @DeleteMapping("/todo/{id}")
    fun deleteToDo(@PathVariable id: String): ResponseEntity<ToDoResponse> {
        logger.info("## Removing To Do: '$id'")
        val toDo = FakeDB.remove(id)

        if (toDo == null) {
            logger.warn("ToDo '$id' was not found.")
            return ResponseEntity.notFound().build()
        }

        logger.info("Removed To Do: '${toDo}'.")
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/todo/{id}")
    fun updateToDo(@PathVariable id: String, @RequestBody updatedValue: UpdateToDoRequest): ResponseEntity<ToDoResponse> {
        logger.info("## Updating To Do: '$id'")
        val oldToDo = FakeDB.remove(id)

        if (oldToDo == null) {
            logger.warn("ToDo '$id' was not found.")
            return ResponseEntity.notFound().build()
        }

        logger.info("Former To Do: '${oldToDo}'.")

        val newToDo = ToDoResponse(
            id = id,
            name = updatedValue.name,
            description = updatedValue.description,
            status = updatedValue.status
        )

        FakeDB.add(newToDo)

        logger.info("Updated To Do: '${newToDo}'.")
        return ResponseEntity.ok(newToDo)
    }
}