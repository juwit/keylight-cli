package com.github.juwit.keylight.cli

import picocli.CommandLine
import sendRequest
import java.util.concurrent.Callable

@CommandLine.Command(name = "off")
class KeyLightOff : Callable<Int> {
    override fun call(): Int {
        val offBody = """
            {
              "lights": [
                {
                  "on": 0
                }
              ]
            }
        """.trimIndent()
        return sendRequest(offBody)
    }
}