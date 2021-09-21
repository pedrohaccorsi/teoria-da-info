package com.unisinos.engines;

import com.unisinos.encoders.Encoder;
import com.unisinos.encoders.EncoderFactory;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;
import com.unisinos.menus.Menu;

import java.io.File;

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

        System.out.println("ok lets do it");

        if( 1 == 2 ){
            File[] availableFiles = getAvailableFiles();
            File targetFile       = getFileFromList(availableFiles);
            Encoder encoder       = getEncoder();
            File encodedFile      = encode(targetFile, encoder);

            saveFile(encodedFile);
        }

    }

    private void saveFile(File file){
        //fileHandler.save(file, OUTPUT_PATH);
    }

    private File encode(File targetFile, Encoder encoder){
        return encoder.encode(targetFile);
    }

    private Encoder getEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }

    private File[] getAvailableFiles(){
        //return fileHandler.getFilesFromDir(OUTPUT_PATH);
        return null;
    }

    private File getFileFromList(File[] fileList){
        //return fileHandler.getFileFromList(fileList);
        return null;
    }

}
