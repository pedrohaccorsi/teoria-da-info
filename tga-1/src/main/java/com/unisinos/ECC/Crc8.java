package com.unisinos.ECC;

public class Crc8 implements Ecc {
    private static final boolean[] G = {true, false, false, false, false, false, true, true, true}; // 0x107

    @Override
    public byte[] generateEcc(byte[] buffer) {
        boolean[] bitstream = new boolean[(buffer.length + 1) * 8];
        for(int i = 0 ; i < buffer.length ; i++) {
            for (int j = 0 ; j < 8 ; j++)
                bitstream[i*8 + j] = (buffer[i] & (byte)(128 / Math.pow(2, j))) != 0;
        }

        for(int i = 0 ; i < buffer.length * 8 ; i++) {
            if(bitstream[i]) {
                for(int c = i ; c < i + 9 ; c++)
                    bitstream[c] ^= G[c-i];
            }
        }

        byte[] ret = {0};
        for(int i = 0 ; i < 8 ; i++)
            if(bitstream[bitstream.length - 8 + i]) ret[0] |= 1 << (7 - i);

        return ret;
    }

    @Override
    public boolean checksum(byte[] buffer, byte check) {
        return this.generateEcc(buffer)[0] == check;
    }
}
