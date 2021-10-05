package com.unisinos.ecc;
public interface Ecc {
    byte checksumResult(byte[] buffer);
    boolean checksum(byte[] buffer);
}