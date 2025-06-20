package com.entity.model;

import com.entity.ShijianrenshuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 任务
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShijianrenshuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 企业
     */
    private Integer qiyeId;


    /**
     * 任务题目
     */
    private String shijianrenshuName;


    /**
     * 任务编号
     */
    private String shijianrenshuUuidNumber;


    /**
     * 任务照片
     */
    private String shijianrenshuPhoto;


    /**
     * 任务类型
     */
    private Integer shijianrenshuTypes;


    /**
     * 任务热度
     */
    private Integer shijianrenshuClicknum;


    /**
     * 任务要求
     */
    private String shijianrenshuText;


    /**
     * 任务内容
     */
    private String shijianrenshuContent;


    /**
     * 任务状态
     */
    private Integer renwuzhuangtaiTypes;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：企业
	 */
    public Integer getQiyeId() {
        return qiyeId;
    }


    /**
	 * 设置：企业
	 */
    public void setQiyeId(Integer qiyeId) {
        this.qiyeId = qiyeId;
    }
    /**
	 * 获取：任务题目
	 */
    public String getShijianrenshuName() {
        return shijianrenshuName;
    }


    /**
	 * 设置：任务题目
	 */
    public void setShijianrenshuName(String shijianrenshuName) {
        this.shijianrenshuName = shijianrenshuName;
    }
    /**
	 * 获取：任务编号
	 */
    public String getShijianrenshuUuidNumber() {
        return shijianrenshuUuidNumber;
    }


    /**
	 * 设置：任务编号
	 */
    public void setShijianrenshuUuidNumber(String shijianrenshuUuidNumber) {
        this.shijianrenshuUuidNumber = shijianrenshuUuidNumber;
    }
    /**
	 * 获取：任务照片
	 */
    public String getShijianrenshuPhoto() {
        return shijianrenshuPhoto;
    }


    /**
	 * 设置：任务照片
	 */
    public void setShijianrenshuPhoto(String shijianrenshuPhoto) {
        this.shijianrenshuPhoto = shijianrenshuPhoto;
    }
    /**
	 * 获取：任务类型
	 */
    public Integer getShijianrenshuTypes() {
        return shijianrenshuTypes;
    }


    /**
	 * 设置：任务类型
	 */
    public void setShijianrenshuTypes(Integer shijianrenshuTypes) {
        this.shijianrenshuTypes = shijianrenshuTypes;
    }
    /**
	 * 获取：任务热度
	 */
    public Integer getShijianrenshuClicknum() {
        return shijianrenshuClicknum;
    }


    /**
	 * 设置：任务热度
	 */
    public void setShijianrenshuClicknum(Integer shijianrenshuClicknum) {
        this.shijianrenshuClicknum = shijianrenshuClicknum;
    }
    /**
	 * 获取：任务要求
	 */
    public String getShijianrenshuText() {
        return shijianrenshuText;
    }


    /**
	 * 设置：任务要求
	 */
    public void setShijianrenshuText(String shijianrenshuText) {
        this.shijianrenshuText = shijianrenshuText;
    }
    /**
	 * 获取：任务内容
	 */
    public String getShijianrenshuContent() {
        return shijianrenshuContent;
    }


    /**
	 * 设置：任务内容
	 */
    public void setShijianrenshuContent(String shijianrenshuContent) {
        this.shijianrenshuContent = shijianrenshuContent;
    }
    /**
	 * 获取：任务状态
	 */
    public Integer getRenwuzhuangtaiTypes() {
        return renwuzhuangtaiTypes;
    }


    /**
	 * 设置：任务状态
	 */
    public void setRenwuzhuangtaiTypes(Integer renwuzhuangtaiTypes) {
        this.renwuzhuangtaiTypes = renwuzhuangtaiTypes;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发布时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
