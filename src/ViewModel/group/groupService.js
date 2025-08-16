import axios from "axios";
import GroupModel from "@/Model/group/groupModel.js";


// static newGroup={
//     groupName: '',          //群组名称
//     groupEmail: '',        //绑定邮箱
//     groupTopic: '',        //群组主题
//     groupDescription: '',  //群组描述
//     groupVisibility: ''   //群组可见性
// }
class GroupService {
   groupLis={
       add:'http://localhost:8080/group/add',
       get:'http://localhost:8080/group/get',
       getList:'http://localhost:8080/group/getAll',
       update:'http://localhost:8080/group/update',
       delete:'http://localhost:8080/group/delete'
   }
   testCatModel(group){
       console.log(group)
   }
   async addGroup(group){
       try{
           const url=this.groupLis.add
           const response= await axios.post(url,{
               groupName:group.groupName,
               groupEmail:group.groupEmail,
               groupTopic:group.groupTopic,
               groupDescription:group.groupDescription,
               groupVisibility:group.groupVisibility
           })
           console.log(response.data)
           alert(response.data.message)
       }catch (error) {
           if (error.response) {
               console.error('请求失败:', error.response.status);
               alert(error.response.data.message || `操作失败: ${error.response.status}`);
           } else {
               // 网络错误或无响应
               console.error('请求错误:', error.message);
               alert('网络错误，请检查连接');
           }
           console.log(error)
       }

   }
   async getGroup(name){
       const url=this.groupLis.get
       console.log(name)
       const headers = {
           'Authorization': '',
           'Group-Email': localStorage.getItem('nowUser')
       };

       const response= await axios.get(url,{
               headers:headers,
               params:{
                   groupName:name
               }
           }
       )
       console.log(response.data)
       return response.data.data
   }
   async getGroupList(){

       const url=this.groupLis.getList
       const headers = {
           'Authorization': '',
           'Group-Email': localStorage.getItem('nowUser')
       };
       const response= await axios.get(url,{ headers:headers})
       GroupModel.groupList.value=response.data.data
   }
   async updateGroup(group){
       try{
       const url=this.groupLis.update
       const headers = {
           'Authorization': '',
           'Group-Email': localStorage.getItem('nowUser')
       };
       const response= await axios.put(url,{
               gid:group.gid,
               groupName:group.groupName,
               groupEmail:group.groupEmail,
               groupTopic:group.groupTopic,
               groupDescription:group.groupDescription,
               groupVisibility:group.groupVisibility

       })
       alert(response.data.message)
       }catch (error) {
           if (error.response) {
               // 服务器返回4xx/5xx状态码
               console.error('请求失败:', error.response.status);
               alert(error.response.data.message || `操作失败: ${error.response.status}`);
           } else {
               // 网络错误或无响应
               console.error('请求错误:', error.message);
               alert('网络错误，请检查连接');
           }
           console.log(error)
       }
   }

   async deleteGroup(gid){
       try{
           const isConfirmed = confirm(`删除群组${gid},会删除群组内所有成员，请确认是否删除`);
           if (!isConfirmed) return; // 用户取消则中止操作
           const url=this.groupLis.delete+`/${gid}`
           const headers = {
               'Authorization': '',
               'Group-Email': localStorage.getItem('nowUser')
           };
           const response= await axios.delete(url,{
                   headers:headers,
               }
           )
           console.log(response.data.data)
           alert(response.data.message)
           window.location.reload()
       }catch (error) {
           if (error.response) {
               // 服务器返回4xx/5xx状态码
               console.error('请求失败:', error.response.status);
               alert(error.response.data.message || `操作失败: ${error.response.status}`);
           } else {
               // 网络错误或无响应
               console.error('请求错误:', error.message);
               alert('网络错误，请检查连接');
           }
       }

   }

}
const groupService = new GroupService();
export default groupService
