import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.SocketSecurityException;
import java.util.List;
import java.util.stream.Stream;

public class DeleteEnters {

    public static void main(String[] args) {

        String fileName = "file.txt";

        // readUnicodeJava11(fileName);
        // readUnicodeBufferedReader(fileName);
        readUnicodeFiles(fileName);
        // readUnicodeClassic(fileName);

    }

    // Java 7 - Files.newBufferedReader(path, StandardCharsets.UTF_8)
    // Java 8 - Files.newBufferedReader(path) // default UTF-8
    public static void readUnicodeBufferedReader(String fileName) {

        Path path = Paths.get(fileName);

        // Java 8, default UTF-8
        try (BufferedReader reader = Files.newBufferedReader(path)) {

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readUnicodeFiles(String fileName) {

        Path path = Paths.get(fileName);
        try {

            // Java 11
            // String s = Files.readString(path, StandardCharsets.UTF_8);
            // System.out.println(s);

            // Java 8
            // List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
            String s = String.join("\n", list);
            // list.forEach(System.out::println);
            s = s.replaceAll("\\.\n", "\\$\\$");
            s = s.replaceAll("\n", "");
            s = s.replaceAll("\\$\\$", "\n");
            System.out.println(s);
            String newString = new String(s.getBytes(), "iso-8859-1");
            FileWriter writer = new FileWriter(fileName);
            writer.write(newString);
            writer.close();
            System.out.println("File edited successfully.");

            // Java 8
            // Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
            // lines.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Java 11, adds charset to FileReader
    // public static void readUnicodeJava11(String fileName) {

    // Path path = Paths.get(fileName);

    // try (FileReader fr = new FileReader(fileName, StandardCharsets.UTF_8);
    // BufferedReader reader = new BufferedReader(fr)) {

    // String str;
    // while ((str = reader.readLine()) != null) {
    // System.out.println(str);
    // }

    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // }

    public static void readUnicodeClassic(String fileName) {

        File file = new File(fileName);

        try (FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)) {

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}