package com.unisinos.encoders.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciEncoder implements Encoder {

    private final int CODE = 2;
    int[] fibonacciNumbers = new int[35];

    public FibonacciEncoder() {
        buildFibonacci();
    }

    private void buildFibonacci() {
        fibonacciNumbers[0] = 1;
        fibonacciNumbers[1] = 2;
        for (int i = 2; i < fibonacciNumbers.length; i ++) {
            fibonacciNumbers[i] = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
        }
    }

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
        List<Integer> indexes = new ArrayList<>();
        // Starts with the last fibonacci number
        for (int i = 34; i >= 0 ; i --) {
            int fibonacci = fibonacciNumbers[i];
            if (fibonacci <= numberToEncode) {
                numberToEncode = numberToEncode - fibonacci;
                indexes.add(i);
            }
            if (numberToEncode == 0) {
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= indexes.get(0); i ++) {
            if (indexes.contains(i)) {
                stringBuilder.append('1');
            } else {
                stringBuilder.append('0');
            }
        }
        stringBuilder.append('1');
        return stringBuilder.toString();
    }

    @Override
    public String decode(String textToDecode) {
        StringBuilder stringBuilder = new StringBuilder();
        while (textToDecode.contains("11")) {
            String codeword = getCodeword(textToDecode);
            System.out.println(codeword.length());
            textToDecode = textToDecode.substring(codeword.length() + 1);
            stringBuilder.append((char) decodeWord(codeword));
        }
        return stringBuilder.toString();
    }

    private String getCodeword(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");

        int index = 0;
        while (!(stringBuilder.charAt(stringBuilder.length() - 1) == '1' && string.charAt(index) == '1')) {
            stringBuilder.append(string.charAt(index));
            index ++;
        }

        stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }

    private int decodeWord(String codeword) {
        int ans = 0;
        for (int i = 0; i < codeword.length(); i ++) {
            if (codeword.charAt(i) == '1') {
                ans += fibonacciNumbers[i];
            }
        }
        return ans;
    }
}
