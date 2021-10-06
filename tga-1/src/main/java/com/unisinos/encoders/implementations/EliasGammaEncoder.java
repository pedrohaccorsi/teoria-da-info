package com.unisinos.encoders.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.utils.StringUtils;

public class EliasGammaEncoder implements Encoder {

    private final int CODE = 1;

    @Override
    public int getCode() {
        return CODE;
    }

    @Override
    public int getDivider() {
        return 0;
    }

    @Override
    public String decode(byte[] textToDecode) {
        return decode(StringUtils.concatByteArrayWithOffset(textToDecode, 2));
    }

    @Override
    public String encode(int numberToEncode) {
        return null;
    }

    @Override
    public String decode(String textToDecode) {
        return null;
    }

}
