package com.erling.lib.opencv.struct.param;

import com.sun.jna.Structure;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Structure.FieldOrder({"gauss_size", "sigmaX", "ksize", "scale", "delta", "imageqos", "imageCode"})
public class EdgeParam extends Structure {
    public int gauss_size;  // 高斯核大小
    public double sigmaX;   // 高斯核标准差
    public int ksize;       // Sobel算子大小
    public double scale;    // 缩放比例
    public double delta;    // 阈值
    public int imageqos;    // 图像质量
    public String imageCode;// 图像编码
}