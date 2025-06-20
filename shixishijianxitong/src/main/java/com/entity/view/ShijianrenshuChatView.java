package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShijianrenshuChatEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务交流
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shijianrenshu_chat")
public class ShijianrenshuChatView extends ShijianrenshuChatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 状态的值
	*/
	@ColumnInfo(comment="状态的字典表值",type="varchar(200)")
	private String zhuangtaiValue;
	/**
	* 数据类型的值
	*/
	@ColumnInfo(comment="数据类型的字典表值",type="varchar(200)")
	private String shijianrenshuChatValue;

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



	public ShijianrenshuChatView() {

	}

	public ShijianrenshuChatView(ShijianrenshuChatEntity shijianrenshuChatEntity) {
		try {
			BeanUtils.copyProperties(this, shijianrenshuChatEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 状态的值
	*/
	public String getZhuangtaiValue() {
		return zhuangtaiValue;
	}
	/**
	* 设置： 状态的值
	*/
	public void setZhuangtaiValue(String zhuangtaiValue) {
		this.zhuangtaiValue = zhuangtaiValue;
	}
	//当前表的
	/**
	* 获取： 数据类型的值
	*/
	public String getShijianrenshuChatValue() {
		return shijianrenshuChatValue;
	}
	/**
	* 设置： 数据类型的值
	*/
	public void setShijianrenshuChatValue(String shijianrenshuChatValue) {
		this.shijianrenshuChatValue = shijianrenshuChatValue;
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
		return "ShijianrenshuChatView{" +
			", zhuangtaiValue=" + zhuangtaiValue +
			", shijianrenshuChatValue=" + shijianrenshuChatValue +
			", qiyeName=" + qiyeName +
			", qiyeLianxiren=" + qiyeLianxiren +
			", qiyePhone=" + qiyePhone +
			", qiyePhoto=" + qiyePhoto +
			", qiyeAddress=" + qiyeAddress +
			", qiyeEmail=" + qiyeEmail +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
