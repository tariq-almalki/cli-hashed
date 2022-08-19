import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import org.apache.commons.codec.digest.DigestUtils;

@Command(name = "file", description = "hashing files with various algorithms")
public class TypeFile implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", index = "0", arity = "1", description = "file to be hashed")
    private File file;

    @Option(names = {"-a", "--algorithm"}, arity = "0..1", description = "hashing functions")
    String algorithm = "SHA1-256";

    @Override
    public Integer call() throws Exception {
        byte[] bytes = Files.readAllBytes(file.toPath());


        return 0;
    }

}
