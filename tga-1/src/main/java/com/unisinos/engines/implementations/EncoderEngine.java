package com.unisinos.engines.implementations;

import com.unisinos.encoders.Encoder;
import com.unisinos.encoders.EncoderFactory;
import com.unisinos.engines.Engine;
import com.unisinos.files.DefaultFileHandler;
import com.unisinos.files.FileHandler;
import com.unisinos.menus.Menu;
import com.unisinos.utils.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EncoderEngine implements Engine {

    FileHandler fileHandler;
    Menu menu;

    public EncoderEngine(){
        this.fileHandler = new DefaultFileHandler();
        this.menu        = new Menu();
    }

    @Override
    public void run(){

        Encoder encoder      = determineEncoder();
        File fileToBeEncoded = determineFile();
        byte[] fileAsBytes;

        try{
            String fileAsText           = getFileAsText(fileToBeEncoded);
            List<Integer> charsAsASCII  = convertAllCharsToASCII(fileAsText);
            List<String> encodedASCII   = encodeASCIIchars(charsAsASCII, encoder);
            fileAsBytes                 = convertASCIIListToByteArray(encodedASCII);
        }
        catch(IOException e){
            fileAsBytes = getFileAsBytes(fileToBeEncoded);
        }

        String fileName = saveEncodedFile(fileToBeEncoded, fileAsBytes);
        System.out.println("The encoded file "+fileName+ " was created!");

    }

    private String saveEncodedFile(File fileToBeEncoded, byte[] asciiListAsByteArray) {

        String filename = fileToBeEncoded.getName().contains(".")
                ? fileToBeEncoded.getName().replace(".txt", ".cod")
                : fileToBeEncoded.getName() + ".cod";

        String filePath = fileToBeEncoded
                .getAbsolutePath()
                .substring(0, (fileToBeEncoded.getAbsolutePath().length() - fileToBeEncoded.getName().length()))
                .replace("base", "encoded");

        try{
            fileHandler.createAndWriteToFile(
                    filename,
                    filePath,
                    asciiListAsByteArray
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }

    private byte[] convertASCIIListToByteArray(List<String> encodedASCII) {

        String joinedString = encodedASCII
                .stream()
                .reduce(String::concat)
                .get();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        String[] str = joinedString.split("(?<=\\G.{8})");

        for(String cur : str) {
            byte b = 0b00000000;
            for(int i = 0; i < cur.length(); i++) {
                if(cur.charAt(i) == '1') {
                    b = (byte) ((0b1 << (7-i)) | b);
                }
            }
            buffer.write(Byte.toUnsignedInt(b));
        }

        return buffer.toByteArray();
    }

    private List<String> encodeASCIIchars(List<Integer> charsAsASCII, Encoder encoder) {

        List<String> encodedASCIIchars =  charsAsASCII
                .stream()
                .map(encoder::encode)
                .collect(Collectors.toList());

        encodedASCIIchars.add(0,StringUtils.getByteFromNumber(encoder.getCode()   ));
        encodedASCIIchars.add(1,StringUtils.getByteFromNumber(encoder.getDivider()));

        return encodedASCIIchars;
    }

    private List<Integer> convertAllCharsToASCII(String fileAsText) {
        return fileAsText
                .chars()
                .mapToObj(c -> (char) c)
                .map(character -> (int) character)
                .collect(Collectors.toList());
    }

    private byte[] getFileAsBytes(File fileToBeEncoded) {
        try {
            return fileHandler.getAsByteArray(fileToBeEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileAsText(File fileToBeEncoded) throws IOException{
        return fileHandler.getAsText(fileToBeEncoded);
    }

    private File determineFile() {
        return fileHandler.getFileFromDirectory("/tga-1/baseFiles", ".txt", true);
    }

    private Encoder determineEncoder(){
        return EncoderFactory.create(menu.determineEncoder());
    }

}
