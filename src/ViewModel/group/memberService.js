import axios from "axios";

class MemberService {
    urlList={
        add:'http://localhost:8080/group/member/add',
        get_list:'http://localhost:8080/group/member/get/',
        update:'http://localhost:8080/group/member/update',
        delete:'http://localhost:8080/group/member/delete/',
    }
    async test(addMemberModel, image_file) {
         console.log(addMemberModel)
         console.log(image_file)
    }
    async addMember(addMemberModel, image_file) {
        const url=this.urlList.add
        try{
            const form = new FormData();
            form.append('groupMember', new Blob([JSON.stringify(addMemberModel)], {
                type: 'application/json'
            }));
            form.append('file', image_file, image_file.name); // 字段名需与后端@RequestParam一致
            // 移除form.getHeaders()用法
            const headers = {
                'Content-Type': 'multipart/form-data; boundary=' + form._boundary
            };

            // 完整axios配置
            const response = await axios.post(url, form, {
                // headers: {
                //     // ...form.getHeaders(),
                //     // 'X-Requested-With': 'XMLHttpRequest'
                // },
                headers: headers,
                maxContentLength: Infinity,
                maxBodyLength: Infinity,
                timeout: 10000,
                withCredentials: true
            });

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
    async getMembers(gid) {
        const url=this.urlList.get_list+gid;
        try{
            const response = await axios.get(url);
            console.log(response.data)
            return response.data.data
        }catch (error) {
            if (error.response) {
                console.error('获取成员列表失败:', error.response.data.message);
                alert(error.response.data.message || `获取成员列表失败: ${error.response.data.message}`);
            }

        }
    }
    async updateMember(memberModel) {
        const url=this.urlList.update;
        try{
            const response = await axios.put(url,{
                mid:memberModel.mid,
                groupId:memberModel.groupId,
                memberName:memberModel.memberName,
                memberEmail:memberModel.memberEmail,
                memberIdentity:memberModel.memberIdentity,
                memberGender:memberModel.memberGender,
                memberDescription:memberModel.memberDescription,
            });
            console.log(response.data)
            alert(response.data.message)
        }catch (error) {
            if (error.response) {
                console.error('更新成员失败:', error.response.data.message);
                alert(error.response.data.message || `更新成员失败: ${error.response.data.message}`);
            }
        }
    }
    async deleteMember(memberModel) {
        const url=this.urlList.delete+memberModel.groupId+'/'+memberModel.mid;
        try{
            const response = await axios.delete(url);
            console.log(response.data)
            alert(response.data.message)
            window.location.reload();
        }catch (error) {
            if (error.response) {
                console.error('删除成员失败:', error.response.data.message);
                alert(error.response.data.message || `删除成员失败: ${error.response.data.message}`);
            }
        }
    }
}
const memberService = new MemberService();
export default memberService;
