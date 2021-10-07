package com.unisinos.engines.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.encoders.EncoderFactory;
import com.unisinos.engines.Engine;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;
import com.unisinos.menus.Menu;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DecoderEngine implements Engine {

    FileHandler fileHandler;
    Menu menu;

    public DecoderEngine(){
        this.fileHandler = new DefaultFileHandler();
        this.menu        = new Menu();
    }

    @Override
    public void run(){

        Encoder encoder      = determineEncoder();
        File fileToBeEncoded = determineFile();
        byte[] fileAsBytes   = getFileAsBytes(fileToBeEncoded);
        String decodedFile   = encoder.decode(fileAsBytes);


        String fileName = saveDecodedFile(fileToBeEncoded, decodedFile);

        System.out.println("The decoded file "+fileName+ " was created!");

    }

    private String saveDecodedFile(File fileToBeEncoded, String decodedFile) {

        String fileName = fileToBeEncoded.getName().contains(".")
                ? fileToBeEncoded.getName().replace(".cod", ".dec")
                : fileToBeEncoded.getName() + ".dec";

        String filePath = fileToBeEncoded
                .getAbsolutePath()
                .substring(0, (fileToBeEncoded.getAbsolutePath().length() - fileToBeEncoded.getName().length()))
                .replace("encoded", "decoded");

        try{
            fileHandler.createAndWriteToFile(
                    fileName,
                    filePath,
                    decodedFile
            );
        } catch (IOException e) {

        }

        return fileName;

    }

    private List<Integer> convertAllCharsToASCII(String fileAsText) {
        return fileAsText
                .chars()
                .mapToObj(c -> (char) c)
                .map(character -> (int) character)
                .collect(Collectors.toList());
    }

    private byte[] getFileAsBytes(File fileToBeEncoded) {
        try {
            return fileHandler.getAsByteArray(fileToBeEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileAsText(File fileToBeEncoded) {
        try {
            return fileHandler.getAsText(fileToBeEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private File determineFile() {
        return fileHandler.getFileFromDirectory("/tga-1/encodedFiles", ".cod", true);
    }

    private Encoder determineEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }

}
