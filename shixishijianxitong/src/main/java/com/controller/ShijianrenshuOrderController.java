
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
 * 任务订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shijianrenshuOrder")
public class ShijianrenshuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ShijianrenshuOrderController.class);

    private static final String TABLE_NAME = "shijianrenshuOrder";

    @Autowired
    private ShijianrenshuOrderService shijianrenshuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表非注册的service
    @Autowired
    private ShijianrenshuService shijianrenshuService;
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
        PageUtils page = shijianrenshuOrderService.queryPage(params);

        //字典表数据转换
        List<ShijianrenshuOrderView> list =(List<ShijianrenshuOrderView>)page.getList();
        for(ShijianrenshuOrderView c:list){
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
        ShijianrenshuOrderEntity shijianrenshuOrder = shijianrenshuOrderService.selectById(id);
        if(shijianrenshuOrder !=null){
            //entity转view
            ShijianrenshuOrderView view = new ShijianrenshuOrderView();
            BeanUtils.copyProperties( shijianrenshuOrder , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shijianrenshuOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 任务
            //级联表
            ShijianrenshuEntity shijianrenshu = shijianrenshuService.selectById(shijianrenshuOrder.getShijianrenshuId());
            if(shijianrenshu != null){
            BeanUtils.copyProperties( shijianrenshu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShijianrenshuId(shijianrenshu.getId());
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
    public R save(@RequestBody ShijianrenshuOrderEntity shijianrenshuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shijianrenshuOrder:{}",this.getClass().getName(),shijianrenshuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shijianrenshuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        shijianrenshuOrder.setCreateTime(new Date());
        shijianrenshuOrder.setInsertTime(new Date());
        shijianrenshuOrderService.insert(shijianrenshuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShijianrenshuOrderEntity shijianrenshuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shijianrenshuOrder:{}",this.getClass().getName(),shijianrenshuOrder.toString());
        ShijianrenshuOrderEntity oldShijianrenshuOrderEntity = shijianrenshuOrderService.selectById(shijianrenshuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shijianrenshuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shijianrenshuOrder.getShijianrenshuOrderFile()) || "null".equals(shijianrenshuOrder.getShijianrenshuOrderFile())){
                shijianrenshuOrder.setShijianrenshuOrderFile(null);
        }

            shijianrenshuOrderService.updateById(shijianrenshuOrder);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ShijianrenshuOrderEntity shijianrenshuOrderEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,shijianrenshuOrderEntity:{}",this.getClass().getName(),shijianrenshuOrderEntity.toString());

        ShijianrenshuOrderEntity oldShijianrenshuOrder = shijianrenshuOrderService.selectById(shijianrenshuOrderEntity.getId());//查询原先数据

        if(shijianrenshuOrderEntity.getShijianrenshuOrderYesnoTypes() == 2){//通过
            shijianrenshuOrderEntity.setShijianrenshuOrderTypes(106);//任务完成

            ShijianrenshuEntity shijianrenshuEntity = shijianrenshuService.selectById(oldShijianrenshuOrder.getShijianrenshuId());
            if(shijianrenshuEntity != null){
                shijianrenshuEntity.setRenwuzhuangtaiTypes(3);
                shijianrenshuService.updateById(shijianrenshuEntity);
            }
        }else if(shijianrenshuOrderEntity.getShijianrenshuOrderYesnoTypes() == 3){//拒绝
            shijianrenshuOrderEntity.setShijianrenshuOrderTypes(108);//改为任务已提交
        }
        shijianrenshuOrderEntity.setShijianrenshuOrderShenheTime(new Date());//审核时间
        shijianrenshuOrderService.updateById(shijianrenshuOrderEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShijianrenshuOrderEntity> oldShijianrenshuOrderList =shijianrenshuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shijianrenshuOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShijianrenshuOrderEntity> shijianrenshuOrderList = new ArrayList<>();//上传的东西
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
                            ShijianrenshuOrderEntity shijianrenshuOrderEntity = new ShijianrenshuOrderEntity();
//                            shijianrenshuOrderEntity.setShijianrenshuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            shijianrenshuOrderEntity.setShijianrenshuId(Integer.valueOf(data.get(0)));   //任务 要改的
//                            shijianrenshuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shijianrenshuOrderEntity.setShijianrenshuOrderTypes(Integer.valueOf(data.get(0)));   //状态类型 要改的
//                            shijianrenshuOrderEntity.setInsertTime(date);//时间
//                            shijianrenshuOrderEntity.setShijianrenshuOrderFile(data.get(0));                    //成果文件 要改的
//                            shijianrenshuOrderEntity.setShijianrenshuOrderYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            shijianrenshuOrderEntity.setShijianrenshuOrderYesnoText(data.get(0));                    //审核回复 要改的
//                            shijianrenshuOrderEntity.setShijianrenshuOrderShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            shijianrenshuOrderEntity.setCreateTime(date);//时间
                            shijianrenshuOrderList.add(shijianrenshuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("shijianrenshuOrderUuidNumber")){
                                    List<String> shijianrenshuOrderUuidNumber = seachFields.get("shijianrenshuOrderUuidNumber");
                                    shijianrenshuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shijianrenshuOrderUuidNumber = new ArrayList<>();
                                    shijianrenshuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shijianrenshuOrderUuidNumber",shijianrenshuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<ShijianrenshuOrderEntity> shijianrenshuOrderEntities_shijianrenshuOrderUuidNumber = shijianrenshuOrderService.selectList(new EntityWrapper<ShijianrenshuOrderEntity>().in("shijianrenshu_order_uuid_number", seachFields.get("shijianrenshuOrderUuidNumber")));
                        if(shijianrenshuOrderEntities_shijianrenshuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShijianrenshuOrderEntity s:shijianrenshuOrderEntities_shijianrenshuOrderUuidNumber){
                                repeatFields.add(s.getShijianrenshuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shijianrenshuOrderService.insertBatch(shijianrenshuOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shijianrenshuOrderService.queryPage(params);

        //字典表数据转换
        List<ShijianrenshuOrderView> list =(List<ShijianrenshuOrderView>)page.getList();
        for(ShijianrenshuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShijianrenshuOrderEntity shijianrenshuOrder = shijianrenshuOrderService.selectById(id);
            if(shijianrenshuOrder !=null){


                //entity转view
                ShijianrenshuOrderView view = new ShijianrenshuOrderView();
                BeanUtils.copyProperties( shijianrenshuOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shijianrenshuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    ShijianrenshuEntity shijianrenshu = shijianrenshuService.selectById(shijianrenshuOrder.getShijianrenshuId());
                if(shijianrenshu != null){
                    BeanUtils.copyProperties( shijianrenshu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShijianrenshuId(shijianrenshu.getId());
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
    public R add(@RequestBody ShijianrenshuOrderEntity shijianrenshuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shijianrenshuOrder:{}",this.getClass().getName(),shijianrenshuOrder.toString());
            ShijianrenshuEntity shijianrenshuEntity = shijianrenshuService.selectById(shijianrenshuOrder.getShijianrenshuId());
            if(shijianrenshuEntity == null){
                return R.error(511,"查不到该任务");
            }


        ShijianrenshuOrderEntity one = shijianrenshuOrderService.selectOne(new EntityWrapper<ShijianrenshuOrderEntity>()
                .eq("yonghu_id", shijianrenshuOrder.getYonghuId())
                .eq("shijianrenshu_id", shijianrenshuOrder.getShijianrenshuId())
                .eq("shijianrenshu_order_types", 1)
        );
            if(one != null)
                return R.error("该用户已对该任务进行申请,请耐心等待审核");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            shijianrenshuOrder.setShijianrenshuOrderTypes(101); //设置订单状态为已申请接单
            shijianrenshuOrder.setYonghuId(userId); //设置订单支付人id
            shijianrenshuOrder.setShijianrenshuOrderUuidNumber(String.valueOf(new Date().getTime()));
            shijianrenshuOrder.setInsertTime(new Date());
            shijianrenshuOrder.setCreateTime(new Date());
                shijianrenshuOrderService.insert(shijianrenshuOrder);//新增订单
            return R.ok();
    }


    /**
    * 取消接单
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ShijianrenshuOrderEntity shijianrenshuOrder = shijianrenshuOrderService.selectById(id);
            shijianrenshuOrder.setShijianrenshuOrderTypes(102);//设置订单状态为已取消接单
            shijianrenshuOrderService.updateById(shijianrenshuOrder);//根据id更新

            return R.ok();
    }

    /**
     * 同意接单
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ShijianrenshuOrderEntity  shijianrenshuOrderEntity = shijianrenshuOrderService.selectById(id);
        shijianrenshuOrderEntity.setShijianrenshuOrderTypes(103);//设置订单状态为已同意接单

        ShijianrenshuEntity shijianrenshuEntity = shijianrenshuService.selectById(shijianrenshuOrderEntity.getShijianrenshuId());
        if(shijianrenshuEntity.getRenwuzhuangtaiTypes()!=1){
            return R.error("该任务已有人接单或者有人做完,不能同意此接单,请核实");
        }

        shijianrenshuEntity.setRenwuzhuangtaiTypes(2);
        shijianrenshuService.updateById(shijianrenshuEntity);//状态改为已接单


        shijianrenshuOrderService.updateById( shijianrenshuOrderEntity);

        return R.ok();
    }


    /**
     * 提交任务成果
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id,String shijianrenshuOrderFile){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        if(shijianrenshuOrderFile == null || shijianrenshuOrderFile.equals("")){
            return R.error("必须上传成果文件");
        }
        ShijianrenshuOrderEntity  shijianrenshuOrderEntity = shijianrenshuOrderService.selectById(id);
        shijianrenshuOrderEntity.setShijianrenshuOrderTypes(104);//设置订单状态为提交任务成果
        shijianrenshuOrderEntity.setShijianrenshuOrderFile(shijianrenshuOrderFile);
        shijianrenshuOrderEntity.setShijianrenshuOrderYesnoTypes(1);//待审核
        shijianrenshuOrderService.updateById( shijianrenshuOrderEntity);
        return R.ok();
    }

}
