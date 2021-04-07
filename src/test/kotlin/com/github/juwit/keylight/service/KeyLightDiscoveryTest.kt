package com.github.juwit.keylight.service

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KeyLightDiscoveryTest {

    @Test
    fun testServiceDiscovery() {
        val address = discovery().get()

        assertNotNull(address)
        assertEquals("http://192.168.1.28:9123", address)
    }

}