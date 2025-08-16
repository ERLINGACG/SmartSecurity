#ifndef _UTILS_H
#define _UTILS_H
#include "smartsecurity/cv/cvexport.h"
namespace cv_utils{
     class ImageUtils{
         public:
             static void resizeWithPadding(
                unsigned char* input, 
                int inputSize,
                int targetWidth, int targetHeight,
                data::ImageData&  outPutData       
            );
            static void resizeWithPadding_mat(
                cv::Mat& input,
                cv::Mat& output,
                int targetWidth, 
                int targetHeight
            );
        
    };
}
#endif