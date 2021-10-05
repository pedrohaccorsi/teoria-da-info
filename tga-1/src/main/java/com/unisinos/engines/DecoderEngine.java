package com.unisinos.engines;

import com.unisinos.files.FileHandler;
import com.unisinos.menus.Menu;
import com.unisinos.files.DefaultFileHandler;

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
