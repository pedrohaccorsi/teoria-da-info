package com.unisinos.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

    File getFileFromDirectory(String targetDirectory, String extension, boolean allowFilesWithoutExtension);
    void createAndWriteToFile(String filename, String filePath, byte[] buffer) throws IOException;
    void createAndWriteToFile(String filename, String filePath, String buffer) throws IOException;
    String getAsText(File inputFile) throws IOException;
    byte[] getAsByteArray(File inputFile) throws IOException;
}
