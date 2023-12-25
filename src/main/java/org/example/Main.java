package org.example;


import com.aparapi.Kernel;
import com.aparapi.Range;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



public class Main {
    public static void main(String[] args) {

        String in = "dwqdw qkkdwqdkw pqkdkqwk pdpwkqdk qwk dkqwk pp qk dwod " +
                "iowq djqw jdioqwjo odiwqjiod oqwi odwHFH IOHQiofh woh qofh ph ofh ohoh o Hh@* H*(HQWHf9qwh fh892 h*(H@*" +
                "(H fuwhf89hw9qfh hqwio WOfoqf pjfhsopfo shofh oHIOH QWOhh239howhf89qwhfh89h2hf8qwhfh0129 qwopdfjqwjfqwjop wdp";


        String filePath = "C:\\Users\\hdnhd\\IdeaProjects\\test\\src\\main\\java\\testFiles\\file4.7z";

        byte[] fileBytes;
        try {
            Path path = Paths.get(filePath);
            fileBytes = Files.readAllBytes(path);

            System.out.println("File read successfully. Byte array length: " + fileBytes.length  + " bytes");


            byte[] resCompres = compress(fileBytes);
            byte[] resDeCompres = decompress(resCompres);

            System.out.println(">>> "  + resCompres.length);
            System.out.println(">>> "  + resDeCompres.length);
            System.out.println(">>> " + fileBytes.length);




        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }



    }



    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream compressedData = new ByteArrayOutputStream();

        for (int i = 0; i < data.length; i++) {
            byte currentByte = data[i];
            int count = 1;

            while (i + 1 < data.length && data[i + 1] == currentByte && count < 127) {
                count++;
                i++;
            }

            compressedData.write((byte) count);
            compressedData.write(currentByte);
        }

        return compressedData.toByteArray();
    }

    public static byte[] decompress(byte[] compressedData) {
        ByteArrayOutputStream decompressedData = new ByteArrayOutputStream();

        for (int i = 0; i < compressedData.length; i += 2) {
            int count = compressedData[i] & 0xFF;
            byte currentByte = compressedData[i + 1];

            for (int j = 0; j < count; j++) {
                decompressedData.write(currentByte);
            }
        }

        return decompressedData.toByteArray();
    }
}
