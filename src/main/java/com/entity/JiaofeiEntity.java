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
 * 缴费
 *
 * @author 
 * @email
 */
@TableName("jiaofei")
public class JiaofeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaofeiEntity() {

	}

	public JiaofeiEntity(T t) {
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
     * 单元
     */
    @TableField(value = "danyuan_types")

    private Integer danyuanTypes;


    /**
     * 缴费名称
     */
    @TableField(value = "shangpin_name")

    private String shangpinName;


    /**
     * 缴费月份
     */
    @TableField(value = "jiaofei_time")

    private String jiaofeiTime;


    /**
     * 物业费
     */
    @TableField(value = "wuye_money")

    private Double wuyeMoney;


    /**
     * 房租费
     */
    @TableField(value = "fangzu_money")

    private Double fangzuMoney;


    /**
     * 总费用
     */
    @TableField(value = "zong_money")

    private Double zongMoney;


    /**
     * 是否缴费
     */
    @TableField(value = "jiaofei_types")

    private Integer jiaofeiTypes;


    /**
     * 备注
     */
    @TableField(value = "jiaofei_content")

    private String jiaofeiContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：单元
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 获取：单元
	 */

    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 设置：缴费名称
	 */
    public String getShangpinName() {
        return shangpinName;
    }


    /**
	 * 获取：缴费名称
	 */

    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }
    /**
	 * 设置：缴费月份
	 */
    public String getJiaofeiTime() {
        return jiaofeiTime;
    }


    /**
	 * 获取：缴费月份
	 */

    public void setJiaofeiTime(String jiaofeiTime) {
        this.jiaofeiTime = jiaofeiTime;
    }
    /**
	 * 设置：物业费
	 */
    public Double getWuyeMoney() {
        return wuyeMoney;
    }


    /**
	 * 获取：物业费
	 */

    public void setWuyeMoney(Double wuyeMoney) {
        this.wuyeMoney = wuyeMoney;
    }
    /**
	 * 设置：房租费
	 */
    public Double getFangzuMoney() {
        return fangzuMoney;
    }


    /**
	 * 获取：房租费
	 */

    public void setFangzuMoney(Double fangzuMoney) {
        this.fangzuMoney = fangzuMoney;
    }
    /**
	 * 设置：总费用
	 */
    public Double getZongMoney() {
        return zongMoney;
    }


    /**
	 * 获取：总费用
	 */

    public void setZongMoney(Double zongMoney) {
        this.zongMoney = zongMoney;
    }
    /**
	 * 设置：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 获取：是否缴费
	 */

    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 设置：备注
	 */
    public String getJiaofeiContent() {
        return jiaofeiContent;
    }


    /**
	 * 获取：备注
	 */

    public void setJiaofeiContent(String jiaofeiContent) {
        this.jiaofeiContent = jiaofeiContent;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jiaofei{" +
            "id=" + id +
            ", danyuanTypes=" + danyuanTypes +
            ", shangpinName=" + shangpinName +
            ", jiaofeiTime=" + jiaofeiTime +
            ", wuyeMoney=" + wuyeMoney +
            ", fangzuMoney=" + fangzuMoney +
            ", zongMoney=" + zongMoney +
            ", jiaofeiTypes=" + jiaofeiTypes +
            ", jiaofeiContent=" + jiaofeiContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
