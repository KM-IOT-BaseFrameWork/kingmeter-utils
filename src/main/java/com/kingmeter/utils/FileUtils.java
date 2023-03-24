package com.kingmeter.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class FileUtils {

    private static FileUtils instance;

    private FileUtils() {}

    public static synchronized FileUtils getInstance() {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new FileUtils();
                }
            }
        }
        return instance;
    }

    public String ReadFile(String path) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(path);
            inputStreamReader =
                    new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public void WriteFile(String str, String path) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getContentFromMultipartFile(MultipartFile file) {
        String sb = "";
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            InputStream is = file.getInputStream();
            int n = 1024;
            byte buffer[] = new byte[n];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            sb = new String(buffer);
            is.close();
            result.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return sb;
    }

}
