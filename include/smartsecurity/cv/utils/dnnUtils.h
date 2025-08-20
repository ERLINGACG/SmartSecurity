//
// Created by HP on 2025/8/20.
//

#ifndef SMARTSECURITYCORELIB_DNN_UTILS_H
#define SMARTSECURITYCORELIB_DNN_UTILS_H
#include "smartsecurity/cv/cvexport.h"
using namespace std;
namespace cv_utils::dnn{

    class DnnUtils{
        public:
           static void getFaceFeatureMat(const data::FaceFeatureByte* faceFeatureByte, cv::Mat& faceFeatureMat);
    };
}
#endif //SMARTSECURITYCORELIB_DNN_UTILS_H
