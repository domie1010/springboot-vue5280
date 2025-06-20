
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
 * 任务
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shijianrenshu")
public class ShijianrenshuController {
    private static final Logger logger = LoggerFactory.getLogger(ShijianrenshuController.class);

    private static final String TABLE_NAME = "shijianrenshu";

    @Autowired
    private ShijianrenshuService shijianrenshuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ShijianrenshuOrderService shijianrenshuOrderService;
    @Autowired
    private ShijianrenshuCollectionService shijianrenshuCollectionService;
    //级联表非注册的service
    //注册表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private QiyeService qiyeService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("企业".equals(role))
            params.put("qiyeId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = shijianrenshuService.queryPage(params);

        //字典表数据转换
        List<ShijianrenshuView> list =(List<ShijianrenshuView>)page.getList();
        for(ShijianrenshuView c:list){
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
        ShijianrenshuEntity shijianrenshu = shijianrenshuService.selectById(id);
        if(shijianrenshu !=null){
            //entity转view
            ShijianrenshuView view = new ShijianrenshuView();
            BeanUtils.copyProperties( shijianrenshu , view );//把实体数据重构到view中
            //级联表 企业
            //级联表
            QiyeEntity qiye = qiyeService.selectById(shijianrenshu.getQiyeId());
            if(qiye != null){
            BeanUtils.copyProperties( qiye , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "qiyeId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setQiyeId(qiye.getId());
            }
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
    public R save(@RequestBody ShijianrenshuEntity shijianrenshu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shijianrenshu:{}",this.getClass().getName(),shijianrenshu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("企业".equals(role))
            shijianrenshu.setQiyeId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShijianrenshuEntity> queryWrapper = new EntityWrapper<ShijianrenshuEntity>()
            .eq("qiye_id", shijianrenshu.getQiyeId())
            .eq("shijianrenshu_name", shijianrenshu.getShijianrenshuName())
            .eq("shijianrenshu_types", shijianrenshu.getShijianrenshuTypes())
            .eq("shijianrenshu_clicknum", shijianrenshu.getShijianrenshuClicknum())
            .eq("renwuzhuangtai_types", shijianrenshu.getRenwuzhuangtaiTypes())
            .eq("shangxia_types", shijianrenshu.getShangxiaTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShijianrenshuEntity shijianrenshuEntity = shijianrenshuService.selectOne(queryWrapper);
        if(shijianrenshuEntity==null){
            shijianrenshu.setShijianrenshuClicknum(1);
            shijianrenshu.setShangxiaTypes(1);
            shijianrenshu.setInsertTime(new Date());
            shijianrenshu.setCreateTime(new Date());
            shijianrenshuService.insert(shijianrenshu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShijianrenshuEntity shijianrenshu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shijianrenshu:{}",this.getClass().getName(),shijianrenshu.toString());
        ShijianrenshuEntity oldShijianrenshuEntity = shijianrenshuService.selectById(shijianrenshu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("企业".equals(role))
//            shijianrenshu.setQiyeId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shijianrenshu.getShijianrenshuPhoto()) || "null".equals(shijianrenshu.getShijianrenshuPhoto())){
                shijianrenshu.setShijianrenshuPhoto(null);
        }

            shijianrenshuService.updateById(shijianrenshu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShijianrenshuEntity> oldShijianrenshuList =shijianrenshuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shijianrenshuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ShijianrenshuEntity> shijianrenshuList = new ArrayList<>();//上传的东西
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
                            ShijianrenshuEntity shijianrenshuEntity = new ShijianrenshuEntity();
//                            shijianrenshuEntity.setQiyeId(Integer.valueOf(data.get(0)));   //企业 要改的
//                            shijianrenshuEntity.setShijianrenshuName(data.get(0));                    //任务题目 要改的
//                            shijianrenshuEntity.setShijianrenshuUuidNumber(data.get(0));                    //任务编号 要改的
//                            shijianrenshuEntity.setShijianrenshuPhoto("");//详情和图片
//                            shijianrenshuEntity.setShijianrenshuTypes(Integer.valueOf(data.get(0)));   //任务类型 要改的
//                            shijianrenshuEntity.setShijianrenshuClicknum(Integer.valueOf(data.get(0)));   //任务热度 要改的
//                            shijianrenshuEntity.setShijianrenshuText(data.get(0));                    //任务要求 要改的
//                            shijianrenshuEntity.setShijianrenshuContent("");//详情和图片
//                            shijianrenshuEntity.setRenwuzhuangtaiTypes(Integer.valueOf(data.get(0)));   //任务状态 要改的
//                            shijianrenshuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            shijianrenshuEntity.setInsertTime(date);//时间
//                            shijianrenshuEntity.setCreateTime(date);//时间
                            shijianrenshuList.add(shijianrenshuEntity);


                            //把要查询是否重复的字段放入map中
                                //任务编号
                                if(seachFields.containsKey("shijianrenshuUuidNumber")){
                                    List<String> shijianrenshuUuidNumber = seachFields.get("shijianrenshuUuidNumber");
                                    shijianrenshuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shijianrenshuUuidNumber = new ArrayList<>();
                                    shijianrenshuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shijianrenshuUuidNumber",shijianrenshuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //任务编号
                        List<ShijianrenshuEntity> shijianrenshuEntities_shijianrenshuUuidNumber = shijianrenshuService.selectList(new EntityWrapper<ShijianrenshuEntity>().in("shijianrenshu_uuid_number", seachFields.get("shijianrenshuUuidNumber")));
                        if(shijianrenshuEntities_shijianrenshuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShijianrenshuEntity s:shijianrenshuEntities_shijianrenshuUuidNumber){
                                repeatFields.add(s.getShijianrenshuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [任务编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shijianrenshuService.insertBatch(shijianrenshuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ShijianrenshuView> returnShijianrenshuViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = shijianrenshuOrderService.queryPage(params1);
        List<ShijianrenshuOrderView> orderViewsList =(List<ShijianrenshuOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ShijianrenshuOrderView orderView:orderViewsList){
            Integer shijianrenshuTypes = orderView.getShijianrenshuTypes();
            if(typeMap.containsKey(shijianrenshuTypes)){
                typeMap.put(shijianrenshuTypes,typeMap.get(shijianrenshuTypes)+1);
            }else{
                typeMap.put(shijianrenshuTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("shijianrenshuTypes",type);
            PageUtils pageUtils1 = shijianrenshuService.queryPage(params2);
            List<ShijianrenshuView> shijianrenshuViewList =(List<ShijianrenshuView>)pageUtils1.getList();
            returnShijianrenshuViewList.addAll(shijianrenshuViewList);
            if(returnShijianrenshuViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = shijianrenshuService.queryPage(params);
        if(returnShijianrenshuViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnShijianrenshuViewList.size();//要添加的数量
            List<ShijianrenshuView> shijianrenshuViewList =(List<ShijianrenshuView>)page.getList();
            for(ShijianrenshuView shijianrenshuView:shijianrenshuViewList){
                Boolean addFlag = true;
                for(ShijianrenshuView returnShijianrenshuView:returnShijianrenshuViewList){
                    if(returnShijianrenshuView.getId().intValue() ==shijianrenshuView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnShijianrenshuViewList.add(shijianrenshuView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnShijianrenshuViewList = returnShijianrenshuViewList.subList(0, limit);
        }

        for(ShijianrenshuView c:returnShijianrenshuViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnShijianrenshuViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shijianrenshuService.queryPage(params);

        //字典表数据转换
        List<ShijianrenshuView> list =(List<ShijianrenshuView>)page.getList();
        for(ShijianrenshuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShijianrenshuEntity shijianrenshu = shijianrenshuService.selectById(id);
            if(shijianrenshu !=null){

                //点击数量加1
                shijianrenshu.setShijianrenshuClicknum(shijianrenshu.getShijianrenshuClicknum()+1);
                shijianrenshuService.updateById(shijianrenshu);

                //entity转view
                ShijianrenshuView view = new ShijianrenshuView();
                BeanUtils.copyProperties( shijianrenshu , view );//把实体数据重构到view中

                //级联表
                    QiyeEntity qiye = qiyeService.selectById(shijianrenshu.getQiyeId());
                if(qiye != null){
                    BeanUtils.copyProperties( qiye , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQiyeId(qiye.getId());
                }
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
    public R add(@RequestBody ShijianrenshuEntity shijianrenshu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shijianrenshu:{}",this.getClass().getName(),shijianrenshu.toString());
        Wrapper<ShijianrenshuEntity> queryWrapper = new EntityWrapper<ShijianrenshuEntity>()
            .eq("qiye_id", shijianrenshu.getQiyeId())
            .eq("shijianrenshu_name", shijianrenshu.getShijianrenshuName())
            .eq("shijianrenshu_uuid_number", shijianrenshu.getShijianrenshuUuidNumber())
            .eq("shijianrenshu_types", shijianrenshu.getShijianrenshuTypes())
            .eq("shijianrenshu_clicknum", shijianrenshu.getShijianrenshuClicknum())
            .eq("shijianrenshu_text", shijianrenshu.getShijianrenshuText())
            .eq("renwuzhuangtai_types", shijianrenshu.getRenwuzhuangtaiTypes())
            .eq("shangxia_types", shijianrenshu.getShangxiaTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShijianrenshuEntity shijianrenshuEntity = shijianrenshuService.selectOne(queryWrapper);
        if(shijianrenshuEntity==null){
            shijianrenshu.setInsertTime(new Date());
            shijianrenshu.setCreateTime(new Date());
        shijianrenshuService.insert(shijianrenshu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}
