package com.erling.lib.opencv.edge;

import com.erling.lib.opencv.struct.output.ImageData;
import com.erling.lib.opencv.struct.param.EdgeParam;
import com.sun.jna.Library;
import com.sun.jna.Pointer;


public interface EdgeDetection extends Library {
    Pointer createEdgeDetection();
    void destroyEdgeDetection(Pointer ptr);

    void SodelDetection(
            Pointer ptr,
            EdgeParam param,
            byte[] inputImg,
            int size,
            ImageData OutPutData);
}
