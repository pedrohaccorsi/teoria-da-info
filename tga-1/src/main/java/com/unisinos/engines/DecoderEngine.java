package main.java.com.unisinos.engines;

import main.java.com.unisinos.files.FileHandler;
import main.java.com.unisinos.menus.Menu;
import main.java.com.unisinos.files.DefaultFileHandler;

public class DecoderEngine implements Engine {

    FileHandler fileHandler;
    Menu menu;

    public DecoderEngine(){
        this.fileHandler = new DefaultFileHandler();
        this.menu        = new Menu();
    }

    @Override
    public void run() {

    }
}
