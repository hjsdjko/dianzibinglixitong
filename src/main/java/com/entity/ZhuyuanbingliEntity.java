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
 * 住院病历
 *
 * @author 
 * @email
 * @date 2021-04-30
 */
@TableName("zhuyuanbingli")
public class ZhuyuanbingliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhuyuanbingliEntity() {

	}

	public ZhuyuanbingliEntity(T t) {
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
     * 患者
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 过敏史
     */
    @TableField(value = "guominshi")

    private String guominshi;


    /**
     * 入院途径
     */
    @TableField(value = "ruyuantujing_types")

    private Integer ruyuantujingTypes;


    /**
     * 病例详情
     */
    @TableField(value = "bingli_content")

    private String bingliContent;


    /**
     * 诊断详情
     */
    @TableField(value = "zhenduan_content")

    private String zhenduanContent;


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
	 * 设置：患者
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：患者
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：过敏史
	 */
    public String getGuominshi() {
        return guominshi;
    }


    /**
	 * 获取：过敏史
	 */

    public void setGuominshi(String guominshi) {
        this.guominshi = guominshi;
    }
    /**
	 * 设置：入院途径
	 */
    public Integer getRuyuantujingTypes() {
        return ruyuantujingTypes;
    }


    /**
	 * 获取：入院途径
	 */

    public void setRuyuantujingTypes(Integer ruyuantujingTypes) {
        this.ruyuantujingTypes = ruyuantujingTypes;
    }
    /**
	 * 设置：病例详情
	 */
    public String getBingliContent() {
        return bingliContent;
    }


    /**
	 * 获取：病例详情
	 */

    public void setBingliContent(String bingliContent) {
        this.bingliContent = bingliContent;
    }
    /**
	 * 设置：诊断详情
	 */
    public String getZhenduanContent() {
        return zhenduanContent;
    }


    /**
	 * 获取：诊断详情
	 */

    public void setZhenduanContent(String zhenduanContent) {
        this.zhenduanContent = zhenduanContent;
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
        return "Zhuyuanbingli{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", guominshi=" + guominshi +
            ", ruyuantujingTypes=" + ruyuantujingTypes +
            ", bingliContent=" + bingliContent +
            ", zhenduanContent=" + zhenduanContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
