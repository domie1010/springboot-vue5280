package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShijianrenshuCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shijianrenshu_collection")
public class ShijianrenshuCollectionView extends ShijianrenshuCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String shijianrenshuCollectionValue;

	//级联表 任务
					 
		/**
		* 任务 的 企业
		*/
		@ColumnInfo(comment="企业",type="int(11)")
		private Integer shijianrenshuQiyeId;
		/**
		* 任务题目
		*/

		@ColumnInfo(comment="任务题目",type="varchar(200)")
		private String shijianrenshuName;
		/**
		* 任务编号
		*/

		@ColumnInfo(comment="任务编号",type="varchar(200)")
		private String shijianrenshuUuidNumber;
		/**
		* 任务照片
		*/

		@ColumnInfo(comment="任务照片",type="varchar(200)")
		private String shijianrenshuPhoto;
		/**
		* 任务类型
		*/
		@ColumnInfo(comment="任务类型",type="int(11)")
		private Integer shijianrenshuTypes;
			/**
			* 任务类型的值
			*/
			@ColumnInfo(comment="任务类型的字典表值",type="varchar(200)")
			private String shijianrenshuValue;
		/**
		* 任务热度
		*/

		@ColumnInfo(comment="任务热度",type="int(11)")
		private Integer shijianrenshuClicknum;
		/**
		* 任务要求
		*/

		@ColumnInfo(comment="任务要求",type="text")
		private String shijianrenshuText;
		/**
		* 任务内容
		*/

		@ColumnInfo(comment="任务内容",type="text")
		private String shijianrenshuContent;
		/**
		* 任务状态
		*/
		@ColumnInfo(comment="任务状态",type="int(11)")
		private Integer renwuzhuangtaiTypes;
			/**
			* 任务状态的值
			*/
			@ColumnInfo(comment="任务状态的字典表值",type="varchar(200)")
			private String renwuzhuangtaiValue;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;



	public ShijianrenshuCollectionView() {

	}

	public ShijianrenshuCollectionView(ShijianrenshuCollectionEntity shijianrenshuCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, shijianrenshuCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getShijianrenshuCollectionValue() {
		return shijianrenshuCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setShijianrenshuCollectionValue(String shijianrenshuCollectionValue) {
		this.shijianrenshuCollectionValue = shijianrenshuCollectionValue;
	}


	//级联表的get和set 任务
		/**
		* 获取：任务 的 企业
		*/
		public Integer getShijianrenshuQiyeId() {
			return shijianrenshuQiyeId;
		}
		/**
		* 设置：任务 的 企业
		*/
		public void setShijianrenshuQiyeId(Integer shijianrenshuQiyeId) {
			this.shijianrenshuQiyeId = shijianrenshuQiyeId;
		}

		/**
		* 获取： 任务题目
		*/
		public String getShijianrenshuName() {
			return shijianrenshuName;
		}
		/**
		* 设置： 任务题目
		*/
		public void setShijianrenshuName(String shijianrenshuName) {
			this.shijianrenshuName = shijianrenshuName;
		}

		/**
		* 获取： 任务编号
		*/
		public String getShijianrenshuUuidNumber() {
			return shijianrenshuUuidNumber;
		}
		/**
		* 设置： 任务编号
		*/
		public void setShijianrenshuUuidNumber(String shijianrenshuUuidNumber) {
			this.shijianrenshuUuidNumber = shijianrenshuUuidNumber;
		}

		/**
		* 获取： 任务照片
		*/
		public String getShijianrenshuPhoto() {
			return shijianrenshuPhoto;
		}
		/**
		* 设置： 任务照片
		*/
		public void setShijianrenshuPhoto(String shijianrenshuPhoto) {
			this.shijianrenshuPhoto = shijianrenshuPhoto;
		}
		/**
		* 获取： 任务类型
		*/
		public Integer getShijianrenshuTypes() {
			return shijianrenshuTypes;
		}
		/**
		* 设置： 任务类型
		*/
		public void setShijianrenshuTypes(Integer shijianrenshuTypes) {
			this.shijianrenshuTypes = shijianrenshuTypes;
		}


			/**
			* 获取： 任务类型的值
			*/
			public String getShijianrenshuValue() {
				return shijianrenshuValue;
			}
			/**
			* 设置： 任务类型的值
			*/
			public void setShijianrenshuValue(String shijianrenshuValue) {
				this.shijianrenshuValue = shijianrenshuValue;
			}

		/**
		* 获取： 任务热度
		*/
		public Integer getShijianrenshuClicknum() {
			return shijianrenshuClicknum;
		}
		/**
		* 设置： 任务热度
		*/
		public void setShijianrenshuClicknum(Integer shijianrenshuClicknum) {
			this.shijianrenshuClicknum = shijianrenshuClicknum;
		}

		/**
		* 获取： 任务要求
		*/
		public String getShijianrenshuText() {
			return shijianrenshuText;
		}
		/**
		* 设置： 任务要求
		*/
		public void setShijianrenshuText(String shijianrenshuText) {
			this.shijianrenshuText = shijianrenshuText;
		}

		/**
		* 获取： 任务内容
		*/
		public String getShijianrenshuContent() {
			return shijianrenshuContent;
		}
		/**
		* 设置： 任务内容
		*/
		public void setShijianrenshuContent(String shijianrenshuContent) {
			this.shijianrenshuContent = shijianrenshuContent;
		}
		/**
		* 获取： 任务状态
		*/
		public Integer getRenwuzhuangtaiTypes() {
			return renwuzhuangtaiTypes;
		}
		/**
		* 设置： 任务状态
		*/
		public void setRenwuzhuangtaiTypes(Integer renwuzhuangtaiTypes) {
			this.renwuzhuangtaiTypes = renwuzhuangtaiTypes;
		}


			/**
			* 获取： 任务状态的值
			*/
			public String getRenwuzhuangtaiValue() {
				return renwuzhuangtaiValue;
			}
			/**
			* 设置： 任务状态的值
			*/
			public void setRenwuzhuangtaiValue(String renwuzhuangtaiValue) {
				this.renwuzhuangtaiValue = renwuzhuangtaiValue;
			}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "ShijianrenshuCollectionView{" +
			", shijianrenshuCollectionValue=" + shijianrenshuCollectionValue +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", shijianrenshuName=" + shijianrenshuName +
			", shijianrenshuUuidNumber=" + shijianrenshuUuidNumber +
			", shijianrenshuPhoto=" + shijianrenshuPhoto +
			", shijianrenshuClicknum=" + shijianrenshuClicknum +
			", shijianrenshuText=" + shijianrenshuText +
			", shijianrenshuContent=" + shijianrenshuContent +
			"} " + super.toString();
	}
}
