package com.erling.entity.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMember {
    int mid;                     //成员id
    int groupId;                 //群组id
    String memberName;           //成员名称
    String memberEmail;          //成员邮箱
    String memberIdentity;       //成员身份
    String memberGender;         //成员性别
    String memberDescription;    //成员描述
    byte[] memberFeature;        //成员特征
}
