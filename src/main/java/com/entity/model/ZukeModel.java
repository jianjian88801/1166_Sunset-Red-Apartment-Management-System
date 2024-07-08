package com.entity.model;

import com.entity.ZukeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 租客
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZukeModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


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
     * 性别
     */
    private Integer sexTypes;


    /**
     * 单元号
     */
    private Integer danyuanTypes;


    /**
     * 使用
     */
    private Integer shiyongTypes;


    /**
     * 余额
     */
    private Double newMoney;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：租客姓名
	 */
    public String getZukeName() {
        return zukeName;
    }


    /**
	 * 设置：租客姓名
	 */
    public void setZukeName(String zukeName) {
        this.zukeName = zukeName;
    }
    /**
	 * 获取：租客手机号
	 */
    public String getZukePhone() {
        return zukePhone;
    }


    /**
	 * 设置：租客手机号
	 */
    public void setZukePhone(String zukePhone) {
        this.zukePhone = zukePhone;
    }
    /**
	 * 获取：租客身份证号
	 */
    public String getZukeIdNumber() {
        return zukeIdNumber;
    }


    /**
	 * 设置：租客身份证号
	 */
    public void setZukeIdNumber(String zukeIdNumber) {
        this.zukeIdNumber = zukeIdNumber;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getZukeEmail() {
        return zukeEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setZukeEmail(String zukeEmail) {
        this.zukeEmail = zukeEmail;
    }
    /**
	 * 获取：租客头像
	 */
    public String getZukePhoto() {
        return zukePhoto;
    }


    /**
	 * 设置：租客头像
	 */
    public void setZukePhoto(String zukePhoto) {
        this.zukePhoto = zukePhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：单元号
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 设置：单元号
	 */
    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 获取：使用
	 */
    public Integer getShiyongTypes() {
        return shiyongTypes;
    }


    /**
	 * 设置：使用
	 */
    public void setShiyongTypes(Integer shiyongTypes) {
        this.shiyongTypes = shiyongTypes;
    }
    /**
	 * 获取：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 设置：余额
	 */
    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
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
