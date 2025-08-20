package com.erling.lib.opencv.struct.param;

import com.sun.jna.Structure;
import lombok.Getter;
import lombok.Setter;

import java.sql.Struct;

/**
 * struct  FaceFeatureParam{
 *            const char* facenet_path;
 *            bool isCuda;
 *            ~FaceFeatureParam()=default;
 *        };
  */
@Getter
@Setter
@Structure.FieldOrder({"facenet_path", "isCuda"})
public class FaceFeatureParam extends Structure {
    public String facenet_path;
    public boolean isCuda;
}
