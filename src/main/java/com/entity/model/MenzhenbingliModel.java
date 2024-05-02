package com.entity.model;

import com.entity.MenzhenbingliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 门诊病历
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-30
 */
public class MenzhenbingliModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 患者
     */
    private Integer yonghuId;


    /**
     * 过敏史
     */
    private String guominshi;


    /**
     * 病例详情
     */
    private String bingliContent;


    /**
     * 诊断详情
     */
    private String zhenduanContent;


    /**
     * 处方详情
     */
    private String chufangContent;


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
	 * 获取：患者
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：患者
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：过敏史
	 */
    public String getGuominshi() {
        return guominshi;
    }


    /**
	 * 设置：过敏史
	 */
    public void setGuominshi(String guominshi) {
        this.guominshi = guominshi;
    }
    /**
	 * 获取：病例详情
	 */
    public String getBingliContent() {
        return bingliContent;
    }


    /**
	 * 设置：病例详情
	 */
    public void setBingliContent(String bingliContent) {
        this.bingliContent = bingliContent;
    }
    /**
	 * 获取：诊断详情
	 */
    public String getZhenduanContent() {
        return zhenduanContent;
    }


    /**
	 * 设置：诊断详情
	 */
    public void setZhenduanContent(String zhenduanContent) {
        this.zhenduanContent = zhenduanContent;
    }
    /**
	 * 获取：处方详情
	 */
    public String getChufangContent() {
        return chufangContent;
    }


    /**
	 * 设置：处方详情
	 */
    public void setChufangContent(String chufangContent) {
        this.chufangContent = chufangContent;
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
