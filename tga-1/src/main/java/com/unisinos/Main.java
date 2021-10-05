package com.unisinos;

import com.unisinos.menus.Menu;
import com.unisinos.engines.EngineFactory;

public class Main {

    public static void main(String[] args) {
        EngineFactory.create(new Menu().determineEngine()).run();
    }

}
