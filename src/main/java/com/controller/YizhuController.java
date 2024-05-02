package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.YizhuEntity;
import com.entity.YonghuEntity;
import com.entity.view.YizhuView;
import com.service.DictionaryService;
import com.service.TokenService;
import com.service.YizhuService;
import com.service.YonghuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.StringUtil;
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
 * 医嘱
 * 后端接口
 * @author
 * @email
 * @date 2021-04-30
*/
@RestController
@Controller
@RequestMapping("/yizhu")
public class YizhuController {
    private static final Logger logger = LoggerFactory.getLogger(YizhuController.class);

    @Autowired
    private YizhuService yizhuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = yizhuService.queryPage(params);

        //字典表数据转换
        List<YizhuView> list =(List<YizhuView>)page.getList();
        for(YizhuView c:list){
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
        YizhuEntity yizhu = yizhuService.selectById(id);
        if(yizhu !=null){
            //entity转view
            YizhuView view = new YizhuView();
            BeanUtils.copyProperties( yizhu , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yizhu.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody YizhuEntity yizhu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yizhu:{}",this.getClass().getName(),yizhu.toString());
        Wrapper<YizhuEntity> queryWrapper = new EntityWrapper<YizhuEntity>()
            .eq("yonghu_id", yizhu.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YizhuEntity yizhuEntity = yizhuService.selectOne(queryWrapper);
        if(yizhuEntity==null){
            yizhu.setInsertTime(new Date());
            yizhu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yizhu.set
        //  }
            yizhuService.insert(yizhu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YizhuEntity yizhu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yizhu:{}",this.getClass().getName(),yizhu.toString());
        //根据字段查询是否有相同数据
        Wrapper<YizhuEntity> queryWrapper = new EntityWrapper<YizhuEntity>()
            .notIn("id",yizhu.getId())
            .andNew()
            .eq("yonghu_id", yizhu.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YizhuEntity yizhuEntity = yizhuService.selectOne(queryWrapper);
        if(yizhuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yizhu.set
            //  }
            yizhuService.updateById(yizhu);//根据id更新
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
        yizhuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

