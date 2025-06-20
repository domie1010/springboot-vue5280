package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShijianrenshuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shijianrenshu")
public class ShijianrenshuView extends ShijianrenshuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 任务类型的值
	*/
	@ColumnInfo(comment="任务类型的字典表值",type="varchar(200)")
	private String shijianrenshuValue;
	/**
	* 任务状态的值
	*/
	@ColumnInfo(comment="任务状态的字典表值",type="varchar(200)")
	private String renwuzhuangtaiValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;

	//级联表 企业
		/**
		* 企业名称
		*/

		@ColumnInfo(comment="企业名称",type="varchar(200)")
		private String qiyeName;
		/**
		* 企业联系人
		*/

		@ColumnInfo(comment="企业联系人",type="varchar(200)")
		private String qiyeLianxiren;
		/**
		* 企业联系方式
		*/

		@ColumnInfo(comment="企业联系方式",type="varchar(200)")
		private String qiyePhone;
		/**
		* 企业logo
		*/

		@ColumnInfo(comment="企业logo",type="varchar(200)")
		private String qiyePhoto;
		/**
		* 企业位置
		*/

		@ColumnInfo(comment="企业位置",type="varchar(200)")
		private String qiyeAddress;
		/**
		* 企业邮箱
		*/

		@ColumnInfo(comment="企业邮箱",type="varchar(200)")
		private String qiyeEmail;
		/**
		* 行业
		*/
		@ColumnInfo(comment="行业",type="int(11)")
		private Integer hangyeTypes;
			/**
			* 行业的值
			*/
			@ColumnInfo(comment="行业的字典表值",type="varchar(200)")
			private String hangyeValue;



	public ShijianrenshuView() {

	}

	public ShijianrenshuView(ShijianrenshuEntity shijianrenshuEntity) {
		try {
			BeanUtils.copyProperties(this, shijianrenshuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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
	//当前表的
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


	//级联表的get和set 企业

		/**
		* 获取： 企业名称
		*/
		public String getQiyeName() {
			return qiyeName;
		}
		/**
		* 设置： 企业名称
		*/
		public void setQiyeName(String qiyeName) {
			this.qiyeName = qiyeName;
		}

		/**
		* 获取： 企业联系人
		*/
		public String getQiyeLianxiren() {
			return qiyeLianxiren;
		}
		/**
		* 设置： 企业联系人
		*/
		public void setQiyeLianxiren(String qiyeLianxiren) {
			this.qiyeLianxiren = qiyeLianxiren;
		}

		/**
		* 获取： 企业联系方式
		*/
		public String getQiyePhone() {
			return qiyePhone;
		}
		/**
		* 设置： 企业联系方式
		*/
		public void setQiyePhone(String qiyePhone) {
			this.qiyePhone = qiyePhone;
		}

		/**
		* 获取： 企业logo
		*/
		public String getQiyePhoto() {
			return qiyePhoto;
		}
		/**
		* 设置： 企业logo
		*/
		public void setQiyePhoto(String qiyePhoto) {
			this.qiyePhoto = qiyePhoto;
		}

		/**
		* 获取： 企业位置
		*/
		public String getQiyeAddress() {
			return qiyeAddress;
		}
		/**
		* 设置： 企业位置
		*/
		public void setQiyeAddress(String qiyeAddress) {
			this.qiyeAddress = qiyeAddress;
		}

		/**
		* 获取： 企业邮箱
		*/
		public String getQiyeEmail() {
			return qiyeEmail;
		}
		/**
		* 设置： 企业邮箱
		*/
		public void setQiyeEmail(String qiyeEmail) {
			this.qiyeEmail = qiyeEmail;
		}
		/**
		* 获取： 行业
		*/
		public Integer getHangyeTypes() {
			return hangyeTypes;
		}
		/**
		* 设置： 行业
		*/
		public void setHangyeTypes(Integer hangyeTypes) {
			this.hangyeTypes = hangyeTypes;
		}


			/**
			* 获取： 行业的值
			*/
			public String getHangyeValue() {
				return hangyeValue;
			}
			/**
			* 设置： 行业的值
			*/
			public void setHangyeValue(String hangyeValue) {
				this.hangyeValue = hangyeValue;
			}


	@Override
	public String toString() {
		return "ShijianrenshuView{" +
			", shijianrenshuValue=" + shijianrenshuValue +
			", renwuzhuangtaiValue=" + renwuzhuangtaiValue +
			", shangxiaValue=" + shangxiaValue +
			", qiyeName=" + qiyeName +
			", qiyeLianxiren=" + qiyeLianxiren +
			", qiyePhone=" + qiyePhone +
			", qiyePhoto=" + qiyePhoto +
			", qiyeAddress=" + qiyeAddress +
			", qiyeEmail=" + qiyeEmail +
			"} " + super.toString();
	}
}
