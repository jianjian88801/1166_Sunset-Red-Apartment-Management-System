package com.entity.view;

import com.entity.ZukeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 租客
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zuke")
public class ZukeView extends ZukeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 性别的值
		*/
		private String sexValue;
		/**
		* 单元号的值
		*/
		private String danyuanValue;
		/**
		* 使用的值
		*/
		private String shiyongValue;



	public ZukeView() {

	}

	public ZukeView(ZukeEntity zukeEntity) {
		try {
			BeanUtils.copyProperties(this, zukeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
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











}
