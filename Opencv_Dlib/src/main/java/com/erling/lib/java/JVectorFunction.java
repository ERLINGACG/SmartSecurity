package com.erling.lib.java;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface JVectorFunction extends Library {
    Pointer DnnOutputTemp_Create(int type);
    void DnnOutputTemp_Insert(Pointer temp,Pointer data,int type);

    int DnnOutputTemp_getSize(Pointer temp,int type);

    Pointer DnnOutputTemp_getData(Pointer temp,int type,int index);


    Pointer  DnnFaceOutput_Create();
    void DnnFaceOutput_Insert(Pointer temp,int faceIndex,byte[] data,int size);
    void DnnFaceOutput_GetData(
            Pointer temp,              // DnnFaceOutput 对象指针
            int index,                 // 索引值
            IntByReference faceIndex,  // 输出：人脸索引
            PointerByReference data,   // 输出：数据指针
            IntByReference size        // 输出：数据大小
    );


}
