package com.sky.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.sky.service.CommonService;
import com.sky.utils.AzureBlobUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CommonServiceImpl implements CommonService {

    private final AzureBlobUtil azureBlobUtil;

    public CommonServiceImpl(AzureBlobUtil azureBlobUtil) {
        this.azureBlobUtil = azureBlobUtil;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String extName = FileNameUtil.extName(multipartFile.getOriginalFilename());
            String newName = IdUtil.fastSimpleUUID() + "." + extName;
            return azureBlobUtil.upload(inputStream, newName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
