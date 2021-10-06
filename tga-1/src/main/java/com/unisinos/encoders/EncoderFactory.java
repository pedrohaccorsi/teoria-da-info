package com.unisinos.encoders;

import com.unisinos.encoders.implementations.GolombEncoder;
import com.unisinos.encoders.implementations.UnaryEncoder;
import com.unisinos.enums.EncodersEnum;

public class EncoderFactory {

    public static Encoder create(EncodersEnum encoderType){
        return switch (encoderType) {
            case GOLOMB -> new GolombEncoder();
            case UNARY -> new UnaryEncoder();
            case ELIAS_GAMMA, FIBONACCI, DELTA -> null;
        };
    }
}
