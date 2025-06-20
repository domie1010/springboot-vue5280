package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 任务
 *
 * @author 
 * @email
 */
@TableName("shijianrenshu")
public class ShijianrenshuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShijianrenshuEntity() {

	}

	public ShijianrenshuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 企业
     */
    @ColumnInfo(comment="企业",type="int(11)")
    @TableField(value = "qiye_id")

    private Integer qiyeId;


    /**
     * 任务题目
     */
    @ColumnInfo(comment="任务题目",type="varchar(200)")
    @TableField(value = "shijianrenshu_name")

    private String shijianrenshuName;


    /**
     * 任务编号
     */
    @ColumnInfo(comment="任务编号",type="varchar(200)")
    @TableField(value = "shijianrenshu_uuid_number")

    private String shijianrenshuUuidNumber;


    /**
     * 任务照片
     */
    @ColumnInfo(comment="任务照片",type="varchar(200)")
    @TableField(value = "shijianrenshu_photo")

    private String shijianrenshuPhoto;


    /**
     * 任务类型
     */
    @ColumnInfo(comment="任务类型",type="int(11)")
    @TableField(value = "shijianrenshu_types")

    private Integer shijianrenshuTypes;


    /**
     * 任务热度
     */
    @ColumnInfo(comment="任务热度",type="int(11)")
    @TableField(value = "shijianrenshu_clicknum")

    private Integer shijianrenshuClicknum;


    /**
     * 任务要求
     */
    @ColumnInfo(comment="任务要求",type="text")
    @TableField(value = "shijianrenshu_text")

    private String shijianrenshuText;


    /**
     * 任务内容
     */
    @ColumnInfo(comment="任务内容",type="text")
    @TableField(value = "shijianrenshu_content")

    private String shijianrenshuContent;


    /**
     * 任务状态
     */
    @ColumnInfo(comment="任务状态",type="int(11)")
    @TableField(value = "renwuzhuangtai_types")

    private Integer renwuzhuangtaiTypes;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="发布时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shijianrenshu{" +
            ", id=" + id +
            ", qiyeId=" + qiyeId +
            ", shijianrenshuName=" + shijianrenshuName +
            ", shijianrenshuUuidNumber=" + shijianrenshuUuidNumber +
            ", shijianrenshuPhoto=" + shijianrenshuPhoto +
            ", shijianrenshuTypes=" + shijianrenshuTypes +
            ", shijianrenshuClicknum=" + shijianrenshuClicknum +
            ", shijianrenshuText=" + shijianrenshuText +
            ", shijianrenshuContent=" + shijianrenshuContent +
            ", renwuzhuangtaiTypes=" + renwuzhuangtaiTypes +
            ", shangxiaTypes=" + shangxiaTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
