package main.java.com.unisinos.engines;

import main.java.com.unisinos.enums.EnginesEnum;

public class EngineFactory {

    public static Engine create(EnginesEnum engineType){

        return switch (engineType) {
            case ENCODE -> new EncoderEngine();
            case DECODE -> new DecoderEngine();
        };

    }

}
