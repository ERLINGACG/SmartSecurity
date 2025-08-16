//
// Created by HP on 2025/8/15.
//

#ifndef SMARTSECURITYCORELIB_DNN_DETECTION_FACE_H
#define SMARTSECURITYCORELIB_DNN_DETECTION_FACE_H
#include "smartsecurity/cv/dnn/dnnBasicClass.h"
#include "smartsecurity/cv/param/dnnParam.h"
namespace cv_dnn::face{
class DnnDetectorFace : public cv_dnn::dnnBasic::DnnBasicClass{
    public:
        DnnDetectorFace(const char* Yolo_path, bool isCUDA, double confThreshold, double nmsThreshold);
        explicit DnnDetectorFace(cv_dnn::param::face::faceParam* param); //重写构造函数
        ~DnnDetectorFace() override =default;
        void DetectImage(unsigned char* inputData, int size, data::ImageData& OutputData) override;
    };
}

#endif //SMARTSECURITYCORELIB_DNN_DETECTION_FACE_H
