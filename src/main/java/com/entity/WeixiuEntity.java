package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 维修
 *
 * @author 
 * @email
 */
@TableName("weixiu")
public class WeixiuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WeixiuEntity() {

	}

	public WeixiuEntity(T t) {
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
    @TableField(value = "id")

    private Integer id;


    /**
     * 租客
     */
    @TableField(value = "zuke_id")

    private Integer zukeId;


    /**
     * 维修名称
     */
    @TableField(value = "weixiu_name")

    private String weixiuName;


    /**
     * 维修类型
     */
    @TableField(value = "weixiu_types")

    private Integer weixiuTypes;


    /**
     * 申请详情
     */
    @TableField(value = "weixiu_content")

    private String weixiuContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 是否维修
     */
    @TableField(value = "shifou_types")

    private Integer shifouTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：租客
	 */
    public Integer getZukeId() {
        return zukeId;
    }


    /**
	 * 获取：租客
	 */

    public void setZukeId(Integer zukeId) {
        this.zukeId = zukeId;
    }
    /**
	 * 设置：维修名称
	 */
    public String getWeixiuName() {
        return weixiuName;
    }


    /**
	 * 获取：维修名称
	 */

    public void setWeixiuName(String weixiuName) {
        this.weixiuName = weixiuName;
    }
    /**
	 * 设置：维修类型
	 */
    public Integer getWeixiuTypes() {
        return weixiuTypes;
    }


    /**
	 * 获取：维修类型
	 */

    public void setWeixiuTypes(Integer weixiuTypes) {
        this.weixiuTypes = weixiuTypes;
    }
    /**
	 * 设置：申请详情
	 */
    public String getWeixiuContent() {
        return weixiuContent;
    }


    /**
	 * 获取：申请详情
	 */

    public void setWeixiuContent(String weixiuContent) {
        this.weixiuContent = weixiuContent;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：是否维修
	 */
    public Integer getShifouTypes() {
        return shifouTypes;
    }


    /**
	 * 获取：是否维修
	 */

    public void setShifouTypes(Integer shifouTypes) {
        this.shifouTypes = shifouTypes;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Weixiu{" +
            "id=" + id +
            ", zukeId=" + zukeId +
            ", weixiuName=" + weixiuName +
            ", weixiuTypes=" + weixiuTypes +
            ", weixiuContent=" + weixiuContent +
            ", insertTime=" + insertTime +
            ", shifouTypes=" + shifouTypes +
            ", createTime=" + createTime +
        "}";
    }
}
