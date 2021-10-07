package com.unisinos.encoders.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.utils.StringUtils;

public class EliasGammaEncoder implements Encoder {

    private final String STOP_BIT = "1";
    private final String UNARY_BIT = "0";
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
    public String encode(int numberToEncode) {

        String encoded = "";

        int unaryPart = (int) ( Math.log(numberToEncode) / Math.log(2) );

        encoded += StringUtils.createStringSequence(UNARY_BIT, unaryPart);
        encoded += STOP_BIT;

        int binaryPart = numberToEncode - (int)Math.pow(2,unaryPart);

        String binaryEncoded = Integer.toBinaryString(binaryPart);

        int leadingZeroes = unaryPart - binaryEncoded.length();

        if (leadingZeroes > 0) {
            encoded += StringUtils.createStringSequence("0", leadingZeroes);
        }

        encoded += binaryEncoded;

        return encoded;
    }

    @Override
    public String decode(String text) {
        return decodeText(text, "");
    }

    @Override
    public String decode(byte[] buffer) {
        return decodeText(StringUtils.concatByteArrayWithOffset(buffer, 2), "");
    }

    public String decodeText(String text, String decoded) {
        if (text.isEmpty() || !text.contains(STOP_BIT)) {
            return decoded;
        }
        int unaryPart = text.indexOf(STOP_BIT);

        int binaryPart = Integer.parseInt(text.substring(unaryPart + 1, unaryPart * 2 + 1), 2);

        decoded += new String(Character.toChars(binaryPart + (int) Math.pow(2, unaryPart)));

        return decodeText(text.substring(unaryPart*2 + 1), decoded);

    }

}
