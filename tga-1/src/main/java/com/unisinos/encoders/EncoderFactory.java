package com.unisinos.encoders;

import com.unisinos.encoders.implementations.*;
import com.unisinos.enums.EncodersEnum;

public class EncoderFactory {

    public static Encoder create(EncodersEnum encoderType){
        return switch (encoderType) {
            case UNARY -> new UnaryEncoder();
            case DELTA -> new DeltaEncoder();
            case GOLOMB -> new GolombEncoder();
            case FIBONACCI -> new FibonacciEncoder();
            case ELIAS_GAMMA -> new EliasGammaEncoder();
        };
    }

}
