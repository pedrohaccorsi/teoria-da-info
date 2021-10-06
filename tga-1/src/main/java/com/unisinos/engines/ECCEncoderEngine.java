package com.unisinos.engines;

import com.unisinos.ECC.Crc8;
import com.unisinos.ECC.Hamming;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ECCEncoderEngine implements Engine {

    FileHandler fileHandler;
    Crc8 crc = new Crc8();
    Hamming ham = new Hamming();

    public ECCEncoderEngine() {
        this.fileHandler = new DefaultFileHandler();
    }

    @Override
    public void run() {
        File fileToEncode = determineFile();
        byte[] fileAsBytes;
        List<Byte> encodedFileBytes = new ArrayList<Byte>();
        

        fileAsBytes = getFileAsBytes(fileToEncode);

        byte[] headerMerge = {fileAsBytes[0], fileAsBytes[1]};
        byte headerCheck = crc.generateEcc(headerMerge)[0];

        encodedFileBytes.add(fileAsBytes[0]);
        encodedFileBytes.add(fileAsBytes[1]);
        encodedFileBytes.add(headerCheck);

        for (int i = 2 ; i < fileAsBytes.length ; i++) {
            boolean[] bitstream = new boolean[8];
            for (int j = 0 ; j < 8 ; j++)
                bitstream[j] = (fileAsBytes[i] & (byte)(128 / Math.pow(2, j))) != 0;

            byte nibble1 = (byte) ( ((bitstream[0]?1:0) << 3) +
                                    ((bitstream[1]?1:0) << 2) +
                                    ((bitstream[2]?1:0) << 1) +
                                    ((bitstream[3]?1:0)) );
            encodedFileBytes.add(ham.generateEcc(new byte[]{nibble1})[0]);

            byte nibble2 = (byte) ( ((bitstream[4]?1:0) << 3) +
                                    ((bitstream[5]?1:0) << 2) +
                                    ((bitstream[6]?1:0) << 1) +
                                    ((bitstream[7]?1:0)) );
            encodedFileBytes.add(ham.generateEcc(new byte[]{nibble2})[0]);
        }

        Object[] converted = encodedFileBytes.toArray();
        byte[] byteConverted = new byte[converted.length];

        for(int i = 0 ; i < converted.length ; i++) byteConverted[i] = (byte) converted[i];

        this.saveEncodedFile(fileToEncode, byteConverted);
    }

    private void saveEncodedFile(File fileToBeEncoded, byte[] byteArray) {
        try{
            fileHandler.createAndWriteToFile(
                    fileToBeEncoded.getName().contains(".")
                        ? fileToBeEncoded.getName().replace(".cod", ".ecc")
                        : fileToBeEncoded.getName() + ".ecc",
                    fileToBeEncoded.getAbsolutePath().substring(
                            0,
                            (fileToBeEncoded.getAbsolutePath().length() - fileToBeEncoded.getName().length())
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
        return fileHandler.getFileFromDirectory("/tga-1/src/main/resources", ".cod", false);
    }
}
