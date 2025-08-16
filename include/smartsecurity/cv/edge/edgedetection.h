#ifndef _EDGEDETECTION_H
#define _EDGEDETECTION_H
#include "smartsecurity/cv/cvexport.h"
#include "smartsecurity/cv/param/edgeParam.h"

namespace cv_edge{
     class EdgeDetection{
          public:
              void SodelDetector(
                param::EdgeParam* param,
                unsigned char*  inputImg,
                int             ImgSize,
                data::ImageData&  outPutData
                
            );
              ~EdgeDetection()=default;
     };
};

#endif //_EDGEDETECTION_H