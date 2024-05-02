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
 * 病房患者
 *
 * @author 
 * @email
 * @date 2021-04-30
 */
@TableName("bingfang_yonghu")
public class BingfangYonghuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BingfangYonghuEntity() {

	}

	public BingfangYonghuEntity(T t) {
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
     * 病房
     */
    @TableField(value = "bingfang_id")

    private Integer bingfangId;


    /**
     * 住院时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "zhuyuan_time",fill = FieldFill.UPDATE)

    private Date zhuyuanTime;


    /**
     * 出院时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "chuyuan_time",fill = FieldFill.UPDATE)

    private Date chuyuanTime;


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
	 * 设置：病房
	 */
    public Integer getBingfangId() {
        return bingfangId;
    }


    /**
	 * 获取：病房
	 */

    public void setBingfangId(Integer bingfangId) {
        this.bingfangId = bingfangId;
    }
    /**
	 * 设置：住院时间
	 */
    public Date getZhuyuanTime() {
        return zhuyuanTime;
    }


    /**
	 * 获取：住院时间
	 */

    public void setZhuyuanTime(Date zhuyuanTime) {
        this.zhuyuanTime = zhuyuanTime;
    }
    /**
	 * 设置：出院时间
	 */
    public Date getChuyuanTime() {
        return chuyuanTime;
    }


    /**
	 * 获取：出院时间
	 */

    public void setChuyuanTime(Date chuyuanTime) {
        this.chuyuanTime = chuyuanTime;
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
        return "BingfangYonghu{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", bingfangId=" + bingfangId +
            ", zhuyuanTime=" + zhuyuanTime +
            ", chuyuanTime=" + chuyuanTime +
            ", createTime=" + createTime +
        "}";
    }
}
