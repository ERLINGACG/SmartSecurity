#ifndef _YOLO_PARAM_H_
#define _YOLO_PARAM_H_

namespace cv_dnn::param::face{

       struct  FaceParam{

           char* caffemodel_path;
           char* prototxt_path;
           bool isCuda;

           ~FaceParam()=default;
       };
       struct FaceFeatureParam{
           std::string modelPath;
           bool isCuda;

           ~FaceFeatureParam()=default;
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