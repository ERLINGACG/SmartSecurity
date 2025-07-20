package com.erling.lib.opencv.utils;

import com.erling.lib.opencv.struct.output.ImageData;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface Utils extends Library {
    void ResizeWithPadding(byte[] inputImg,
                              int size,
                              int targetWidth, int targetHeight,
                              ImageData outPutData
                              );
}
