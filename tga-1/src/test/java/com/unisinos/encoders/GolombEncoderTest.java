package com.unisinos.encoders;

import com.unisinos.encoders.implementations.GolombEncoder;
import org.junit.Assert;
import org.junit.Test;

public class GolombEncoderTest {

    private Encoder cut = new GolombEncoder();
    private int toBeEncoded;;
    private String result;
    private String toBeDecoded;
    private final String STOPPING_BIT = "1";

    private void givenNumberToEncode(int number){
        this.toBeEncoded = number;
    }

    private void givenTextToDecode(String text){
        this.toBeDecoded = text;
    }

    private void whenDecodeIsCalled(){
        return;//result = this.cut.decode(toBeDecoded);
    }

    private void whenEncodeIsCalled(){
        result = this.cut.encode(toBeEncoded);
    }

    private void thenResultShouldBe(String expected){
        Assert.assertEquals(expected, result);
    }

    @Test
    public void encode100(){
        String prefix = "0";
        String suffix = "100100";

        givenNumberToEncode(100);
        whenEncodeIsCalled();
        thenResultShouldBe(prefix + STOPPING_BIT + suffix);
    }

}
