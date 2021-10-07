package com.unisinos.engines;

import com.unisinos.engines.implementations.DecoderEngine;
import com.unisinos.engines.implementations.ECCDecoderEngine;
import com.unisinos.engines.implementations.ECCEncoderEngine;
import com.unisinos.engines.implementations.EncoderEngine;
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
