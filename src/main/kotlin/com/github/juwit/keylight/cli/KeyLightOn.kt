package com.github.juwit.keylight.cli

import picocli.CommandLine
import sendRequest
import java.util.concurrent.Callable

@CommandLine.Command(name = "on")
class KeyLightOn : Callable<Int> {
    override fun call(): Int {
        val onBody = """
            {
              "lights": [
                {
                  "on": 1
                }
              ]
            }
        """.trimIndent()
        return sendRequest(onBody)
    }
}