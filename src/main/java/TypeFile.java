import org.apache.commons.codec.digest.DigestUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;


@Command(name = "file", version = "1.0.0", description = "hashing files with various algorithms")
public class TypeFile implements Callable<Integer> {

    @Parameters(paramLabel = "FILE", index = "0", arity = "1..*", description = "file/s to be hashed")
    private File[] files;

    @Option(names = {"-a", "--algorithm"}, arity = "0..1", defaultValue = "SHA-256", description = "SHA-1\n" +
            "SHA-256\n" +
            "SHA-384\n" +
            "SHA-512\n" +
            "SHA-512/224\n" +
            "SHA-512/256\n" +
            "SHA3-224\n" +
            "SHA3-256\n" +
            "SHA3-384\n" +
            "SHA3-512\n" +
            "default value is: ${DEFAULT-VALUE}")
    String algorithm;

    int counter;

    @Override
    public Integer call() throws Exception {


        Map<File, Object> mapOfFilesToTheirBytes = Arrays.stream(files).collect(Collectors.toMap(Function.identity(), file -> {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));


        //noinspection DuplicatedCode
        Path path = Paths.get("./hashing-functions/hashing_functions.txt");

        List<String> listOfHashingFunctions = Files.readAllLines(path);

        Map<String, String> hashingFunctions = listOfHashingFunctions
                .stream()
                .collect(Collectors.toMap(str -> str.split(",\\s+")[1].toLowerCase(), str -> str.split(",\\s+")[0]));


        mapOfFilesToTheirBytes.forEach((file, object) -> {
            try {

                String hashed = (String) DigestUtils
                        .class
                        // we pass "String.class" to "getMethod" so it knows beforehand parameter's type that will be used for invoking
                        // the method.
                        .getMethod(hashingFunctions.get(algorithm.toLowerCase()), byte[].class)
                        // we pass "null" in place of a reference because the method we're invoking is static method.
                        // this is stated in docs of the method.
                        .invoke(null, object);

                System.out.printf("file(%d) message digest: %s%n", ++counter, hashed);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        });


        return 0;
    }

}
