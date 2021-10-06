package com.unisinos.engines;

import com.unisinos.ECC.Crc8;
import com.unisinos.ECC.Hamming;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ECCDecoderEngine implements Engine {
    FileHandler fileHandler;
    Crc8 crc = new Crc8();
    Hamming ham = new Hamming();

    public ECCDecoderEngine() {
        this.fileHandler = new DefaultFileHandler();
    }

    @Override
    public void run() {
        File fileToDecode = determineFile();
        byte[] fileAsBytes;
        List<Byte> decodedFileBytes = new ArrayList<Byte>();

        fileAsBytes = getFileAsBytes(fileToDecode);

        byte[] headerMerge = {fileAsBytes[0], fileAsBytes[1]};
        if(!crc.checksum(headerMerge, fileAsBytes[2])) {
            System.out.println("Error while verifying integrity of header, cannot decode file!");
            return;
        }

        decodedFileBytes.add(fileAsBytes[0]);
        decodedFileBytes.add(fileAsBytes[1]);

        byte nibble1 = 0;
        byte nibble2 = 0;
        for(int i = 3 ; i < fileAsBytes.length ; i++) {
            byte corrected = ham.checksum(new byte[]{fileAsBytes[i]});

            boolean[] bitstream = new boolean[8];
            for (int j = 0 ; j < 8 ; j++)
                bitstream[j] = (corrected & (byte)(128 / Math.pow(2, j))) != 0;

            if(i%2 == 0) {
                nibble2 = (byte) ( ((bitstream[1]?1:0) << 3) +
                                   ((bitstream[2]?1:0) << 2) +
                                   ((bitstream[3]?1:0) << 1) +
                                   ((bitstream[5]?1:0)) );

                byte combined = (byte) ((nibble1 << 4 ) + nibble2);
                decodedFileBytes.add(combined);
            } else {
                nibble1 = (byte) ( ((bitstream[1]?1:0) << 3) +
                                   ((bitstream[2]?1:0) << 2) +
                                   ((bitstream[3]?1:0) << 1) +
                                   ((bitstream[5]?1:0)) );
            }
        }

        Object[] converted = decodedFileBytes.toArray();
        byte[] byteConverted = new byte[converted.length];

        for(int i = 0 ; i < converted.length ; i++) byteConverted[i] = (byte) converted[i];

        this.saveDecodedFile(fileToDecode, byteConverted);
    }

    private void saveDecodedFile(File fileToBeDecoded, byte[] byteArray) {
        try{
            fileHandler.createAndWriteToFile(
                    fileToBeDecoded.getName().contains(".")
                        ? fileToBeDecoded.getName().replace(".ecc", ".cod")
                        : fileToBeDecoded.getName() + ".cod",
                    fileToBeDecoded.getAbsolutePath().substring(
                            0,
                            (fileToBeDecoded.getAbsolutePath().length() - fileToBeDecoded.getName().length())
                    ),
                    byteArray
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getFileAsBytes(File fileToBeEncoded) {
        try {
            return fileHandler.getAsByteArray(fileToBeEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File determineFile() {
        return fileHandler.getFileFromDirectory("/tga-1/src/main/resources", ".ecc", false);
    }
}
