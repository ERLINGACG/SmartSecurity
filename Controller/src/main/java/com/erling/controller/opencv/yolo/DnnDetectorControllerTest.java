package com.erling.controller.opencv.yolo;

import com.erling.service.opencv.dnn.DnnDetectorServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/yolo/test")
public class DnnDetectorControllerTest {
    DnnDetectorServiceTest dnnDetectorServiceTest;
    @Autowired
    public DnnDetectorControllerTest(DnnDetectorServiceTest dnnDetectorServiceTest) {
        this.dnnDetectorServiceTest = dnnDetectorServiceTest;
    }

    @PostMapping("/detect")
    public ResponseEntity<byte[]> detect(@RequestParam("image") MultipartFile image) throws IOException {
        byte[] bytes = dnnDetectorServiceTest.detectTest(image.getBytes());
        return ResponseEntity.
                status(HttpStatus.OK).
                contentType(MediaType.IMAGE_JPEG).
                body(bytes);
    }
}
