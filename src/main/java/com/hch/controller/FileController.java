package com.hch.controller;

import com.hch.pojo.Constants;
import com.hch.pojo.ErrorEnum;
import com.hch.config.CustomProperties;
import com.hch.pojo.response.CommonResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public CommonResponse<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        // 在执行这段打印前会生成临时文件,以我的mac为例：/var/folders/xw/zffp6wkx0fvfq7vhbyzwh7fr0000gn/T/tomcat...
        System.err.println(this.getClass() + " Uploading file begins,file name: " + multipartFile.getOriginalFilename());

        CommonResponse<String> response = new CommonResponse<>(ErrorEnum.SUCCESS);
        // File file = new File(Constants.OUTPUT_PATH,
        //         Optional.ofNullable(multipartFile.getOriginalFilename()).orElse("file_" + System.currentTimeMillis()));
        // File parentDir = file.getParentFile();
        //
        // if (!parentDir.exists()) {
        //     parentDir.mkdirs();
        // }
        // byte[] bytes = new byte[1024 * 8];
        // try (InputStream inputStream = multipartFile.getInputStream();  // java.io.FileInputStream
        //      BufferedInputStream in = new BufferedInputStream(inputStream);
        //      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
        //     int r;
        //     while ((r = in.read(bytes)) != -1) {
        //         out.write(bytes, 0, r);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     response = new CommonResponse<>(ErrorEnum.UNKNOWN_ERROR);
        // }
        return response;
    }

    @ApiOperation("servlet file")
    @RequestMapping(value = "/tradition/file", method = RequestMethod.POST)
    public CommonResponse<String> receiveFile(HttpServletRequest request) throws IOException {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
        System.out.println();
        try (
                // BufferedReader reader = request.getReader();
                ServletInputStream inputStream = request.getInputStream();
                BufferedInputStream in = new BufferedInputStream(inputStream);
        ) {
            byte[] buf = new byte[8192];
            int count;
            StringBuilder stringBuilder = new StringBuilder();
            while ((count = in.read(buf)) != -1) {
                stringBuilder.append(new String(buf, 0, count));
            }
            System.out.println(stringBuilder);
        }
        return new CommonResponse<>(ErrorEnum.SUCCESS);
    }

    // @ApiOperation("download file")
    // @GetMapping("file/download")
    // public
}
