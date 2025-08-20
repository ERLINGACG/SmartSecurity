package com.erling.lib.java;

import com.erling.lib.instance.LibraryAnn;
import com.erling.lib.instance.Load;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.util.ArrayList;
import java.util.List;

public class JVTest {
    @LibraryAnn(
            WindowsPath = ".\\lib\\x64\\debug\\SmartSecurityCoreLibTest"
    )
    interface Jv extends JVectorFunction{}

    public static void main(String[] args) {
        try{
            Jv jv = Load.loading(Jv.class);
            List<Float> list = new ArrayList<Float>();
            Long start = System.currentTimeMillis();
            Pointer v1= new Memory(4);
            Pointer v2= new Memory(4);
            Pointer v3= new Memory(4);
            Pointer v4= new Memory(4);
            Pointer v5= new Memory(4);
            // 要写入的浮点数
            float value1 = 3.14159f;
            float value2 = 2.71828f;
            float value3 = 1.61803f;
            float value4 = 0.57721f;
            float value5 = 1.41421f;

            // // 方法1：使用 setFloat() - 最简洁
            v1.setFloat(0, value1);  // 参数1：内存偏移量 (从0开始)
            v2.setFloat(0, value2);
            v3.setFloat(0, value3);
            v4.setFloat(0, value4);
            v5.setFloat(0, value5);
            Pointer temp = jv.DnnOutputTemp_Create(0);
            jv.DnnOutputTemp_Insert(temp, v1, 0);
            jv.DnnOutputTemp_Insert(temp, v2, 0);
            jv.DnnOutputTemp_Insert(temp, v3, 0);
            jv.DnnOutputTemp_Insert(temp, v4, 0);
            jv.DnnOutputTemp_Insert(temp, v5, 0);
            for(int i=0;i<jv.DnnOutputTemp_getSize(temp,0);++i){
                Pointer result = jv.DnnOutputTemp_getData(temp, 0, i);
                list.add(result.getFloat(0));
            }
            Long end = System.currentTimeMillis();
            System.out.println("耗时："+(end-start)+"ms");
            System.out.println(list);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
