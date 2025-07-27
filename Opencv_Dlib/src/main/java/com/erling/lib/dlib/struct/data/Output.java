package com.erling.lib.dlib.struct.data;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;



@Structure.FieldOrder({"buffer", "size"})
public class Output extends Structure {
    public Pointer buffer;  // 字节数据指针
    public  int size;       // 数据长度

    public byte[] getBuffer() {
        return buffer.getByteArray(0, size);
    }
}