package com.erling.lib.java;

import com.erling.lib.instance.LibraryAnn;
import com.erling.lib.instance.Load;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class JVTest2 {

    @LibraryAnn(
            WindowsPath = ".\\lib\\x64\\debug\\SmartSecurityCoreLibTest"
    )
    interface Jv extends JVectorFunction{}
    public static void main(String[] args) {
        Jv jv= Load.loading(Jv.class);

        // 准备输出参数
        IntByReference faceIndexRef1 = new IntByReference();
        PointerByReference dataRef1 = new PointerByReference();
        IntByReference sizeRef1 = new IntByReference();

        IntByReference faceIndexRef2 = new IntByReference();
        PointerByReference dataRef2 = new PointerByReference();
        IntByReference sizeRef2 = new IntByReference();

        byte[] bytes=new byte[4];  //随便填充点啥
        bytes[0]=1;
        bytes[1]=2;
        bytes[2]=3;
        bytes[3]=4;
        byte[] bytes2=new byte[4];
        bytes2[0]=5;
        bytes2[1]=6;
        bytes2[2]=7;
        bytes2[3]=8;
        long start=System.currentTimeMillis();
        Pointer temp=jv.DnnFaceOutput_Create();
        jv.DnnFaceOutput_Insert(temp,0,bytes,4);
        jv.DnnFaceOutput_Insert(temp,1,bytes2,4);
        jv.DnnFaceOutput_GetData(temp,0,faceIndexRef1,dataRef1,sizeRef1);
        jv.DnnFaceOutput_GetData(temp,1,faceIndexRef2,dataRef2,sizeRef2);
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");
        // 获取结果值
        int faceIndex1 = faceIndexRef1.getValue();
        int dataSize1 = sizeRef1.getValue();
        // 获取数据指针
        Pointer dataPointer1 = dataRef1.getValue();

        Pointer dataPointer2 = dataRef2.getValue();
        int faceIndex2 = faceIndexRef2.getValue();
        int dataSize2 = sizeRef2.getValue();

        byte[] dataBytes1 = dataPointer1.getByteArray(0, dataSize1);
        byte[] dataBytes2 = dataPointer2.getByteArray(0, dataSize2);
        System.out.println(dataBytes1.length);
        System.out.println(dataBytes2.length);
        for (byte dataByte : dataBytes1) {
                System.out.println(dataByte);

        }
        for (byte dataByte : dataBytes2) {
            System.out.println(dataByte);
        }
        
    }
}
