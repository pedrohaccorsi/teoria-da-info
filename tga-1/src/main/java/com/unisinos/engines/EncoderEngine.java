package main.java.com.unisinos.engines;

import main.java.com.unisinos.encoders.Encoder;
import main.java.com.unisinos.encoders.EncoderFactory;
import main.java.com.unisinos.files.DefaultFileHandler;
import main.java.com.unisinos.files.FileHandler;
import main.java.com.unisinos.menus.Menu;

import java.io.File;
import java.util.List;

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
            List<File> availableFiles = getAvailableFiles();
            File targetFile           = getFileFromList(availableFiles);
            Encoder encoder           = getEncoder();
            File encodedFile          = encode(targetFile, encoder);

            saveFile(encodedFile);
        }

    }

    private void saveFile(File file){
        fileHandler.save(file, OUTPUT_PATH);
    }

    private File encode(File targetFile, Encoder encoder){
        String encoded = encoder.encode(1);
        return null;
    }

    private Encoder getEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }

    private List<File> getAvailableFiles(){
        return fileHandler.getFilesFromDir(OUTPUT_PATH);
    }

    private File getFileFromList(List<File> fileList){
        return fileHandler.getFileFromList(fileList);
    }

}
