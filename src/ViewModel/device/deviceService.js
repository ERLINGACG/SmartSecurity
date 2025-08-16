import axios from "axios";
import {ref} from "vue";
import DeviceModel from "@/Model/device/deviceModel.js";

class DeviceService{
    test(DeviceModel){
        console.log("test");
        console.log(DeviceModel);
    }
    test2(DeviceModel,index){
        console.log("test2");
        console.log(DeviceModel[index]);
        console.log(index);
    }
    test3(newDeviceModel,updateDeviceModel,configModelData,index){
        console.log("test3");
        updateDeviceModel=configModelData[index];
        if(newDeviceModel.deviceName!==""){
            updateDeviceModel.deviceName=newDeviceModel.deviceName;
        }

        console.log(updateDeviceModel);
    }
    async PostAddDevices(DeviceModel){
        const url=' http://localhost:8080/device/api/add'
        try{
            const response = await axios.post(url,{
                   pid:DeviceModel.pid,
                   deviceName:DeviceModel.deviceName,
                   deviceTopic:DeviceModel.deviceTopic,
                   deviceType:DeviceModel.deviceType,
                   deviceDescribe:DeviceModel.deviceDescribe,
                   userEmail:DeviceModel.userEmail,
                }
            )
            alert(response.data.message);

        }catch(e){
            alert("添加失败，请检查账号与设备信息是否正确");
            console.log(e);
        }
    }
    async DeleteDevice(isdelete,pid,email){
        if(!isdelete){
            alert("请确认删除");
            return;
        }
        const url='http://localhost:8080/device/api/delete'
        try {
            const response = await axios.delete(url, {
                params: { pid, email }
            });
            alert(response.data.message);
        } catch (error) {
            console.error('删除设备失败:', error);
            throw error;
        }
    }
    async GetDeviceList(email){
        try{
            console.log(email);
            const url='http://localhost:8080/device/api/select'

            const response = await axios.get(url, {
                params:{ email }
            })
            // alert(response.data.message);
            console.log(response.data.data);
            return response.data.data;
        }catch(e){
          alert("<UNK>");
          console.log(e);
        }

    }
    async GetDeviceInfo(pid,email){
        try{
            const url='http://localhost:8080/device/api/getDevice'
            const response = await axios.get(url, {
                params:{ pid,email }
            })
            console.log(response.data.data);
            return response.data.data;
        }catch(e){
            console.log(e);
        }
    }

    async UpdateDevice(DeviceModel){
        console.log(DeviceModel);
        const url='http://localhost:8080/device/api/update'
        try{
            const response = await axios.put(url,{
                pid:DeviceModel.pid,
                deviceName:DeviceModel.deviceName,
                deviceTopic:DeviceModel.deviceTopic,
                deviceType:DeviceModel.deviceType,
                deviceDescribe:DeviceModel.deviceDescribe,
                userEmail:DeviceModel.userEmail,
             }
         )
         alert(response.data.message);
        }catch(e){
            alert("更新失败，请检查账号与设备信息是否正确");
            console.log(e);
        }
    }
}
const deviceService = new DeviceService();
export default deviceService;