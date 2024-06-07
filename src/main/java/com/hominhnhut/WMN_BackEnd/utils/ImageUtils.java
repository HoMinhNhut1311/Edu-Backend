package com.hominhnhut.WMN_BackEnd.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {


    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }



    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    public static File convertToFile(byte[] bytes, String filePath) throws IOException {
        FileOutputStream fos = null;
        File file = new File(filePath);
        try {
            // Tạo một đối tượng FileOutputStream để ghi dữ liệu vào tệp
            fos = new FileOutputStream(file);
            // Ghi dữ liệu từ mảng byte vào tệp
            fos.write(bytes);
        } finally {
            // Đảm bảo đóng luồng sau khi hoàn thành
            if (fos != null) {
                fos.close();
            }
        }
        return file;
    }

    public static String toBase64(String filePath) throws IOException {
        System.out.println(filePath);
        byte[] byteImage = Files.readAllBytes(new File(filePath).toPath());
        return Base64.getEncoder().encodeToString(byteImage);
    }

}