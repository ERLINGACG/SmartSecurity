//
// Created by HP on 2025/8/19.
//

#ifndef SMARTSECURITYCORELIB_ENCODE_PARAM_H_
#define SMARTSECURITYCORELIB_ENCODE_PARAM_H_
namespace cv_param{
    struct EncodeParam{
        int qos;     // 质量
        char* type; // 编码类型

        ~EncodeParam()=default;
    };
}
#endif //SMARTSECURITYCORELIB_ENCODE_PARAM_H_
