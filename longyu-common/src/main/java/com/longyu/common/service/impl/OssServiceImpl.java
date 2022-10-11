package com.longyu.common.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.longyu.common.enums.AppHttpCodeEnum;
import com.longyu.common.exception.SystemException;
import com.longyu.common.service.OssService;
import com.longyu.common.util.ConstantPropertiesUtil;
import com.longyu.common.util.PathUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String upload(MultipartFile file) {

        // 通过工具类获取值
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);


        try {
            // 上传文件流
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String fileName = file.getOriginalFilename();

            if (!StringUtils.hasText(fileName)) {
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
            }
            String filePath = PathUtil.generateFilePath(fileName);
            // 创建PutObject请求。
            ossClient.putObject(bucketName, filePath, inputStream);
            return "https://" + bucketName + "." + endpoint + "/" + filePath;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
