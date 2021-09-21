package com.unisinos;

import com.unisinos.engines.EngineFactory;
import com.unisinos.menus.Menu;

public class Main {

    public static void main(String[] args) {
        EngineFactory.create(new Menu().determineEngine()).run();
    }

}
