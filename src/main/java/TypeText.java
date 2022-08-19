import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;


@Command(name = "text", description = "hashing texts with various algorithms")
public class TypeText implements Callable<Integer> {


    @Parameters(paramLabel = "STR", index = "0", arity = "0..1", description = "string to be hashed")
    private String str;

    @Option(names = {"-a", "--algorithm"}, arity = "0..1", description = "SHA-1\n" +
            "SHA-256\n" +
            "SHA-384\n" +
            "SHA-512\n" +
            "SHA-512/224\n" +
            "SHA-512/256\n" +
            "SHA3-224\n" +
            "SHA3-256\n" +
            "SHA3-384\n" +
            "SHA3-512\n" +
            "Argon2\n" +
            "bcrypt\n" +
            "scrypt\n" +
            "PBKDF2")
    private String algorithm = "SHA-256";


    @Override
    public Integer call() throws Exception {

        Path path = Paths.get("commons-codec.txt");

        List<String> listOfHashingFunctions = Files.readAllLines(path);

        Map<String, String> hashing_functions = listOfHashingFunctions
                .stream()
                .collect(Collectors.toMap(str->str.split(",\\s+")[1].toLowerCase(), str -> str.split(",\\s+")[0]));


        String hashed = (String) DigestUtils
                .class
                // we pass "String.class" to "getMethod" so it knows beforehand parameter's type that will be used for invoking
                // the method.
                .getMethod(hashing_functions.get(algorithm.toLowerCase()), String.class)
                .invoke(null, str);

        System.out.printf("message digest: %s%n", hashed);

        return 0;
    }

}
































