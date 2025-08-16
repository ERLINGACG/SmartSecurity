//测试案例
// data = {
//     'groupName': f'测试群组{item}',
//     'groupEmail': 'test1@test1.com',  # 修正邮箱格式
//      'groupTopic': '测试主题',
//     'groupDescription': '测试描述',
//     'groupVisibility': '公开'
// }

import {ref} from "vue";

class GroupModel{
    static newGroup={
        groupName: '',          //群组名称
        groupEmail: '',        //绑定邮箱
        groupTopic: '',        //群组主题
        groupDescription: '',  //群组描述
        groupVisibility: ''   //群组可见性
    }
    static groupList=ref([])
    static updateGroupList=ref({
        gid: 0,
        groupName: '',
        groupEmail: localStorage.getItem('nowUser'),
        groupTopic: '',
        groupDescription: '',
        groupVisibility: ''
    })
}
export default GroupModel;