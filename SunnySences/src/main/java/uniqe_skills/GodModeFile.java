package uniqe_skills;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by JLyc on 4. 2. 2015.
 */
public class GodModeFile {

    public static void main(String[] args) throws IOException {
        Path filePath = FileSystems.getDefault().getPath("C:\\JLycFolder.{ED7BA470-8E54-465E-825C-99712043E01C}");
        Files.createDirectory(filePath);
    }
}
