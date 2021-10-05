package com.unisinos.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DefaultFileHandler implements FileHandler {

    private File rootDirectory;

    public DefaultFileHandler(){
        this.rootDirectory = (new File("")).getAbsoluteFile();
    }

    @Override
    public File getFileFromDirectory(String targetDirectoryName){

        File targetFolder = (new File(rootDirectory.getAbsolutePath() + targetDirectoryName));

        List<String> files = getFilesListFromDirectory(targetFolder);

        int choice = getChoice(files);

        return new File(targetFolder.getAbsolutePath() + "/" + files.get(choice));

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

    private List<String> getFilesListFromDirectory(File targetDirectory) {
        return Arrays.asList(targetDirectory.list());
    }


    @Override
    public void save(File file, String directory) {

    }

    @Override
    public String getAsText(File inputFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
        return lines.stream().reduce("", (s, s2) -> s + "\n" + s2);
    }

}
