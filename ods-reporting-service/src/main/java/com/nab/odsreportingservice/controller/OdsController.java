package com.nab.odsreportingservice.controller;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlobInputStream;
import com.azure.storage.blob.specialized.BlockBlobClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;

@RestController
public class OdsController {

//    @PostConstruct
//    public void test() {
//
//        // Create a BlobServiceClient object which will be used to create a container client
//        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString("AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;").buildClient();
//
////Create a unique name for the container
//        String containerName = "ods";
//
//// Create the container and return a container client object
//        BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);
//
//        BlobClient blobClient = containerClient.getBlobClient("test.json");
//
//        System.out.println(blobClient);
//    }

    @Value("azure-blob://ods/test.json")
    private Resource blobFile;

    @GetMapping("/report")
    public String readBlobFile() throws IOException {
        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString("AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;").buildClient();

//Create a unique name for the container
        String containerName = "ods";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        BinaryData content = containerClient.getBlobClient("test.json").downloadContent();
        return  content.toString();
    }

    @PostMapping("/report")
    public String writeBlobFile(@RequestBody String data) throws IOException {
        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString("AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;").buildClient();

//Create a unique name for the container
        String containerName = "ods";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        BlobClient blobClient = containerClient.getBlobClient("test.json");
        String dataSample = "Blah blah blah";
        blobClient.upload(BinaryData.fromString(dataSample), true);

        return "hello";
    }

}