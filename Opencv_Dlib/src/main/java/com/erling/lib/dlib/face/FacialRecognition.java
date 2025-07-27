package com.erling.lib.dlib.face;

import com.erling.lib.dlib.struct.data.Output;
import com.erling.lib.dlib.struct.param.FaceNew;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface FacialRecognition extends Library {
    Pointer createFacialRecognition(FaceNew faceNew);

    void getDetection(Pointer facialRecognition, byte[] img, int length, Output output);
    double getDistance(Pointer facialRecognition, byte[] by1,int length1, byte[] by2,int length2);
}
