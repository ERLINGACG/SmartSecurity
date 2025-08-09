package com.erling.lib.opencv.struct.output;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import lombok.Getter;

@Getter
@Structure.FieldOrder({"width", "height", "channels", "size","data"})
public class ImageData extends Structure {
    // 对应C++的int类型
    public int width;
    public int height;
    public int channels;
    public int size;
    public Pointer data;

    // 可选：添加内存访问辅助方法
    public byte[] getDataBuffer() {
        return data.getByteArray(0, size);
    }
}