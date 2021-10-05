package com.unisinos.ECC;

public interface Ecc {
    byte []generateEcc(byte[] buffer);
    boolean checksum(byte[] buffer);
}
