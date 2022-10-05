package top.syhan.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.syhan.file.service.FileService;
import top.syhan.file.util.AliyunResource;
import top.syhan.file.util.FileResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description:
 * @author: syhan
 * @create: 2022-09-28
 **/

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private AliyunResource aliyunResource;

    @Resource
    private FileResource fileResource;

    @Override
    public String uploadFile(MultipartFile file) {
        // 读入配置文件信息
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();

        String endpoint = fileResource.getEndPoint();
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileName = file.getOriginalFilename();
        // 分割文件名，获得文件后缀名
        assert fileName != null;
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        //拼接得到新的上传文件名
        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID() + "." + suffix;
        // 上传网络需要用的字节流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            System.err.println("上传文件出现异常");
        }
        //执行阿里云上传文件操作
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        // 关闭OSSClient
        ossClient.shutdown();
        return uploadFileName;
    }
}
