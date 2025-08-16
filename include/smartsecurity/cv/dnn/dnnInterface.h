//
// Created by HP on 2025/8/15.
//

#ifndef SMARTSECURITYCORELIB_DNN_INTERFACE_H
#define SMARTSECURITYCORELIB_DNN_INTERFACE_H
#include "smartsecurity/data/image_data.h"
namespace cv_dnn::interface{
    class DnnInterface{
    public:

        virtual ~DnnInterface()=default;
        virtual void DetectImage(unsigned char* inputData,int size,data::ImageData& OutputData)=0;
    };
}
#endif //SMARTSECURITYCORELIB_DNN_INTERFACE_H
