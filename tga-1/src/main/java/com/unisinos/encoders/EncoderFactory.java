package com.unisinos.encoders;

import com.unisinos.enums.EncodersEnum;

public class EncoderFactory {

    public static Encoder create(EncodersEnum encoderType){
        return switch (encoderType) {
            case GOLOMB -> new GolombEncoder();
            case ELIAS_GAMMA, FIBONACCI, UNARY, DELTA -> null;
        };
    }
}
