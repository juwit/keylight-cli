package com.github.juwit.keylight.cli

import com.github.juwit.keylight.service.off
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "off")
class KeyLightOff : Callable<Int> {
    override fun call() = off()
}