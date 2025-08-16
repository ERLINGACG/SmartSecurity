import {ref} from "vue";

class ExampleModel {
    static historyUserList=ref([
        {id:1,name:'空条承太郎'},
        {id:2,name:'波鲁纳雷夫'},
        {id:3,name:'东方仗助'}
    ])
    static box1=ref({
        email:'',
        id:0,
        name:'',
    })
    static p1=ref([
        {pid:1,name:'设备121245',class:'A',topic:'123/123/121341241',email:'123@123.com',time:'2022-01-01'},
        {pid:2,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:3,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:4,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:5,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:6,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:7,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:8,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:9,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
        {pid:10,name:'设备121245',class:'A',topic:'123/123',email:'123@123.com',time:'2022-01-01'},
    ])
    static m1=ref([
        {name:'空条承太郎',email:'empty@empty.com',gender:'男',time:'2022-01-01',identity:'学生'},
        {name:'波鲁纳雷夫',email:'browne@browne.com',gender:'男',time:'2022-01-01' ,identity:'学生'},
    ])
    static t1(){
        console.log(this.box1.value)
    }
}

export default ExampleModel;