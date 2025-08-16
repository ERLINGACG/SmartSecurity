#ifndef _EDGEPARAM_H_
#define _EDGEPARAM_H_
#include "smartsecurity/cv/cvexport.h"

namespace cv_edge{
     namespace param{
        struct EdgeParam{

            int             gauss_size;     //高斯核大小
            double          sigmaX     ;          // x方向标准差
            int             ksize      ; 
            double          scale      ;   // 缩放因子
            double          delta      ;   // 增量值 
            int             imageqos   ;   // 图像质量
            const char*     imageCode  ;
        };
     };
};

#endif // _EDGEPARAM_H_