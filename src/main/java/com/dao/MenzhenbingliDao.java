package com.dao;

import com.entity.MenzhenbingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MenzhenbingliView;

/**
 * 门诊病历 Dao 接口
 *
 * @author 
 * @since 2021-04-30
 */
public interface MenzhenbingliDao extends BaseMapper<MenzhenbingliEntity> {

   List<MenzhenbingliView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
