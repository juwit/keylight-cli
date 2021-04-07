import com.github.juwit.keylight.cli.KeyLightCLI
import picocli.CommandLine
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val exitCode = CommandLine(KeyLightCLI()).execute(*args);
    exitProcess(exitCode);
}