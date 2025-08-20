package com.erling.lib.opencv.struct.param;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 原始结构体结构
 * struct  FaceParam{
             std::string caffemodel_path;
             std::string prototxt_path;
             bool isCuda;

             ~FaceParam()=default;
         };
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Structure.FieldOrder({"caffemodel_path", "prototxt_path", "isCuda"})
public class FaceParam extends Structure {
    public String caffemodel_path;
    public String prototxt_path;
    public boolean isCuda;
}
