import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

         import users from '@/views/modules/users/list'
        import dictionary from '@/views/modules/dictionary/list'
        import fangke from '@/views/modules/fangke/list'
        import gonggao from '@/views/modules/gonggao/list'
        import jiaofei from '@/views/modules/jiaofei/list'
        import liuyan from '@/views/modules/liuyan/list'
        import weixiu from '@/views/modules/weixiu/list'
        import xingcheng from '@/views/modules/xingcheng/list'
        import zuke from '@/views/modules/zuke/list'
        import config from '@/views/modules/config/list'
        import dictionaryDanyuan from '@/views/modules/dictionaryDanyuan/list'
        import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
        import dictionaryJiaofei from '@/views/modules/dictionaryJiaofei/list'
        import dictionaryLiuyan from '@/views/modules/dictionaryLiuyan/list'
        import dictionarySex from '@/views/modules/dictionarySex/list'
        import dictionaryShifou from '@/views/modules/dictionaryShifou/list'
        import dictionaryShiyong from '@/views/modules/dictionaryShiyong/list'
        import dictionaryWeixiu from '@/views/modules/dictionaryWeixiu/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryDanyuan',
        name: '单元号类型名称',
        component: dictionaryDanyuan
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型名称',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryJiaofei',
        name: '是否缴费',
        component: dictionaryJiaofei
    }
    ,{
        path: '/dictionaryLiuyan',
        name: '留言类型名称',
        component: dictionaryLiuyan
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型名称',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShifou',
        name: '是否维修',
        component: dictionaryShifou
    }
    ,{
        path: '/dictionaryShiyong',
        name: '是否使用',
        component: dictionaryShiyong
    }
    ,{
        path: '/dictionaryWeixiu',
        name: '维修类型名称',
        component: dictionaryWeixiu
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/fangke',
        name: '访客',
        component: fangke
      }
    ,{
        path: '/gonggao',
        name: '公告信息',
        component: gonggao
      }
    ,{
        path: '/jiaofei',
        name: '缴费',
        component: jiaofei
      }
    ,{
        path: '/liuyan',
        name: '留言',
        component: liuyan
      }
    ,{
        path: '/weixiu',
        name: '维修',
        component: weixiu
      }
    ,{
        path: '/xingcheng',
        name: '行程轨迹',
        component: xingcheng
      }
    ,{
        path: '/zuke',
        name: '租客',
        component: zuke
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
