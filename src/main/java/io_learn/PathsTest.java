package io_learn;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class PathsTest {
    public static void main(String[] args) throws IOException {
        Path absolute = Paths.get("/home", "harry");
        System.out.println(absolute.getFileName());
        Path relative = Paths.get("myprog", "conf", "user.properties");
        System.out.println(relative);
        Path workRelative = Paths.get("work");
        System.out.println(workRelative);
        Path workPath = Paths.get("/").resolve(workRelative);
        System.out.println(workPath);
        Path tempPath = workPath.resolveSibling("temp");
        System.out.println(tempPath);
        Path userDir = Paths.get("useheart");
        System.out.println(userDir.resolve("IDEA"));
        System.out.println(userDir.resolveSibling("IDEA"));
        System.out.println(userDir.relativize(Paths.get("IDEA")));
        System.out.println(userDir.relativize(Paths.get("IDEA")).normalize());
        Path useheart = Paths.get("/Users", "useheart");
        InputStream inputStream = Files.newInputStream(useheart);
        inputStream.close();

        try (Stream<Path> entries = Files.list(useheart)) {
            entries.forEach(System.out::println);
        }

        try (Stream<Path> entries = Files.walk(useheart)) {
            entries.limit(3).forEach(System.out::println);
        }


        System.out.println();
    }
}
