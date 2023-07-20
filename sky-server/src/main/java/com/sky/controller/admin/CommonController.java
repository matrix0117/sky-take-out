package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.CommonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/common")
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        String url = commonService.upload(file);
        return Result.success(url);
    }
}
