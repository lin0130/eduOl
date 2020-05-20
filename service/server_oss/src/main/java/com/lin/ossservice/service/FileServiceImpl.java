package com.lin.ossservice.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lin.ossservice.until.ConstantPropertiesUntil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUntil.END_POINT;
        String accessKeyId = ConstantPropertiesUntil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUntil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUntil.BUCKET_NAME;



        //获取上传文件输入流
        try {
            //创建oss对象
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();

            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName,fileName,inputStream);

            ossClient.shutdown();

            //把上传之后文件的路径返回
            //需要上传到阿里云oss路径手动拼接出来
            String url = "https://"+bucketName+"."+endPoint+"/"+fileName;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
