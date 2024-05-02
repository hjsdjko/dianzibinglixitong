package com.entity.view;

import com.entity.BingfangYonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 病房患者
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-30
 */
@TableName("bingfang_yonghu")
public class BingfangYonghuView extends BingfangYonghuEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 bingfang
			/**
			* 楼栋
			*/
			private String building;
			/**
			* 单元
			*/
			private String unit;
			/**
			* 房间号
			*/
			private String room;
			/**
			* 是否被使用
			*/
			private Integer bingfangTypes;
				/**
				* 是否被使用的值
				*/
				private String bingfangValue;

		//级联表 yonghu
			/**
			* 患者姓名
			*/
			private String yonghuName;
			/**
			* 患者手机号
			*/
			private String yonghuPhone;
			/**
			* 患者身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 患者照片
			*/
			private String yonghuPhoto;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 患者年龄
			*/
			private Integer yonghuAge;
			/**
			* 家属姓名
			*/
			private String jiashuName;
			/**
			* 家属联系方式
			*/
			private String jiashuPhone;

	public BingfangYonghuView() {

	}

	public BingfangYonghuView(BingfangYonghuEntity bingfangYonghuEntity) {
		try {
			BeanUtils.copyProperties(this, bingfangYonghuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











				//级联表的get和set bingfang
					/**
					* 获取： 楼栋
					*/
					public String getBuilding() {
						return building;
					}
					/**
					* 设置： 楼栋
					*/
					public void setBuilding(String building) {
						this.building = building;
					}
					/**
					* 获取： 单元
					*/
					public String getUnit() {
						return unit;
					}
					/**
					* 设置： 单元
					*/
					public void setUnit(String unit) {
						this.unit = unit;
					}
					/**
					* 获取： 房间号
					*/
					public String getRoom() {
						return room;
					}
					/**
					* 设置： 房间号
					*/
					public void setRoom(String room) {
						this.room = room;
					}
					/**
					* 获取： 是否被使用
					*/
					public Integer getBingfangTypes() {
						return bingfangTypes;
					}
					/**
					* 设置： 是否被使用
					*/
					public void setBingfangTypes(Integer bingfangTypes) {
						this.bingfangTypes = bingfangTypes;
					}


						/**
						* 获取： 是否被使用的值
						*/
						public String getBingfangValue() {
							return bingfangValue;
						}
						/**
						* 设置： 是否被使用的值
						*/
						public void setBingfangValue(String bingfangValue) {
							this.bingfangValue = bingfangValue;
						}





























				//级联表的get和set yonghu
					/**
					* 获取： 患者姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 患者姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 患者手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 患者手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 患者身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 患者身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 患者照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 患者照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
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
					* 获取： 患者年龄
					*/
					public Integer getYonghuAge() {
						return yonghuAge;
					}
					/**
					* 设置： 患者年龄
					*/
					public void setYonghuAge(Integer yonghuAge) {
						this.yonghuAge = yonghuAge;
					}
					/**
					* 获取： 家属姓名
					*/
					public String getJiashuName() {
						return jiashuName;
					}
					/**
					* 设置： 家属姓名
					*/
					public void setJiashuName(String jiashuName) {
						this.jiashuName = jiashuName;
					}
					/**
					* 获取： 家属联系方式
					*/
					public String getJiashuPhone() {
						return jiashuPhone;
					}
					/**
					* 设置： 家属联系方式
					*/
					public void setJiashuPhone(String jiashuPhone) {
						this.jiashuPhone = jiashuPhone;
					}







}
