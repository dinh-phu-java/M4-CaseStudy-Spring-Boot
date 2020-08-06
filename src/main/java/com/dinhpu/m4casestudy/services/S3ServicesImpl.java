package com.dinhpu.m4casestudy.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ServicesImpl implements S3Services{
    private Logger logger = LoggerFactory.getLogger(com.dinhpu.m4casestudy.services.S3ServicesImpl.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${jsa.s3.bucket}")
    private String bucketName;

    @Override
    public void uploadFile(String keyName, String uploadFilePath) {
        try {

            File file = new File(uploadFilePath);
            s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
            logger.info("===================== Upload File - Done! =====================");

        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }
    }

    @Override
    public void deleteFile(String keyName) {
        logger.info("Deleting file with name= " + keyName);
        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, keyName);
        s3client.deleteObject(deleteObjectRequest);
        logger.info("File deleted successfully.");
    }
}
