package com.sky.utils;


import com.azure.storage.blob.*;
import com.azure.storage.common.StorageSharedKeyCredential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

@Data
@AllArgsConstructor
@Slf4j
public class AzureBlobUtil {

    private String accountName;
    private String accountKey;
    private String containerName;

    /**
     * 文件上传
     *
     * @param stream
     * @param objectName
     * @return
     */
    public String upload(InputStream stream, String objectName) {
        log.info(objectName);
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName);
        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();
        BlobContainerClient containerClient = storageClient.getBlobContainerClient(containerName);
        containerClient.createIfNotExists();
        BlobClient blobClient = containerClient.getBlobClient(objectName);
        blobClient.upload(stream);
        String accountUrl = containerClient.getAccountUrl();
        log.info(accountUrl);
        //文件访问路径规则 https://accountName.blob.core.windows.net/containerName/objectName
        StringBuilder stringBuilder = new StringBuilder(accountUrl);
        stringBuilder
                .append("/")
                .append(containerName)
                .append("/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder);

        return stringBuilder.toString();

    }

}
