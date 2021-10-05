package com.unisinos.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

    File getFileFromDirectory(String targetDirectory);
    void save(File file, String directory);
    String getAsText(File inputFile) throws IOException;
}
