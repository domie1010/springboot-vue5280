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
 * 任务订单
 *
 * @author 
 * @email
 */
@TableName("shijianrenshu_order")
public class ShijianrenshuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShijianrenshuOrderEntity() {

	}

	public ShijianrenshuOrderEntity(T t) {
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
     * 订单号
     */
    @ColumnInfo(comment="订单号",type="varchar(200)")
    @TableField(value = "shijianrenshu_order_uuid_number")

    private String shijianrenshuOrderUuidNumber;


    /**
     * 任务
     */
    @ColumnInfo(comment="任务",type="int(11)")
    @TableField(value = "shijianrenshu_id")

    private Integer shijianrenshuId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 状态类型
     */
    @ColumnInfo(comment="状态类型",type="int(11)")
    @TableField(value = "shijianrenshu_order_types")

    private Integer shijianrenshuOrderTypes;


    /**
     * 申请接单时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请接单时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 成果文件
     */
    @ColumnInfo(comment="成果文件",type="varchar(200)")
    @TableField(value = "shijianrenshu_order_file")

    private String shijianrenshuOrderFile;


    /**
     * 审核状态
     */
    @ColumnInfo(comment="审核状态",type="int(11)")
    @TableField(value = "shijianrenshu_order_yesno_types")

    private Integer shijianrenshuOrderYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="text")
    @TableField(value = "shijianrenshu_order_yesno_text")

    private String shijianrenshuOrderYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "shijianrenshu_order_shenhe_time")

    private Date shijianrenshuOrderShenheTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单号
	 */
    public String getShijianrenshuOrderUuidNumber() {
        return shijianrenshuOrderUuidNumber;
    }
    /**
	 * 设置：订单号
	 */

    public void setShijianrenshuOrderUuidNumber(String shijianrenshuOrderUuidNumber) {
        this.shijianrenshuOrderUuidNumber = shijianrenshuOrderUuidNumber;
    }
    /**
	 * 获取：任务
	 */
    public Integer getShijianrenshuId() {
        return shijianrenshuId;
    }
    /**
	 * 设置：任务
	 */

    public void setShijianrenshuId(Integer shijianrenshuId) {
        this.shijianrenshuId = shijianrenshuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：状态类型
	 */
    public Integer getShijianrenshuOrderTypes() {
        return shijianrenshuOrderTypes;
    }
    /**
	 * 设置：状态类型
	 */

    public void setShijianrenshuOrderTypes(Integer shijianrenshuOrderTypes) {
        this.shijianrenshuOrderTypes = shijianrenshuOrderTypes;
    }
    /**
	 * 获取：申请接单时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：申请接单时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：成果文件
	 */
    public String getShijianrenshuOrderFile() {
        return shijianrenshuOrderFile;
    }
    /**
	 * 设置：成果文件
	 */

    public void setShijianrenshuOrderFile(String shijianrenshuOrderFile) {
        this.shijianrenshuOrderFile = shijianrenshuOrderFile;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getShijianrenshuOrderYesnoTypes() {
        return shijianrenshuOrderYesnoTypes;
    }
    /**
	 * 设置：审核状态
	 */

    public void setShijianrenshuOrderYesnoTypes(Integer shijianrenshuOrderYesnoTypes) {
        this.shijianrenshuOrderYesnoTypes = shijianrenshuOrderYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getShijianrenshuOrderYesnoText() {
        return shijianrenshuOrderYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setShijianrenshuOrderYesnoText(String shijianrenshuOrderYesnoText) {
        this.shijianrenshuOrderYesnoText = shijianrenshuOrderYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getShijianrenshuOrderShenheTime() {
        return shijianrenshuOrderShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setShijianrenshuOrderShenheTime(Date shijianrenshuOrderShenheTime) {
        this.shijianrenshuOrderShenheTime = shijianrenshuOrderShenheTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShijianrenshuOrder{" +
            ", id=" + id +
            ", shijianrenshuOrderUuidNumber=" + shijianrenshuOrderUuidNumber +
            ", shijianrenshuId=" + shijianrenshuId +
            ", yonghuId=" + yonghuId +
            ", shijianrenshuOrderTypes=" + shijianrenshuOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", shijianrenshuOrderFile=" + shijianrenshuOrderFile +
            ", shijianrenshuOrderYesnoTypes=" + shijianrenshuOrderYesnoTypes +
            ", shijianrenshuOrderYesnoText=" + shijianrenshuOrderYesnoText +
            ", shijianrenshuOrderShenheTime=" + DateUtil.convertString(shijianrenshuOrderShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
