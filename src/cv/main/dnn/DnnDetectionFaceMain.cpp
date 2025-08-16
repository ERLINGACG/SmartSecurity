//
// Created by HP on 2025/8/15.
//
#include "smartsecurity/cv/cvexport.h"
using  namespace cv_dnn::face;

extern "C"{
    DnnDetectorFace* DnnDetectorFaceCreate(const char* Yolo_path, bool isCUDA, double confThreshold, double nmsThreshold){
        return new DnnDetectorFace(Yolo_path,isCUDA,confThreshold,nmsThreshold);
    }
}