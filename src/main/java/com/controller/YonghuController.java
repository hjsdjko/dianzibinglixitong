package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.YonghuEntity;
import com.entity.view.YonghuView;
import com.service.DictionaryService;
import com.service.TokenService;
import com.service.YonghuService;
import com.utils.PageUtils;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 患者
 * 后端接口
 * @author
 * @email
 * @date 2021-04-30
*/
@RestController
@Controller
@RequestMapping("/yonghu")
public class YonghuController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuController.class);

    @Autowired
    private YonghuService yonghuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
            params.put("orderBy","id");
        PageUtils page = yonghuService.queryPage(params);

        //字典表数据转换
        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuEntity yonghu = yonghuService.selectById(id);
        if(yonghu !=null){
            //entity转view
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("yonghu_name", yonghu.getYonghuName())
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            .eq("sex_types", yonghu.getSexTypes())
            .eq("yonghu_age", yonghu.getYonghuAge())
            .eq("jiashu_name", yonghu.getJiashuName())
            .eq("jiashu_phone", yonghu.getJiashuPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yonghu.set
        //  }
            yonghuService.insert(yonghu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        //根据字段查询是否有相同数据
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .notIn("id",yonghu.getId())
            .andNew()
            .eq("yonghu_name", yonghu.getYonghuName())
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            .eq("sex_types", yonghu.getSexTypes())
            .eq("yonghu_age", yonghu.getYonghuAge())
            .eq("jiashu_name", yonghu.getJiashuName())
            .eq("jiashu_phone", yonghu.getJiashuPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if("".equals(yonghu.getYonghuPhoto()) || "null".equals(yonghu.getYonghuPhoto())){
                yonghu.setYonghuPhoto(null);
        }
        if(yonghuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yonghu.set
            //  }
            yonghuService.updateById(yonghu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

