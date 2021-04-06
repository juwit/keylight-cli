import com.github.juwit.keylight.cli.KeyLightCLI
import picocli.CommandLine


fun main(args: Array<String>) {
    val exitCode = CommandLine(KeyLightCLI()).execute(*args);
    System.exit(exitCode);
}