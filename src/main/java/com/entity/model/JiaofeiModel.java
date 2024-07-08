package com.entity.model;

import com.entity.JiaofeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 缴费
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiaofeiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 单元
     */
    private Integer danyuanTypes;


    /**
     * 缴费名称
     */
    private String shangpinName;


    /**
     * 缴费月份
     */
    private String jiaofeiTime;


    /**
     * 物业费
     */
    private Double wuyeMoney;


    /**
     * 房租费
     */
    private Double fangzuMoney;


    /**
     * 总费用
     */
    private Double zongMoney;


    /**
     * 是否缴费
     */
    private Integer jiaofeiTypes;


    /**
     * 备注
     */
    private String jiaofeiContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：单元
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 设置：单元
	 */
    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 获取：缴费名称
	 */
    public String getShangpinName() {
        return shangpinName;
    }


    /**
	 * 设置：缴费名称
	 */
    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }
    /**
	 * 获取：缴费月份
	 */
    public String getJiaofeiTime() {
        return jiaofeiTime;
    }


    /**
	 * 设置：缴费月份
	 */
    public void setJiaofeiTime(String jiaofeiTime) {
        this.jiaofeiTime = jiaofeiTime;
    }
    /**
	 * 获取：物业费
	 */
    public Double getWuyeMoney() {
        return wuyeMoney;
    }


    /**
	 * 设置：物业费
	 */
    public void setWuyeMoney(Double wuyeMoney) {
        this.wuyeMoney = wuyeMoney;
    }
    /**
	 * 获取：房租费
	 */
    public Double getFangzuMoney() {
        return fangzuMoney;
    }


    /**
	 * 设置：房租费
	 */
    public void setFangzuMoney(Double fangzuMoney) {
        this.fangzuMoney = fangzuMoney;
    }
    /**
	 * 获取：总费用
	 */
    public Double getZongMoney() {
        return zongMoney;
    }


    /**
	 * 设置：总费用
	 */
    public void setZongMoney(Double zongMoney) {
        this.zongMoney = zongMoney;
    }
    /**
	 * 获取：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 设置：是否缴费
	 */
    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 获取：备注
	 */
    public String getJiaofeiContent() {
        return jiaofeiContent;
    }


    /**
	 * 设置：备注
	 */
    public void setJiaofeiContent(String jiaofeiContent) {
        this.jiaofeiContent = jiaofeiContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
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

    }
