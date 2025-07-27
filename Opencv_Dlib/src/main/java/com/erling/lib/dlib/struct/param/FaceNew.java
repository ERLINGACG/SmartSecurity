package com.erling.lib.dlib.struct.param;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

// 方式1：使用String自动转换（适用于只读字符串）
@Structure.FieldOrder({"predictor_path", "recognition_Path"})
public class FaceNew extends Structure {
    public String predictor_path;    // 对应C++端的const char*
    public String recognition_Path;  // 注意保持大写P与C++结构体一致

    public FaceNew() {
        super();
    }

    public FaceNew(Pointer p) {
        super(p);
        read();
    }
}