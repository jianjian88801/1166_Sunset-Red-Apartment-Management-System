









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
 * 访客
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangke")
public class FangkeController {
    private static final Logger logger = LoggerFactory.getLogger(FangkeController.class);

    @Autowired
    private FangkeService fangkeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private ZukeService zukeService;


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
        PageUtils page = fangkeService.queryPage(params);

        //字典表数据转换
        List<FangkeView> list =(List<FangkeView>)page.getList();
        for(FangkeView c:list){
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
        FangkeEntity fangke = fangkeService.selectById(id);
        if(fangke !=null){
            //entity转view
            FangkeView view = new FangkeView();
            BeanUtils.copyProperties( fangke , view );//把实体数据重构到view中

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
    public R save(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .eq("danyuan_types", fangke.getDanyuanTypes())
            .eq("fangke_name", fangke.getFangkeName())
            .eq("fangke_phone", fangke.getFangkePhone())
            .eq("fangke_id_number", fangke.getFangkeIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            fangke.setInsertTime(new Date());
            fangke.setCreateTime(new Date());
            fangkeService.insert(fangke);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .notIn("id",fangke.getId())
            .andNew()
            .eq("danyuan_types", fangke.getDanyuanTypes())
            .eq("fangke_name", fangke.getFangkeName())
            .eq("fangke_phone", fangke.getFangkePhone())
            .eq("fangke_id_number", fangke.getFangkeIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if("".equals(fangke.getFangkePhoto()) || "null".equals(fangke.getFangkePhoto())){
                fangke.setFangkePhoto(null);
        }
        if(fangkeEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      fangke.set
            //  }
            fangkeService.updateById(fangke);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        fangkeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<FangkeEntity> fangkeList = new ArrayList<>();//上传的东西
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
                            FangkeEntity fangkeEntity = new FangkeEntity();
//                            fangkeEntity.setDanyuanTypes(Integer.valueOf(data.get(0)));   //单元 要改的
//                            fangkeEntity.setFangkeName(data.get(0));                    //访客姓名 要改的
//                            fangkeEntity.setFangkePhone(data.get(0));                    //访客手机号 要改的
//                            fangkeEntity.setFangkeIdNumber(data.get(0));                    //访客身份证号 要改的
//                            fangkeEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            fangkeEntity.setFangkePhoto("");//照片
//                            fangkeEntity.setFangkeContent("");//照片
//                            fangkeEntity.setInsertTime(date);//时间
//                            fangkeEntity.setCreateTime(date);//时间
                            fangkeList.add(fangkeEntity);


                            //把要查询是否重复的字段放入map中
                                //访客手机号
                                if(seachFields.containsKey("fangkePhone")){
                                    List<String> fangkePhone = seachFields.get("fangkePhone");
                                    fangkePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> fangkePhone = new ArrayList<>();
                                    fangkePhone.add(data.get(0));//要改的
                                    seachFields.put("fangkePhone",fangkePhone);
                                }
                                //访客身份证号
                                if(seachFields.containsKey("fangkeIdNumber")){
                                    List<String> fangkeIdNumber = seachFields.get("fangkeIdNumber");
                                    fangkeIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangkeIdNumber = new ArrayList<>();
                                    fangkeIdNumber.add(data.get(0));//要改的
                                    seachFields.put("fangkeIdNumber",fangkeIdNumber);
                                }
                        }

                        //查询是否重复
                         //访客手机号
                        List<FangkeEntity> fangkeEntities_fangkePhone = fangkeService.selectList(new EntityWrapper<FangkeEntity>().in("fangke_phone", seachFields.get("fangkePhone")));
                        if(fangkeEntities_fangkePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangkeEntity s:fangkeEntities_fangkePhone){
                                repeatFields.add(s.getFangkePhone());
                            }
                            return R.error(511,"数据库的该表中的 [访客手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //访客身份证号
                        List<FangkeEntity> fangkeEntities_fangkeIdNumber = fangkeService.selectList(new EntityWrapper<FangkeEntity>().in("fangke_id_number", seachFields.get("fangkeIdNumber")));
                        if(fangkeEntities_fangkeIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangkeEntity s:fangkeEntities_fangkeIdNumber){
                                repeatFields.add(s.getFangkeIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [访客身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangkeService.insertBatch(fangkeList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
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
        PageUtils page = fangkeService.queryPage(params);

        //字典表数据转换
        List<FangkeView> list =(List<FangkeView>)page.getList();
        for(FangkeView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangkeEntity fangke = fangkeService.selectById(id);
            if(fangke !=null){


                //entity转view
                FangkeView view = new FangkeView();
                BeanUtils.copyProperties( fangke , view );//把实体数据重构到view中

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
    public R add(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());
        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .eq("danyuan_types", fangke.getDanyuanTypes())
            .eq("fangke_name", fangke.getFangkeName())
            .eq("fangke_phone", fangke.getFangkePhone())
            .eq("fangke_id_number", fangke.getFangkeIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            fangke.setInsertTime(new Date());
            fangke.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      fangke.set
        //  }
        fangkeService.insert(fangke);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
