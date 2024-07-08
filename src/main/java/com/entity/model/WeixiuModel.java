package com.entity.model;

import com.entity.WeixiuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 维修
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WeixiuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 租客
     */
    private Integer zukeId;


    /**
     * 维修名称
     */
    private String weixiuName;


    /**
     * 维修类型
     */
    private Integer weixiuTypes;


    /**
     * 申请详情
     */
    private String weixiuContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 是否维修
     */
    private Integer shifouTypes;


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
	 * 获取：租客
	 */
    public Integer getZukeId() {
        return zukeId;
    }


    /**
	 * 设置：租客
	 */
    public void setZukeId(Integer zukeId) {
        this.zukeId = zukeId;
    }
    /**
	 * 获取：维修名称
	 */
    public String getWeixiuName() {
        return weixiuName;
    }


    /**
	 * 设置：维修名称
	 */
    public void setWeixiuName(String weixiuName) {
        this.weixiuName = weixiuName;
    }
    /**
	 * 获取：维修类型
	 */
    public Integer getWeixiuTypes() {
        return weixiuTypes;
    }


    /**
	 * 设置：维修类型
	 */
    public void setWeixiuTypes(Integer weixiuTypes) {
        this.weixiuTypes = weixiuTypes;
    }
    /**
	 * 获取：申请详情
	 */
    public String getWeixiuContent() {
        return weixiuContent;
    }


    /**
	 * 设置：申请详情
	 */
    public void setWeixiuContent(String weixiuContent) {
        this.weixiuContent = weixiuContent;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：是否维修
	 */
    public Integer getShifouTypes() {
        return shifouTypes;
    }


    /**
	 * 设置：是否维修
	 */
    public void setShifouTypes(Integer shifouTypes) {
        this.shifouTypes = shifouTypes;
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
