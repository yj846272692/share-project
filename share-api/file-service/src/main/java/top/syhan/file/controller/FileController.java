package top.syhan.file.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.syhan.file.common.ResponseResult;
import top.syhan.file.service.FileService;
import top.syhan.file.util.FileResource;

import javax.annotation.Resource;

/**
 * @description:
 * @author: syhan
 * @create: 2022-09-28
 **/

@RestController
@RequestMapping(value = "/files")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @Resource
    private FileResource fileResource;

    @PostMapping(value = "/upload")
    public ResponseResult uploadFile(MultipartFile file) {
        // 声明图片的地址路径，返回到前端
        String path = null;
        // 判断文件不能为空
        if (file != null) {
            // 获得文件上传的名称
            String fileName = file.getOriginalFilename();
            log.info(fileName);
            //调用上传服务，得到上传后的新文件名
            path = fileService.uploadFile(file);
        }
        if (StringUtils.isNotBlank(path)) {
            //拼接上服务器地址前缀，得到最终返回给前端的url
            path = fileResource.getOssHost() + path;
            log.info(path);
        }
        return ResponseResult.success(path);
    }
}

