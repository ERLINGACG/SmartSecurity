import { createRouter, createWebHistory } from 'vue-router';

function RegisterRoute(path,name, params, meta = {}) {
    return {
        path: path,
        name: name,
        component: params,
        meta: meta
    }
}
function RegisterChildRoutes(parentPath, name, component, childRoutes = []) {
    return {
        path: parentPath,
        name: name,
        component: component,
        children: childRoutes  // 支持子路由配置
    }
}

const routes = [
     RegisterRoute('/test/page_0', 'page_0', () => import('../test/page_0.vue')),
     RegisterRoute('/user/login', 'login', () => import('../View/user/Login.vue')),
     RegisterRoute('/user/register','register', () => import('../View/user/Register.vue')),
     RegisterChildRoutes(
        '/home',
        'home',

        () => import('../View/home/homePage1.vue'),[
             RegisterRoute('homeItem', 'homeItem', () => import('../View/home/homeItem.vue')),
             // RegisterRoute('devicePage', 'homeItem2', () => import('../View/device/devicePage.vue')),
             RegisterChildRoutes(
                 'devicePage',
                 'devicePage', () => import('../View/device/devicePage.vue'),[
                     RegisterRoute('add', 'addDevice', () => import('../View/device/add/addDevice.vue')),
                     RegisterRoute('update', 'updateDevice', () => import('../View/device/update/updateDevice.vue')),
                     RegisterRoute('delete', 'deleteDevice', () => import('../View/device/delete/deleteDevice.vue')),
                     RegisterRoute('list', 'listDevice', () => import('../View/device/list/ListDevice.vue')),
                 ]
             ),
             RegisterChildRoutes(
                 'groupPage',
                 'groupPage', () => import('../View/group/groupPage.vue'),[
                     RegisterRoute('create', 'createGroup', () => import('../View/group/create/createGroup.vue')),
                     RegisterRoute('upload', 'uploadMember', () => import('../View/group/upLoad/upLoadMember.vue')),
                     RegisterRoute('update-m', 'updateMember', () => import('../View/group/updatemember/updateMember.vue')),
                     RegisterRoute('update-g', 'updateGroup', () => import('../View/group/updategroup/updateGroup.vue')),
                     RegisterRoute('list-p', 'listGroup', () => import('../View/group/listgroup/listGroup.vue')),
                 ]
             ),
             RegisterRoute('/user/PersonalCenter', 'PersonalCenter', () => import('../View/user/PersonalCenter.vue')),
         ]),
     {
        path: '/',
        redirect: '/user/login',
     }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
});
export default router;