package com.github.juwit.keylight.cli

import picocli.CommandLine
import sendRequest
import java.util.concurrent.Callable

@CommandLine.Command(name = "brightness")
class KeyLightBrightness() : Callable<Int> {

    @CommandLine.Parameters(index = "0")
    var brightness: Int = 0

    @CommandLine.Spec
    lateinit var spec: CommandLine.Model.CommandSpec

    override fun call(): Int {
        if (brightness !in 0..100) {
            throw CommandLine.ParameterException(
                spec.commandLine(),
                """
                    Invalid value '$brightness' for parameter 'brightness': 
                    value must be between 0 and 100.
                """.trimIndent()
            )

        }
        val body = """
            {
              "lights": [
                {
                  "brightness": $brightness
                }
              ]
            }
        """.trimIndent()
        return sendRequest(body)
    }

}