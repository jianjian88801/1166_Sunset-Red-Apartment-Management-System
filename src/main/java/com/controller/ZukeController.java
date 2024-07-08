









package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 租客
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zuke")
public class ZukeController {
    private static final Logger logger = LoggerFactory.getLogger(ZukeController.class);

    @Autowired
    private ZukeService zukeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("租客".equals(role))
            params.put("zukeId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zukeService.queryPage(params);

        //字典表数据转换
        List<ZukeView> list =(List<ZukeView>)page.getList();
        for(ZukeView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZukeEntity zuke = zukeService.selectById(id);
        if(zuke !=null){
            //entity转view
            ZukeView view = new ZukeView();
            BeanUtils.copyProperties( zuke , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZukeEntity zuke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zuke:{}",this.getClass().getName(),zuke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<ZukeEntity> queryWrapper = new EntityWrapper<ZukeEntity>()
            .eq("username", zuke.getUsername())
            .or()
            .eq("zuke_phone", zuke.getZukePhone())
            .or()
            .eq("zuke_id_number", zuke.getZukeIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZukeEntity zukeEntity = zukeService.selectOne(queryWrapper);
        if(zukeEntity==null){
            zuke.setCreateTime(new Date());
            zuke.setPassword("123456");
            zukeService.insert(zuke);
            return R.ok();
        }else {
            return R.error(511,"账户或者租客手机号或者租客身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZukeEntity zuke, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zuke:{}",this.getClass().getName(),zuke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ZukeEntity> queryWrapper = new EntityWrapper<ZukeEntity>()
            .notIn("id",zuke.getId())
            .andNew()
            .eq("username", zuke.getUsername())
            .or()
            .eq("zuke_phone", zuke.getZukePhone())
            .or()
            .eq("zuke_id_number", zuke.getZukeIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZukeEntity zukeEntity = zukeService.selectOne(queryWrapper);
        if("".equals(zuke.getZukePhoto()) || "null".equals(zuke.getZukePhoto())){
                zuke.setZukePhoto(null);
        }
        if(zukeEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      zuke.set
            //  }
            zukeService.updateById(zuke);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者租客手机号或者租客身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zukeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ZukeEntity> zukeList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZukeEntity zukeEntity = new ZukeEntity();
//                            zukeEntity.setUsername(data.get(0));                    //账户 要改的
//                            //zukeEntity.setPassword("123456");//密码
//                            zukeEntity.setZukeName(data.get(0));                    //租客姓名 要改的
//                            zukeEntity.setZukePhone(data.get(0));                    //租客手机号 要改的
//                            zukeEntity.setZukeIdNumber(data.get(0));                    //租客身份证号 要改的
//                            zukeEntity.setZukeEmail(data.get(0));                    //电子邮箱 要改的
//                            zukeEntity.setZukePhoto("");//照片
//                            zukeEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            zukeEntity.setDanyuanTypes(Integer.valueOf(data.get(0)));   //单元号 要改的
//                            zukeEntity.setShiyongTypes(Integer.valueOf(data.get(0)));   //使用 要改的
//                            zukeEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            zukeEntity.setCreateTime(date);//时间
                            zukeList.add(zukeEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //租客手机号
                                if(seachFields.containsKey("zukePhone")){
                                    List<String> zukePhone = seachFields.get("zukePhone");
                                    zukePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> zukePhone = new ArrayList<>();
                                    zukePhone.add(data.get(0));//要改的
                                    seachFields.put("zukePhone",zukePhone);
                                }
                                //租客身份证号
                                if(seachFields.containsKey("zukeIdNumber")){
                                    List<String> zukeIdNumber = seachFields.get("zukeIdNumber");
                                    zukeIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zukeIdNumber = new ArrayList<>();
                                    zukeIdNumber.add(data.get(0));//要改的
                                    seachFields.put("zukeIdNumber",zukeIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<ZukeEntity> zukeEntities_username = zukeService.selectList(new EntityWrapper<ZukeEntity>().in("username", seachFields.get("username")));
                        if(zukeEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZukeEntity s:zukeEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //租客手机号
                        List<ZukeEntity> zukeEntities_zukePhone = zukeService.selectList(new EntityWrapper<ZukeEntity>().in("zuke_phone", seachFields.get("zukePhone")));
                        if(zukeEntities_zukePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZukeEntity s:zukeEntities_zukePhone){
                                repeatFields.add(s.getZukePhone());
                            }
                            return R.error(511,"数据库的该表中的 [租客手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //租客身份证号
                        List<ZukeEntity> zukeEntities_zukeIdNumber = zukeService.selectList(new EntityWrapper<ZukeEntity>().in("zuke_id_number", seachFields.get("zukeIdNumber")));
                        if(zukeEntities_zukeIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZukeEntity s:zukeEntities_zukeIdNumber){
                                repeatFields.add(s.getZukeIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [租客身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zukeService.insertBatch(zukeList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ZukeEntity zuke = zukeService.selectOne(new EntityWrapper<ZukeEntity>().eq("username", username));
        if(zuke==null || !zuke.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(zuke.getShiyongTypes() == 1){
            return R.error("账户已被禁用，请联系管理员解禁");
        }
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(zuke.getId(),username, "zuke", "租客");
        R r = R.ok();
        r.put("token", token);
        r.put("role","租客");
        r.put("username",zuke.getZukeName());
        r.put("tableName","zuke");
        r.put("userId",zuke.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ZukeEntity zuke){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<ZukeEntity> queryWrapper = new EntityWrapper<ZukeEntity>()
            .eq("username", zuke.getUsername())
            .or()
            .eq("zuke_phone", zuke.getZukePhone())
            .or()
            .eq("zuke_id_number", zuke.getZukeIdNumber())
            ;
        ZukeEntity zukeEntity = zukeService.selectOne(queryWrapper);
        if(zukeEntity != null)
            return R.error("账户或者租客手机号或者租客身份证号已经被使用");
        zuke.setNewMoney(0.0);
        zuke.setShiyongTypes(1);
        zuke.setCreateTime(new Date());
        zukeService.insert(zuke);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ZukeEntity zuke = new ZukeEntity();
        zuke.setPassword("123456");
        zuke.setId(id);
        zukeService.updateById(zuke);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        ZukeEntity zuke = zukeService.selectOne(new EntityWrapper<ZukeEntity>().eq("username", username));
        if(zuke!=null){
            zuke.setPassword("123456");
            boolean b = zukeService.updateById(zuke);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrZuke(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ZukeEntity zuke = zukeService.selectById(id);
        if(zuke !=null){
            //entity转view
            ZukeView view = new ZukeView();
            BeanUtils.copyProperties( zuke , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = zukeService.queryPage(params);

        //字典表数据转换
        List<ZukeView> list =(List<ZukeView>)page.getList();
        for(ZukeView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZukeEntity zuke = zukeService.selectById(id);
            if(zuke !=null){


                //entity转view
                ZukeView view = new ZukeView();
                BeanUtils.copyProperties( zuke , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZukeEntity zuke, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zuke:{}",this.getClass().getName(),zuke.toString());
        Wrapper<ZukeEntity> queryWrapper = new EntityWrapper<ZukeEntity>()
            .eq("username", zuke.getUsername())
            .or()
            .eq("zuke_phone", zuke.getZukePhone())
            .or()
            .eq("zuke_id_number", zuke.getZukeIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZukeEntity zukeEntity = zukeService.selectOne(queryWrapper);
        if(zukeEntity==null){
            zuke.setCreateTime(new Date());
        zuke.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      zuke.set
        //  }
        zukeService.insert(zuke);
            return R.ok();
        }else {
            return R.error(511,"账户或者租客手机号或者租客身份证号已经被使用");
        }
    }


}
