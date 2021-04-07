package com.github.juwit.keylight.cli

import com.github.juwit.keylight.service.changeColor
import picocli.CommandLine
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
        return changeColor(color)
    }

}