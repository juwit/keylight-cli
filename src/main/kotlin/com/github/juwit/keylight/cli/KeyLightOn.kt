package com.github.juwit.keylight.cli

import com.github.juwit.keylight.service.on
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "on")
class KeyLightOn : Callable<Int> {
    override fun call() = on()
}