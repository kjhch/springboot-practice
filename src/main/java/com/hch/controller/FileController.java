package com.hch.controller;

import com.hch.pojo.ErrorEnum;
import com.hch.config.CustomProperties;
import com.hch.pojo.response.CommonResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@RestController
public class FileController {
    @Autowired
    private CustomProperties customProperties;
    // @Autowired
    // private SessionFactory sessionFactory;
    // @Autowired
    // private DataSource dataSource;
    //
    // @RequestMapping(value = "hello", method = RequestMethod.POST)
    // public CommonResponse<Object> hello() {
    //     // try (Session session = sessionFactory.openSession()) {
    //     //     log.info("sessionFactory:{}, dataSource:{}", sessionFactory, dataSource);
    //     //     List result = session.createNativeQuery("select * from tb_user;").getResultList();
    //     //     log.info("result: {}", result);
    //     // }
    //     Session session = sessionFactory.openSession();
    //     Session currentSession = sessionFactory.getCurrentSession();
    //     log.info("sessionFactory:{}, dataSource:{}", sessionFactory, dataSource);
    //     log.info("getCurrentSession:{}, openSession:{}",currentSession,session);
    //     currentSession.createNativeQuery("select * from tb_user;").getResultList();
    //     // session.createNativeQuery("select * from tb_user;").getResultList();  // 未关闭session会造成连接泄漏
    //     return new CommonResponse<>();
    // }

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

    // @ApiOperation("download file")
    // @GetMapping("file/download")
    // public
}
