import {ref} from "vue";

class DeviceModel {

    // int pid;
    // String deviceName;
    // @NotBlank(message = "设备主题不能为空")
    // String deviceTopic;
    // @NotBlank(message = "设备类型不能为空")
    // String deviceType;
    //
    // String deviceDescribe;
    // @NotBlank(message = "用户邮箱不能为空")
    // @Email
    // String userEmail;
    static device={
        pid:0,
        deviceName:'',
        deviceTopic:'',
        deviceType:'',
        deviceDescribe:'',
        userEmail:localStorage.getItem('nowUser'),
    }
    static deleteModel={
        isdelete:false,
        pid:0,
        email:'',
    }
    static DeviceList=ref([])
    static updateModel=ref({
        pid:0,
        deviceName:'',
        deviceTopic:'',
        deviceType:'',
        deviceDescribe:'',
        userEmail:localStorage.getItem('nowUser'),
    })

    static updateConfig={
        index:0,
        item: []
    }
}

export default DeviceModel;