package com.unisinos.engines;

import com.unisinos.enums.EnginesEnum;

public class EngineFactory {

    public static Engine create(EnginesEnum engineType){

        return switch (engineType) {
            case ENCODE -> new EncoderEngine();
            case DECODE -> new DecoderEngine();
            case ECCENCODE -> new ECCEncoderEngine();
            case ECCDECODE -> new ECCDecoderEngine();
        };

    }

}
