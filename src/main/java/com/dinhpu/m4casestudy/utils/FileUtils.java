package com.dinhpu.m4casestudy.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static boolean isFileEmpty(MultipartFile[] files){
        boolean check=true;

        for (MultipartFile file: files){
            if (!file.getOriginalFilename().equals("")){
                check=false;
                break;
            }
        }

        return check;
    }
}
