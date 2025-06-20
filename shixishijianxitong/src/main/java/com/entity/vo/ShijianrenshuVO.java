package com.entity.vo;

import com.entity.ShijianrenshuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 任务
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shijianrenshu")
public class ShijianrenshuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 企业
     */

    @TableField(value = "qiye_id")
    private Integer qiyeId;


    /**
     * 任务题目
     */

    @TableField(value = "shijianrenshu_name")
    private String shijianrenshuName;


    /**
     * 任务编号
     */

    @TableField(value = "shijianrenshu_uuid_number")
    private String shijianrenshuUuidNumber;


    /**
     * 任务照片
     */

    @TableField(value = "shijianrenshu_photo")
    private String shijianrenshuPhoto;


    /**
     * 任务类型
     */

    @TableField(value = "shijianrenshu_types")
    private Integer shijianrenshuTypes;


    /**
     * 任务热度
     */

    @TableField(value = "shijianrenshu_clicknum")
    private Integer shijianrenshuClicknum;


    /**
     * 任务要求
     */

    @TableField(value = "shijianrenshu_text")
    private String shijianrenshuText;


    /**
     * 任务内容
     */

    @TableField(value = "shijianrenshu_content")
    private String shijianrenshuContent;


    /**
     * 任务状态
     */

    @TableField(value = "renwuzhuangtai_types")
    private Integer renwuzhuangtaiTypes;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：企业
	 */
    public Integer getQiyeId() {
        return qiyeId;
    }


    /**
	 * 获取：企业
	 */

    public void setQiyeId(Integer qiyeId) {
        this.qiyeId = qiyeId;
    }
    /**
	 * 设置：任务题目
	 */
    public String getShijianrenshuName() {
        return shijianrenshuName;
    }


    /**
	 * 获取：任务题目
	 */

    public void setShijianrenshuName(String shijianrenshuName) {
        this.shijianrenshuName = shijianrenshuName;
    }
    /**
	 * 设置：任务编号
	 */
    public String getShijianrenshuUuidNumber() {
        return shijianrenshuUuidNumber;
    }


    /**
	 * 获取：任务编号
	 */

    public void setShijianrenshuUuidNumber(String shijianrenshuUuidNumber) {
        this.shijianrenshuUuidNumber = shijianrenshuUuidNumber;
    }
    /**
	 * 设置：任务照片
	 */
    public String getShijianrenshuPhoto() {
        return shijianrenshuPhoto;
    }


    /**
	 * 获取：任务照片
	 */

    public void setShijianrenshuPhoto(String shijianrenshuPhoto) {
        this.shijianrenshuPhoto = shijianrenshuPhoto;
    }
    /**
	 * 设置：任务类型
	 */
    public Integer getShijianrenshuTypes() {
        return shijianrenshuTypes;
    }


    /**
	 * 获取：任务类型
	 */

    public void setShijianrenshuTypes(Integer shijianrenshuTypes) {
        this.shijianrenshuTypes = shijianrenshuTypes;
    }
    /**
	 * 设置：任务热度
	 */
    public Integer getShijianrenshuClicknum() {
        return shijianrenshuClicknum;
    }


    /**
	 * 获取：任务热度
	 */

    public void setShijianrenshuClicknum(Integer shijianrenshuClicknum) {
        this.shijianrenshuClicknum = shijianrenshuClicknum;
    }
    /**
	 * 设置：任务要求
	 */
    public String getShijianrenshuText() {
        return shijianrenshuText;
    }


    /**
	 * 获取：任务要求
	 */

    public void setShijianrenshuText(String shijianrenshuText) {
        this.shijianrenshuText = shijianrenshuText;
    }
    /**
	 * 设置：任务内容
	 */
    public String getShijianrenshuContent() {
        return shijianrenshuContent;
    }


    /**
	 * 获取：任务内容
	 */

    public void setShijianrenshuContent(String shijianrenshuContent) {
        this.shijianrenshuContent = shijianrenshuContent;
    }
    /**
	 * 设置：任务状态
	 */
    public Integer getRenwuzhuangtaiTypes() {
        return renwuzhuangtaiTypes;
    }


    /**
	 * 获取：任务状态
	 */

    public void setRenwuzhuangtaiTypes(Integer renwuzhuangtaiTypes) {
        this.renwuzhuangtaiTypes = renwuzhuangtaiTypes;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：发布时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
