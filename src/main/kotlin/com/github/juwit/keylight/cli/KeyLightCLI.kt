package com.github.juwit.keylight.cli

import picocli.CommandLine

@CommandLine.Command(
    name = "keylight",
    subcommands = [
        KeyLightOn::class,
        KeyLightOff::class,
        KeyLightBrightness::class,
        KeyLightColor::class,
    ]
)
class KeyLightCLI {
}