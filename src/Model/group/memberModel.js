// public class GroupMember {
//     int mid;                     //成员id，自增
//     int groupId;                 //群组id
//     String memberName;           //成员名称
//     String memberEmail;          //成员邮箱
//     String memberIdentity;       //成员身份
//     String memberGender;         //成员性别
//     String memberDescription;    //成员描述
//     byte[] memberFeature;        //成员特征
// }

import {ref} from "vue";

class MemberModel {
    static addMemberModel={
       groupId:0,
       memberName:'',
       memberEmail:'',
       memberIdentity:'',
       memberGender:'',
       memberDescription:'',

    }
    static addFeatureModel=null;

    static memberList=ref([]);
}
export default MemberModel;