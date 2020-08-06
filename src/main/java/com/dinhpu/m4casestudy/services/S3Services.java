package com.dinhpu.m4casestudy.services;

public interface S3Services {
     void uploadFile(String keyName, String uploadFilePath);
    void deleteFile(final String keyName);
}
