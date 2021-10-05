package main.java.com.unisinos.ecc;

public class Crc8 implements Ecc {
    @Override
    public byte checksumResult(byte[] buffer) {
        return 0;
    }

    @Override
    public boolean checksum(byte[] buffer) {
        return false;
    }
}
