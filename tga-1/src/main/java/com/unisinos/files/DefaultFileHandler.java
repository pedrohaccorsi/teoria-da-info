package com.unisinos.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DefaultFileHandler implements FileHandler {

    private File rootDirectory;

    public DefaultFileHandler(){
        this.rootDirectory = (new File("")).getAbsoluteFile();
    }

    @Override
    public File getFileFromDirectory(String targetDirectoryName, String extension){

        System.out.println(rootDirectory.getAbsolutePath() );
        File targetFolder = (new File(rootDirectory.getAbsolutePath() + targetDirectoryName));

        List<String> files = getFilesListFromDirectory(targetFolder, extension);

        int choice = getChoice(files);

        return new File(targetFolder.getAbsolutePath() + "/" + files.get(choice));

    }

    @Override
    public void createAndWriteToFile(String fileName, String filePath, byte[] buffer) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath + fileName), false)) {
            fos.write(buffer);
        }

    }

    @Override
    public void createAndWriteToFile(String filename, String filePath, String buffer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath + filename)))) {
            writer.write(buffer);
        }
    }

    private int getChoice(List<String> files) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose one of the following options:");

        for (int i = 0; i < files.size(); i++)
            System.out.println("(" + i + ") - " + files.get(i));

        int choice = scanner.nextInt();

        while(choice > files.size()-1|| choice < 0) {
            System.out.println("Chose one of the following options:");
            choice = scanner.nextInt();
        }
        return choice;
    }

    private List<String> getFilesListFromDirectory(File targetDirectory, String extension) {
        return Arrays.asList(targetDirectory.list())
                .stream()
                .filter((String s) -> {return s.endsWith(extension) ;})
                .collect(Collectors.toList());
    }

    @Override
    public String getAsText(File inputFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
        return lines.stream().reduce("", (s, s2) -> s + "\n" + s2);
    }

    @Override
    public byte[] getAsByteArray(File inputFile) throws IOException {
        return Files.readAllBytes(inputFile.toPath());
    }

}
