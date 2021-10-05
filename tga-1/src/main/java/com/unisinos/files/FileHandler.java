package main.java.com.unisinos.files;

import java.io.File;
import java.util.List;

public interface FileHandler {

    List<File> getFilesFromDir(String directory);
    File getFileFromList(List<File> files);
    void save(File file, String directory);
}
