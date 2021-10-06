package com.unisinos.encoders.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.utils.StringUtils;

public class UnaryEncoder implements Encoder {

    private final static String REPEAT_BIT = "1";
    private final static String STOP_BIT = "0";

    @Override
    public int getCode() {
        return 3;
    }

    @Override
    public int getDivider() {
        return 0;
    }

    @Override
    public String encode(int numberToEncode) {
        return StringUtils.createStringSequence(REPEAT_BIT, numberToEncode) + STOP_BIT;
    }

    @Override
    public String decode(byte[] textToDecode) {
        return decode(StringUtils.concatByteArrayWithOffset(textToDecode, 2));
    }

    @Override
    public String decode(String text) {
        StringBuilder decodedBuilder = new StringBuilder();

        do {
            int firstStopBit = text.indexOf(STOP_BIT);
            decodedBuilder.append((char)firstStopBit);
            text = text.substring(firstStopBit + 1);
        } while (text.length() > 0 && text.contains(STOP_BIT));

        return decodedBuilder.toString();
    }
}