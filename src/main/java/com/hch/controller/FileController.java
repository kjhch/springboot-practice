package com.hch.controller;

import com.hch.api.ErrorEnum;
import com.hch.config.CustomProperties;
import com.hch.api.response.CommonResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
public class FileController {
    @Autowired
    private CustomProperties customProperties;

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public CommonResponse<Object> hello() {
        return new CommonResponse<>();
    }

    @ApiOperation("FILE")
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public CommonResponse<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        System.err.println("Uploading file begins,file name: " + multipartFile.getOriginalFilename());
        CommonResponse<String> response = new CommonResponse<>();
        File file = new File(Paths.get(System.getProperty("user.home"), "var", customProperties.getProjectName(), "upload").toString(),
                Optional.ofNullable(multipartFile.getOriginalFilename()).orElse("file_" + System.currentTimeMillis()));
        if (!file.exists() && file.getParentFile().isDirectory()) {
            System.err.println("creating dir: " + file.getParentFile().mkdirs());
        }
        byte[] bytes = new byte[1024 * 1024 * 20];
        try (InputStream inputStream = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new CommonResponse<>(ErrorEnum.UNKNOWN_ERROR);
        }
        return response;
    }
}
