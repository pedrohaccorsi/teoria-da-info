package test.java.com.unisinos.encoders;

import main.java.com.unisinos.encoders.Encoder;
import main.java.com.unisinos.encoders.GolombEncoder;
import org.junit.Assert;
import org.junit.Test;

public class GolombEncoderTest {

    private Encoder cut = new GolombEncoder();
    private String result;
    private int toBeEncoded;
    private final String STOPPING_BIT = "1";

    private void givenNumberToEncode(int number){
        this.toBeEncoded = number;
    }

    private void whenCutIsCalled(){
        result = this.cut.encode(toBeEncoded);
    }

    private void thenResultShouldBe(String expected){
        Assert.assertEquals(result, expected);
    }

    @Test
    public void encode100(){
        String prefix = "0";
        String suffix = "100100";

        givenNumberToEncode(25);
        whenCutIsCalled();
        thenResultShouldBe(prefix + STOPPING_BIT + suffix);
    }
}
