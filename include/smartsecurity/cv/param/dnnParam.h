#ifndef _YOLO_PARAM_H_
#define _YOLO_PARAM_H_

namespace cv_dnn::param::face{

       struct  faceParam{
           std::string modelPath;
           bool isCuda;

           ~faceParam()=default;
       };
}
namespace cv_dnn::param::yolo{
    struct yoloParam{
        std::string modelPath;
        bool isCuda;

        ~yoloParam()=default;
    };
}


#endif // _YOLO_PARAM_H_