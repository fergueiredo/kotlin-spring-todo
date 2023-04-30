package io.fergueiredo.idgen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IdGenTest {
    @Test
    fun generateRandomSixCharacterId() {
        val response = IdGen.newId();

        assertThat(response.length).isEqualTo(6)
    }
}