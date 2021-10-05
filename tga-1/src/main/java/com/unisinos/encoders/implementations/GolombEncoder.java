package com.unisinos.encoders.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.utils.StringUtils;

public class GolombEncoder implements Encoder {

    public static final int    DIVIDER    = 64;
    public static final String REPEAT_BIT = "0";
    public static final String STOP_BIT   = "1";

    @Override
    public String encode(int numberToEncode) {
        int suffixLength = (int) ( Math.log(DIVIDER) / Math.log(2) );

        int suffix = numberToEncode % DIVIDER;

        int prefix = numberToEncode / DIVIDER;

        String prefixBinary = StringUtils.createStringSequence(REPEAT_BIT, prefix) + STOP_BIT;

        String suffixBinary = Integer.toBinaryString(suffix);

        if (suffixBinary.length() < suffixLength) {
            suffixBinary = StringUtils.createStringSequence("0", suffixLength - suffixBinary.length()) + suffixBinary;
        }

        return prefixBinary + suffixBinary;
    }

    private String decode(String textToDecode) {

        StringBuilder decoded = new StringBuilder();

        while(!textToDecode.isEmpty() && textToDecode.contains("1")) {
            int prefixCounter = 0;
            char[] splitString = textToDecode.toCharArray();

            int suffixLength = (int) ( Math.log(DIVIDER) / Math.log(2) );

            while (splitString[prefixCounter] == (REPEAT_BIT.charAt(0))) {
                prefixCounter++;
            }

            int prefix = prefixCounter * DIVIDER;

            int suffixStart = textToDecode.indexOf(STOP_BIT) + 1;
            int x = Integer.parseInt(textToDecode.substring(suffixStart, suffixStart + suffixLength), 2);
            textToDecode = textToDecode.substring(suffixStart + suffixLength);
            decoded.append(Character.toChars(prefix + x));
        }

        return decoded.toString();

    }

    @Override
    public String decode(byte[] textToDecode) {
        return decode(StringUtils.concatByteArrayWithOffset(textToDecode, 0));
    }

}