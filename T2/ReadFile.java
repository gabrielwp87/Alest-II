import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Leitura Arquivo
 * 
 * @version 2023-06-12
 */
public class ReadFile {
    private String file;

    public ReadFile(String file) {
        this.file = file;
    }

    public ArrayList<String> read() throws IOException {
        String currDir = Paths.get("").toAbsolutePath().toString();
        String completeName = currDir + "\\T2\\Cases\\" + this.file;
        System.out.println(completeName);
        Path path = Paths.get(completeName);

        ArrayList<String> fileLines = (ArrayList<String>) Files.readAllLines(path);

        return fileLines;
    }

}