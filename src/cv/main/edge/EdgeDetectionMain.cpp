#include "smartsecurity/cv/cvexport.h"


extern "C"{
    CORE_CV_API cv_edge::EdgeDetection* createEdgeDetection(){
        return new cv_edge::EdgeDetection();
    }
    CORE_CV_API void destroyEdgeDetection(cv_edge::EdgeDetection* edgeDetection){
        delete edgeDetection;
    }
};
extern "C"{
    CORE_CV_API void SodelDetection(
        cv_edge::EdgeDetection* edgeDetection,
        cv_edge::param::EdgeParam* edgeParam,
        unsigned char*  inputImg,
        int             ImgSize,
        data::ImageData& OutputImg
    ){
       edgeDetection->SodelDetector(edgeParam,inputImg, ImgSize, OutputImg);

    }
};