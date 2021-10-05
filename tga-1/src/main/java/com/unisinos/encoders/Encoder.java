package com.unisinos.encoders;

public interface Encoder {
    public String encode(int numberToEncode);
    public String decode(String textToDecode);
}
