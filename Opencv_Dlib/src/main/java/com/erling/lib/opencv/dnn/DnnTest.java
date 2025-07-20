package com.erling.lib.opencv.dnn;

import com.erling.lib.opencv.instance.Instance;
import com.erling.lib.opencv.struct.output.ImageData;
import com.sun.jna.Pointer;

public class DnnTest {

    static DnnDetector dnnDetector= Instance.OPENCV_4120_DNN.getInstance();;
    static Pointer netClass=dnnDetector.createDnnDetector("lib/x64/debug/best2.onnx",0.5,0.5);

    static public byte[] DnnD1(byte[] image){
        ImageData data=new ImageData();
        dnnDetector.DnnDetectorYolo(netClass,image,image.length,data);
        return data.getDataBuffer();

    }
}
