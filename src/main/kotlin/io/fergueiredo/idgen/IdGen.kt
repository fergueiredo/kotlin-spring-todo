package io.fergueiredo.idgen

import org.slf4j.LoggerFactory
import java.util.Base64
import kotlin.random.Random

object IdGen {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun newId(): String {
        val bytes = Random.nextBytes(4)
        val b64 = Base64.getEncoder().encode(bytes)
        val strB64 = String(b64)

        val modB64 = strB64
            .replace("+", "-")
            .replace("/", "_")
            .replace("=", "")
            .trim()

        logger.trace("Random bytes -> ${String(bytes)}")
        logger.trace("Base64 -> $strB64")
        logger.info("IdGen::newID -> $modB64")

        return modB64
    }
}