package com.entity.view;

import com.entity.LiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 留言
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("liuyan")
public class LiuyanView extends LiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 留言类型的值
		*/
		private String liuyanValue;



		//级联表 zuke
			/**
			* 租客姓名
			*/
			private String zukeName;
			/**
			* 租客手机号
			*/
			private String zukePhone;
			/**
			* 租客身份证号
			*/
			private String zukeIdNumber;
			/**
			* 电子邮箱
			*/
			private String zukeEmail;
			/**
			* 租客头像
			*/
			private String zukePhoto;
			/**
			* 单元号
			*/
			private Integer danyuanTypes;
				/**
				* 单元号的值
				*/
				private String danyuanValue;
			/**
			* 使用
			*/
			private Integer shiyongTypes;
				/**
				* 使用的值
				*/
				private String shiyongValue;
			/**
			* 余额
			*/
			private Double newMoney;

	public LiuyanView() {

	}

	public LiuyanView(LiuyanEntity liuyanEntity) {
		try {
			BeanUtils.copyProperties(this, liuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 留言类型的值
			*/
			public String getLiuyanValue() {
				return liuyanValue;
			}
			/**
			* 设置： 留言类型的值
			*/
			public void setLiuyanValue(String liuyanValue) {
				this.liuyanValue = liuyanValue;
			}


















				//级联表的get和set zuke
					/**
					* 获取： 租客姓名
					*/
					public String getZukeName() {
						return zukeName;
					}
					/**
					* 设置： 租客姓名
					*/
					public void setZukeName(String zukeName) {
						this.zukeName = zukeName;
					}
					/**
					* 获取： 租客手机号
					*/
					public String getZukePhone() {
						return zukePhone;
					}
					/**
					* 设置： 租客手机号
					*/
					public void setZukePhone(String zukePhone) {
						this.zukePhone = zukePhone;
					}
					/**
					* 获取： 租客身份证号
					*/
					public String getZukeIdNumber() {
						return zukeIdNumber;
					}
					/**
					* 设置： 租客身份证号
					*/
					public void setZukeIdNumber(String zukeIdNumber) {
						this.zukeIdNumber = zukeIdNumber;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getZukeEmail() {
						return zukeEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setZukeEmail(String zukeEmail) {
						this.zukeEmail = zukeEmail;
					}
					/**
					* 获取： 租客头像
					*/
					public String getZukePhoto() {
						return zukePhoto;
					}
					/**
					* 设置： 租客头像
					*/
					public void setZukePhoto(String zukePhoto) {
						this.zukePhoto = zukePhoto;
					}
					/**
					* 获取： 单元号
					*/
					public Integer getDanyuanTypes() {
						return danyuanTypes;
					}
					/**
					* 设置： 单元号
					*/
					public void setDanyuanTypes(Integer danyuanTypes) {
						this.danyuanTypes = danyuanTypes;
					}


						/**
						* 获取： 单元号的值
						*/
						public String getDanyuanValue() {
							return danyuanValue;
						}
						/**
						* 设置： 单元号的值
						*/
						public void setDanyuanValue(String danyuanValue) {
							this.danyuanValue = danyuanValue;
						}
					/**
					* 获取： 使用
					*/
					public Integer getShiyongTypes() {
						return shiyongTypes;
					}
					/**
					* 设置： 使用
					*/
					public void setShiyongTypes(Integer shiyongTypes) {
						this.shiyongTypes = shiyongTypes;
					}


						/**
						* 获取： 使用的值
						*/
						public String getShiyongValue() {
							return shiyongValue;
						}
						/**
						* 设置： 使用的值
						*/
						public void setShiyongValue(String shiyongValue) {
							this.shiyongValue = shiyongValue;
						}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}


}
