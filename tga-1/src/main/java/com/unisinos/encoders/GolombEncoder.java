package com.unisinos.encoders;

public class GolombEncoder implements Encoder {

    private final int DIVIDER = 64;
    private final String REPEAT_BIT = "0";
    private final String STOP_BIT = "1";

    @Override
    public String encode(int numberToEncode) {
        return determineUnaryPrefix(numberToEncode)
                + STOP_BIT
                + determineBinarySuffix(numberToEncode);
    }

    @Override
    public String decode(String textToDecode) {

        int prefix = textToDecode.indexOf(STOP_BIT);
        int suffix = Integer.parseInt(textToDecode.substring(prefix+1, textToDecode.length()),2);

        return Integer.valueOf(prefix * DIVIDER + suffix).toString();
    }

    private String determineBinarySuffix(int numberToEncode) {

        int suffixLength = (int) ( Math.log(DIVIDER) / Math.log(2) );
        int suffix       = numberToEncode % DIVIDER;

        StringBuffer binarySuffix = new StringBuffer(Integer.toBinaryString(suffix));

        while(binarySuffix.length() < suffixLength)
            binarySuffix.insert(0,REPEAT_BIT);

        return binarySuffix.toString();

    }

    private String determineUnaryPrefix(int numberToEncode) {

        String unaryPrefix = "";
        int prefix         = numberToEncode / DIVIDER;

        for(int i=0; i<prefix; i++)
            unaryPrefix += "0";

        return unaryPrefix;

    }

}