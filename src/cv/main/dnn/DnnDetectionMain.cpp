#include "smartsecurity/cv/cvexport.h"

using namespace dnnDetection;
using namespace cv_dnn;
extern "C"{
    CORE_CV_API DnnDetectorYolo* createDnnDetector(const char* path, double confidence, double nms){
        return new DnnDetectorYolo(path, true, confidence, nms);
    }

}

extern "C"{
    CORE_CV_API void DnnDetectorYolo(DnnDetectorYolo* detector,
                                     unsigned char* inputData, int size,
                                     data::ImageData& OutputData)
    {
        detector->DetectImage(inputData,size,OutputData);
    }
}