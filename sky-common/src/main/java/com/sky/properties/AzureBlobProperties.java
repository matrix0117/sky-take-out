package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.cloud.azure.storage.blob")
@Data
public class AzureBlobProperties {

    private String accountName;
    private String accountKey;
    private String containerName;

}
