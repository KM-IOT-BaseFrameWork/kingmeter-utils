package com.kingmeter.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;


public class FileUtils {

    private static FileUtils instance;

    private FileUtils() {}

    public static FileUtils getInstance() {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new FileUtils();
                }
            }
        }
        return instance;
    }

    public String ReadFile(String Path) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = "";
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
        }
        return sb.toString();
    }

    public void WriteFile(String str, String Path) {
        StringBuffer sb = new StringBuffer();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Path);
            out.write(str.getBytes("UTF-8"));
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
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            sb = result.toString("UTF-8");
            is.close();
            result.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

}
