package com.github.juwit.keylight.cli

import picocli.CommandLine
import sendRequest
import java.util.concurrent.Callable

@CommandLine.Command(name = "color")
class KeyLightColor() : Callable<Int> {

    @CommandLine.Parameters(index = "0")
    var color: Int = 0

    @CommandLine.Spec
    lateinit var spec: CommandLine.Model.CommandSpec

    override fun call(): Int {
        if (color !in 2900..7000) {
            throw CommandLine.ParameterException(
                spec.commandLine(),
                """
                    Invalid value '$color' for parameter 'color': 
                    value must be between 2900 and 7000.
                """.trimIndent()
            )
        }
        // régression linéaire basique
        val keyLightColor = -0.049 * color + 486
        val rounded = Math.round(keyLightColor).toInt()
        val body = """
            {
              "lights": [
                {
                  "temperature": $rounded
                }
              ]
            }
        """.trimIndent()
        return sendRequest(body)
    }

}