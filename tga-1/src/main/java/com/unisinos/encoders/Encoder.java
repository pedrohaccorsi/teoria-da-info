package com.unisinos.encoders;

public interface Encoder {
    public int getCode();
    public int getDivider();
    public String encode(int numberToEncode);
    public String decode(byte[] textToDecode);

}
