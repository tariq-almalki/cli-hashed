import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "Hash",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        subcommands = {TypeText.class, TypeFile.class, CommandLine.HelpCommand.class},
        description = "hash texts and file with ease.",
        sortOptions = false,
        showDefaultValues = true)

public class Main {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main())
                .setUsageHelpLongOptionsMaxWidth(30)
                .execute(args);

        System.exit(exitCode);
    }
}
