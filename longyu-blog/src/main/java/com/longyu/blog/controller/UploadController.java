package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    /**
     * 坑！！！不用@RequestParam，前后端命名必须一致
     */
    public R<String> upload(MultipartFile img) {
        String url = ossService.upload(img);
        return R.ok(url);
    }

}
