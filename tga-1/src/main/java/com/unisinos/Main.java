package main.java.com.unisinos;

import main.java.com.unisinos.menus.Menu;
import main.java.com.unisinos.engines.EngineFactory;

public class Main {

    public static void main(String[] args) {
        EngineFactory.create(new Menu().determineEngine()).run();
    }

}
