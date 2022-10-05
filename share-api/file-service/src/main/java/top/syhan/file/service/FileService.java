package top.syhan.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: syhan
 * @create: 2022-09-28
 **/

public interface FileService {
    /**
     * 上传文件到OSS
     *
     * @param file 文件对象
     * @return 上传后的url
     */
    String uploadFile(MultipartFile file);
}

