package com.erling.service.opencv.utils;

import com.erling.lib.opencv.instance.Instance;
import com.erling.lib.opencv.struct.output.ImageData;
import com.erling.lib.opencv.utils.Utils;
import com.sun.jna.ptr.IntByReference;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceTest {
    Utils utils;
    public UtilsServiceTest() {
        utils = Instance.OPENCV_4120_UTILS.getInstance();
    }
    public byte[] Test1(byte[] input){
        long startTime = System.currentTimeMillis();  // 新增：记录开始时间
        ImageData OutPutData = new ImageData();
        utils.ResizeWithPadding(
                input,
                input.length,
                400,
                400,
                 OutPutData
         );
        long endTime = System.currentTimeMillis();  // 新增：记录结束时间
        System.out.println("Test1 执行耗时: " + (endTime - startTime) + "ms");
        return OutPutData.getDataBuffer();
    }
}
