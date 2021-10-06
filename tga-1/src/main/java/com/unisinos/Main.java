package com.unisinos;

//import com.unisinos.menus.Menu;
//import com.unisinos.engines.EngineFactory;

public class Main {

    public static void main(String[] args) {

        //EngineFactory.create(new Menu().determineEngine()).run();

        byte[] buffer = new byte[] {49, 2, 19, 27};
        for (int i = 0; i < buffer.length; i++) {
            String str = Integer.toBinaryString(buffer[i]);
        }
    }

}
