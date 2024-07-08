package com.entity.vo;

import com.entity.ZukeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 租客
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zuke")
public class ZukeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 租客姓名
     */

    @TableField(value = "zuke_name")
    private String zukeName;


    /**
     * 租客手机号
     */

    @TableField(value = "zuke_phone")
    private String zukePhone;


    /**
     * 租客身份证号
     */

    @TableField(value = "zuke_id_number")
    private String zukeIdNumber;


    /**
     * 电子邮箱
     */

    @TableField(value = "zuke_email")
    private String zukeEmail;


    /**
     * 租客头像
     */

    @TableField(value = "zuke_photo")
    private String zukePhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 单元号
     */

    @TableField(value = "danyuan_types")
    private Integer danyuanTypes;


    /**
     * 使用
     */

    @TableField(value = "shiyong_types")
    private Integer shiyongTypes;


    /**
     * 余额
     */

    @TableField(value = "new_money")
    private Double newMoney;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：租客姓名
	 */
    public String getZukeName() {
        return zukeName;
    }


    /**
	 * 获取：租客姓名
	 */

    public void setZukeName(String zukeName) {
        this.zukeName = zukeName;
    }
    /**
	 * 设置：租客手机号
	 */
    public String getZukePhone() {
        return zukePhone;
    }


    /**
	 * 获取：租客手机号
	 */

    public void setZukePhone(String zukePhone) {
        this.zukePhone = zukePhone;
    }
    /**
	 * 设置：租客身份证号
	 */
    public String getZukeIdNumber() {
        return zukeIdNumber;
    }


    /**
	 * 获取：租客身份证号
	 */

    public void setZukeIdNumber(String zukeIdNumber) {
        this.zukeIdNumber = zukeIdNumber;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getZukeEmail() {
        return zukeEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setZukeEmail(String zukeEmail) {
        this.zukeEmail = zukeEmail;
    }
    /**
	 * 设置：租客头像
	 */
    public String getZukePhoto() {
        return zukePhoto;
    }


    /**
	 * 获取：租客头像
	 */

    public void setZukePhoto(String zukePhoto) {
        this.zukePhoto = zukePhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：单元号
	 */
    public Integer getDanyuanTypes() {
        return danyuanTypes;
    }


    /**
	 * 获取：单元号
	 */

    public void setDanyuanTypes(Integer danyuanTypes) {
        this.danyuanTypes = danyuanTypes;
    }
    /**
	 * 设置：使用
	 */
    public Integer getShiyongTypes() {
        return shiyongTypes;
    }


    /**
	 * 获取：使用
	 */

    public void setShiyongTypes(Integer shiyongTypes) {
        this.shiyongTypes = shiyongTypes;
    }
    /**
	 * 设置：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
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

}
