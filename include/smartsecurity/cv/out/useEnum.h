//
// Created by HP on 2025/8/18.
//

#ifndef SMARTSECURITYCORELIB_USE_ENUM_H
#define SMARTSECURITYCORELIB_USE_ENUM_H
namespace cv_dnn::out::Enum{
    enum useEnum{
        Float    = 0,  // 单精度浮点数
        Int      = 1,  // 整数
        Double   = 2,  // 双精度浮点数
        Char     = 3,  // 字符
        UChar    = 4,  // 无符号字符
        Short    = 5,  // 短整数
        Long     = 6,  // 长整数
        LongLong = 7,  // 长长整数
        Pointer  = 8,  // 指针
        String   = 9,  // 字符串
        Bool     = 10,  // 布尔值
    };
}
#endif //SMARTSECURITYCORELIB_USE_ENUM_H
