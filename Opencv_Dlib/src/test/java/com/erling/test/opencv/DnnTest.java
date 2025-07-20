package com.erling.test.opencv;

import com.erling.lib.opencv.dnn.DnnDetector;
import com.erling.lib.opencv.instance.LibraryAnn;
import com.erling.lib.opencv.instance.Load;
import com.erling.lib.opencv.struct.output.ImageData;
import com.sun.jna.Pointer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

public class DnnTest {

    @LibraryAnn(
            WindowsPath = "E:\\SmartSecurity\\SmartSecurityWeb\\lib\\x64\\debug\\SmartSecurityCoreLibTest"
    )
    interface Dnn extends DnnDetector{}
    public Load load = new Load(Dnn.class);

    @Test
    public void testDnnDetector() throws Exception {
        // 在测试方法中按顺序初始化
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        Dnn dnn = load.loading();
        if (dnn == null) {
            throw new IllegalStateException("DNN实例初始化失败");
        }
        Pointer net = dnn.createDnnDetector(
                "E:\\SmartSecurity\\SmartSecurityWeb\\lib\\x64\\debug\\best2.onnx",
                0.5,0.5);

        // 读取图片文件
        File imageFile = new File("E:\\SmartSecurity\\SmartSecurityWeb\\lib\\example\\example1.jpg");
        if (!imageFile.exists()) {
            throw new FileNotFoundException("图片文件不存在: " + imageFile.getAbsolutePath());
        }
        byte[] imgBytes = Files.readAllBytes(imageFile.toPath());

        // 调用检测方法

        for(int i=0;i<10;i++){
            ImageData imageData = new ImageData();
            dnn.DnnDetectorYolo(net, imgBytes, imgBytes.length, imageData);
        }

    }
}
