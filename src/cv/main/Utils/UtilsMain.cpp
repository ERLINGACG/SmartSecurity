#include "smartsecurity/cv/cvexport.h"


extern "C" {
    CORE_CV_API void ResizeWithPadding(
        unsigned char* input, 
        int inputSize,
        int targetWidth, int targetHeight,
        data::ImageData&  outPutData
    ){
    
        cv_utils::ImageUtils::resizeWithPadding(
            input,
            inputSize,
            targetWidth,
            targetHeight,
            outPutData
        );
        
    }
}