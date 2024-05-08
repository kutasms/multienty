package com.chia.multienty.plugin.core.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
    public static List<String> unzip(String zipFilePath, String destDir) {
        List<String> nameList = new ArrayList<>();
        ZipFile zipFile = null;
        try {
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            zipFile = new ZipFile(zipFilePath, StandardCharsets.UTF_8);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            File file, parentFile;
            ZipEntry entry;
            byte[] cache = new byte[1024];

            while (entries.hasMoreElements()) {
                entry = (ZipEntry) entries.nextElement();
                if (entry.isDirectory()) {
                    new File(destDir + entry.getName()).mkdirs();
                    continue;
                }
                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                file = new File(destDir + entry.getName());
                //获取文件名
                String fileName = file.getName();
                nameList.add(fileName);
                parentFile = file.getParentFile();
                if (parentFile != null && (!parentFile.exists())) {
                    parentFile.mkdirs();
                }
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, 1024);
                int readIndex = 0;
                while ((readIndex = bis.read(cache, 0, 1024)) != -1) {
                    fos.write(cache, 0, readIndex);
                }
                bos.flush();
                bos.close();
                fos.close();
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally{
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return nameList;
    }
}
