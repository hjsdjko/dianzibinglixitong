package com.dao;

import com.entity.BingchengjiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BingchengjiluView;

/**
 * 病程记录 Dao 接口
 *
 * @author 
 * @since 2021-04-30
 */
public interface BingchengjiluDao extends BaseMapper<BingchengjiluEntity> {

   List<BingchengjiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
