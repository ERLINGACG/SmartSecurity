package com.erling.lib.opencv.struct.param;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 *
 * struct EncodeParam{
 *         int qos;     // 质量
 *         char* type; // 编码类型
 *         ~EncodeParam()=default;
 *     };
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Structure.FieldOrder({"qos", "type"})
public class EncodeParam extends Structure {
    public int qos;
    public String type;
}
