package io.fergueiredo.idgen

import java.util.Base64
import kotlin.random.Random

object IdGen {
    fun newId(): String {
        val bytes = Random.nextBytes(4)
        val b64 = Base64.getEncoder().encode(bytes)
        val strB64 = String(b64)

        val modB64 = strB64
            .replace("+", "-")
            .replace("/", "_")
            .replace("=", "")
            .trim()

        println("Random bytes -> ${String(bytes)}")
        println("Base64 -> $strB64")
        println("IdGen::newID -> $modB64")

        return modB64
    }
}