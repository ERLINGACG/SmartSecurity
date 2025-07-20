package com.erling.controller.opencv.utils;

import com.erling.service.opencv.utils.UtilsServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/utils/Test")
public class UtilsControllerTest {

    UtilsServiceTest  utilsServiceTest;
    @Autowired
    public UtilsControllerTest(UtilsServiceTest utilsServiceTest) {
        this.utilsServiceTest = utilsServiceTest;
    }
    @PostMapping("/test1")
    public ResponseEntity<byte[]> test1(@RequestBody MultipartFile file) throws Exception {
        return ResponseEntity.
                status(200).
                contentType(MediaType.IMAGE_JPEG).
                body(utilsServiceTest.Test1(file.getBytes()));
    }

}
