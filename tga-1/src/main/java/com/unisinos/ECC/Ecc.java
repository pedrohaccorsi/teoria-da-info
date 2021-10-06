package com.unisinos.ECC;

public interface Ecc {
    byte []generateEcc(byte[] buffer);

    default boolean checksum(byte[] buffer, byte check) {
        return false;
    };

    default byte checksum(byte[] buffer) {
        return (byte) 0;
    }
}
