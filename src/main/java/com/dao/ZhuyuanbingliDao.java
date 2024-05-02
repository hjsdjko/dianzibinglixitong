package com.dao;

import com.entity.ZhuyuanbingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuyuanbingliView;

/**
 * 住院病历 Dao 接口
 *
 * @author 
 * @since 2021-04-30
 */
public interface ZhuyuanbingliDao extends BaseMapper<ZhuyuanbingliEntity> {

   List<ZhuyuanbingliView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
