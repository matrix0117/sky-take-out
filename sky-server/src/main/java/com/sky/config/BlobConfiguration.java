package com.sky.config;

import com.sky.properties.AzureBlobProperties;
import com.sky.utils.AzureBlobUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BlobConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AzureBlobUtil azureBlobUtil(AzureBlobProperties azureBlobProperties) {
        return new AzureBlobUtil(azureBlobProperties.getAccountName(),
                azureBlobProperties.getAccountKey(), azureBlobProperties.getContainerName());
    }

}
