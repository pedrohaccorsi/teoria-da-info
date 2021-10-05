package main.java.com.unisinos.encoders;

public class GolombEncoder implements Encoder {

    private final int DIVIDER = 64;
    private final String REPEAT_BIT = "1";
    private final String STOP_BIT = "0";

    @Override
    public String encode(int numberToEncode) {

        int suffixLength = (int) ( Math.log(DIVIDER) / Math.log(2) );
        int suffix       = numberToEncode % DIVIDER;
        int prefix       = numberToEncode / DIVIDER;

        String prefixInBinary = Integer.toBinaryString(prefix);
        String suffixInBinary = Integer.toBinaryString(prefix);

        while (suffixInBinary.length() < suffixLength) {
            suffixInBinary = suffixInBinary.substring(0, 0) + "0" + suffixInBinary.substring(1);
        }

        return prefixInBinary + STOP_BIT + suffixInBinary;

    }

}