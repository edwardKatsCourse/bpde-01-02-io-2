package com.company.theory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        //InputStream
        //OutputStream

        //byte -128..127 -> 01111111
        //int 0..255, no data -> -1
        String path = "C:\\Users\\Edward\\Desktop\\BPDE-01-02 IO 2\\";
        String filename = "google.html";

        URL url = new URL("http://google.com");
        InputStream internetInputStream = url.openStream();

//        System.out.printf("File: [%s] %s%n", file.getName(), file.exists() ? "exists" : "does not exist");
//        if (!file.exists()) {
//            System.out.println("Creating...");
////            Thread.sleep(1500);
//            file.createNewFile();
//        }
//        System.out.printf("File: [%s] %s%n", file.getName(), file.exists() ? "exists" : "does not exist");


        File file = new File(path + filename);
        OutputStream fileOutputStream = new FileOutputStream(file);


        copy(internetInputStream, fileOutputStream);


    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException, InterruptedException {
        byte [] buffer = new byte[1000 * 1024];
        //To know, that there's something left to read:
        //1. inputStream.available() > 0 -> bytes available to read
        //2. inputStream.read() != -1 -> bytes are casted to int (0..255) and -1 means there's
        // nothing left to read (the "broken" byte)

        int offset = 0;
        while (inputStream.available() > 0) {
            int bytesCount = inputStream.read(buffer, offset, buffer.length-offset);
            System.out.printf("Bytes read from InputStream: %s%n", bytesCount);
            //first iteration
            //b[0,0,0,0,0,0,0,0,0,0] - initial
            //b[100,100,0,0,0,0,0,0,0,0]
            //write(b, 0, 2)

            //second iteration
            //b[100,100,0,0,0,0,0,0,0,0] - initial
            //offset 2, length
            // b[100, 100, (!)100, 100, 0,0,0,0,0,0]

            //third iteration
            // b[100, 100, 100, 100, 0,0,0,0,0,0]
            //offset 4, length
            // b[100, 100, 100, 100, (!)100,100,0,0,0,0]
            outputStream.write(buffer, offset, bytesCount);
            Thread.sleep(20_000);
            offset = bytesCount;
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
    public static void closeExample() {
        String filename = "C:\\Users\\Edward\\Desktop\\BPDE-01-02 IO 2\\myFile.txt";
        File file = new File(filename);
        try (OutputStream outputStream = new FileOutputStream(file)) {

            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream.write("This is some plain text".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
