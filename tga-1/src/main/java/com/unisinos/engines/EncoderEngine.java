package com.unisinos.engines;

import com.unisinos.encoders.Encoder;
import com.unisinos.encoders.EncoderFactory;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;
import com.unisinos.menus.Menu;
import java.io.File;
import java.io.IOException;

public class EncoderEngine implements Engine {

    FileHandler fileHandler;
    Menu menu;

    private final String OUTPUT_PATH = "/output";
    private final String INPUT_PATH = "/input";
    private final String TXT_EXT = ".txt";
    private final String COD_EXT = ".cod";

    public EncoderEngine(){
        this.fileHandler = new DefaultFileHandler();
        this.menu        = new Menu();
    }

    @Override
    public void run(){

        Encoder encoder       = determineEncoder();
        File fileToBeEncoded  = determineFile();
        String fileAsText     = getFileAsText(fileToBeEncoded);

        /*
        NEXT: send the content of fileAsText to encoder.encode(),
        char by char, probably converted into their ASCII values.
        */

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
        return fileHandler.getFileFromDirectory("/src/main/resources");
    }

    private void saveFile(File file){
        fileHandler.save(file, OUTPUT_PATH);
    }

    private File encode(File targetFile, Encoder encoder){
        String encoded = encoder.encode(1);
        return null;
    }

    private Encoder determineEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }


}
