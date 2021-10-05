package com.unisinos.engines;

import com.unisinos.encoders.Encoder;
import com.unisinos.encoders.EncoderFactory;
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

        Encoder encoder            = determineEncoder();
        File fileToBeEncoded       = determineFile();
        byte[] fileAsBytes         = getFileAsBytes(fileToBeEncoded);
        String decodedFile         = encoder.decode(fileAsBytes);

        try{
            fileHandler.createAndWriteToFile(
                    fileToBeEncoded.getName().replace(".cod", ".dec"),
                    fileToBeEncoded.getAbsolutePath().substring(
                            0,
                            (fileToBeEncoded.getAbsolutePath().length() - fileToBeEncoded.getName().length())
                    ),
                    decodedFile
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        return fileHandler.getFileFromDirectory("/src/main/resources", ".cod");
    }

    private File encode(File targetFile, Encoder encoder){
        String encoded = encoder.encode(1);
        return null;
    }

    private Encoder determineEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }


}
