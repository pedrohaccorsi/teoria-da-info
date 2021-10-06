package com.unisinos.ECC;

public class Hamming implements Ecc {
    private static final boolean[][] hammingPositions = { {true,    true,   true   },   // 7
                                                          {true,    true,   false  },   // 6
                                                          {true,    false,  true   },   // 5
                                                          {true,    false,  false  },   // 4
                                                          {false,   true,   true   },   // 3
                                                          {false,   true,   false  },   // 2
                                                          {false,   false,  true   } }; // 1

    @Override
    public byte[] generateEcc(byte[] buffer) {
        boolean[] bitstream = new boolean[buffer.length * 8];
        for(int i = 0 ; i < buffer.length ; i++) {
            for (int j = 0 ; j < 8 ; j++)
                bitstream[i*8 + j] = (buffer[i] & (byte)(128 / Math.pow(2, j))) != 0;
        }

        int ones = 0;
        for(boolean ignored : bitstream) ones++;

        boolean[][] hammingMatrix = new boolean[ones][3];
        ones = 0;
        for(int i = 0 ; i < 8 ; i++) {
            if(bitstream[i]) {
                switch (i) {
                    case 4 -> hammingMatrix[ones++] = hammingPositions[0];
                    case 5 -> hammingMatrix[ones++] = hammingPositions[1];
                    case 6 -> hammingMatrix[ones++] = hammingPositions[2];
                    case 7 -> hammingMatrix[ones++] = hammingPositions[4];
                    default -> {
                    }
                }

            }
        }

        boolean[] hammingParityBits = {false, false, false};
        for(int i = 0 ; i < 3 ; i++) {
            int oneCount = 0;
            for(int j = 0 ; j < hammingMatrix.length ; j++) {
                if (hammingMatrix[j][i]) oneCount++;
            }
            if(oneCount%2 != 0) hammingParityBits[i] = true;
        }

        byte ret = (byte) ( ((bitstream[4]?1:0) << 6) +
                            ((bitstream[5]?1:0) << 5) +
                            ((bitstream[6]?1:0) << 4) +
                            ((hammingParityBits[0]?1:0) << 3) +
                            ((bitstream[7]?1:0) << 2) +
                            ((hammingParityBits[1]?1:0) << 1) +
                            (hammingParityBits[2]?1:0) );

        return new byte[]{ret};
    }

    @Override
    public byte checksum(byte[] buffer) {
        boolean[] bitstream = new boolean[buffer.length * 8];
        for(int i = 0 ; i < buffer.length ; i++) {
            for (int j = 0 ; j < 8 ; j++)
                bitstream[i*8 + j] = (buffer[i] & (byte)(128 / Math.pow(2, j))) != 0;
        }

        int ones = 0;
        for(boolean ignored : bitstream) ones++;

        boolean[][] hammingMatrix = new boolean[ones][3];
        ones = 0;
        for(int i = 0 ; i < 8 ; i++) {
            if(bitstream[i]) hammingMatrix[ones++] = hammingPositions[i-1];
        }

        boolean[] hammingParityBits = {false, false, false};
        for(int i = 0 ; i < 3 ; i++) {
            int oneCount = 0;
            for(int j = 0 ; j < hammingMatrix.length ; j++) {
                if (hammingMatrix[j][i]) oneCount++;
            }
            if(oneCount%2 != 0) hammingParityBits[i] = true;
        }

        byte checkOut = (byte) (((hammingParityBits[0]?1:0) << 2) + ((hammingParityBits[1]?1:0) << 1) + (hammingParityBits[2]?1:0));

        if(checkOut != 0) {
            System.out.println("Erro na verificação do codeword Hamming: bit " + checkOut + " inconsistente. ");
            buffer[0] ^= 1 << (checkOut - 1);
        }

        return buffer[0];
    }
}
