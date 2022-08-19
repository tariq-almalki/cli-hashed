import com.password4j.Password;
import org.apache.commons.codec.digest.DigestUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


@Command(name = "file", description = "hashing files with various algorithms")
public class TypeFile implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", index = "0", arity = "1", description = "file to be hashed")
    private File file;

    @Option(names = {"-a", "--algorithm"}, arity = "0..1", description = "SHA-1\n" +
            "SHA-256\n" +
            "SHA-384\n" +
            "SHA-512\n" +
            "SHA-512/224\n" +
            "SHA-512/256\n" +
            "SHA3-224\n" +
            "SHA3-256\n" +
            "SHA3-384\n" +
            "SHA3-512\n")
    String algorithm = "SHA1-256";

    @Override
    public Integer call() throws Exception {
        byte[] bytes = Files.readAllBytes(file.toPath());

        //noinspection DuplicatedCode
        Path path = Paths.get("./hashing-functions/hashing_functions.txt");

        List<String> listOfHashingFunctions = Files.readAllLines(path);

        Map<String, String> hashingFunctions = listOfHashingFunctions
                .stream()
                .collect(Collectors.toMap(str -> str.split(",\\s+")[1].toLowerCase(), str -> str.split(",\\s+")[0]));


        String hashed = (String) DigestUtils
                .class
                // we pass "String.class" to "getMethod" so it knows beforehand parameter's type that will be used for invoking
                // the method.
                .getMethod(hashingFunctions.get(algorithm.toLowerCase()), byte[].class).invoke(null, bytes);

        System.out.printf("message digest: %s%n", hashed);

        return 0;
    }

}
