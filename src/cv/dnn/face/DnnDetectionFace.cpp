//
// Created by HP on 2025/8/15.
//
#include "smartsecurity/cv/dnn/dnnDetectionFace.h"

using namespace  cv_dnn::face;
DnnDetectorFace::DnnDetectorFace(const char* Yolo_path, bool isCUDA, double confThreshold, double nmsThreshold){
        this->net=cv::dnn::readNetFromONNX(Yolo_path);
        if(isCUDA){
            this->net.setPreferableBackend(cv::dnn::DNN_BACKEND_CUDA);
            this->net.setPreferableTarget(cv::dnn::DNN_TARGET_CUDA);
        }
}
DnnDetectorFace::DnnDetectorFace(cv_dnn::param::face::faceParam* param){
    this->net=cv::dnn::readNetFromONNX(param->modelPath);
    if(param->isCuda){
        this->net.setPreferableBackend(cv::dnn::DNN_BACKEND_CUDA);
        this->net.setPreferableTarget(cv::dnn::DNN_TARGET_CUDA);
    }
}

void DnnDetectorFace::DetectImage(unsigned char *inputData, int size, data::ImageData &OutputData) {
//    pass
}
