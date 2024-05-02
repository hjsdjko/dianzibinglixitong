package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.BingfangEntity;
import com.entity.BingfangYonghuEntity;
import com.entity.YonghuEntity;
import com.entity.view.BingfangYonghuView;
import com.service.*;
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
 * 病房患者
 * 后端接口
 * @author
 * @email
 * @date 2021-04-30
*/
@RestController
@Controller
@RequestMapping("/bingfangYonghu")
public class BingfangYonghuController {
    private static final Logger logger = LoggerFactory.getLogger(BingfangYonghuController.class);

    @Autowired
    private BingfangYonghuService bingfangYonghuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private BingfangService bingfangService;
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
        PageUtils page = bingfangYonghuService.queryPage(params);

        //字典表数据转换
        List<BingfangYonghuView> list =(List<BingfangYonghuView>)page.getList();
        for(BingfangYonghuView c:list){
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
        BingfangYonghuEntity bingfangYonghu = bingfangYonghuService.selectById(id);
        if(bingfangYonghu !=null){
            //entity转view
            BingfangYonghuView view = new BingfangYonghuView();
            BeanUtils.copyProperties( bingfangYonghu , view );//把实体数据重构到view中

            //级联表
            BingfangEntity bingfang = bingfangService.selectById(bingfangYonghu.getBingfangId());
            if(bingfang != null){
                BeanUtils.copyProperties( bingfang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setBingfangId(bingfang.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(bingfangYonghu.getYonghuId());
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
    public R save(@RequestBody BingfangYonghuEntity bingfangYonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bingfangYonghu:{}",this.getClass().getName(),bingfangYonghu.toString());
        Wrapper<BingfangYonghuEntity> queryWrapper = new EntityWrapper<BingfangYonghuEntity>()
            .eq("yonghu_id", bingfangYonghu.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingfangYonghuEntity bingfangYonghuEntity = bingfangYonghuService.selectOne(queryWrapper);
        if(bingfangYonghuEntity==null){
            bingfangYonghu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      bingfangYonghu.set
        //  }
            BingfangEntity bingfangEntity = bingfangService.selectById(bingfangYonghu.getBingfangId());
            if(bingfangEntity.getBingfangTypes() == 2){
                return R.error(511,"病房已被使用");
            }
            bingfangYonghuService.insert(bingfangYonghu); //新增病房用户

            bingfangEntity.setBingfangTypes(2);// 设置病房为已使用
            bingfangService.updateById(bingfangEntity);
            return R.ok();
        }else {
            return R.error(511,"该用户已经在另一个病房中");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BingfangYonghuEntity bingfangYonghu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bingfangYonghu:{}",this.getClass().getName(),bingfangYonghu.toString());
        //根据字段查询是否有相同数据
        Wrapper<BingfangYonghuEntity> queryWrapper = new EntityWrapper<BingfangYonghuEntity>()
            .notIn("id",bingfangYonghu.getId())
            .andNew()
            .eq("yonghu_id", bingfangYonghu.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingfangYonghuEntity bingfangYonghuEntity = bingfangYonghuService.selectOne(queryWrapper);
        if(bingfangYonghuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      bingfangYonghu.set
            //  }
            BingfangYonghuEntity oldBingfangYonghuEntity = bingfangYonghuService.selectById(bingfangYonghu.getId());
            BingfangEntity oldBbingfangEntity = bingfangService.selectById(oldBingfangYonghuEntity.getBingfangId());
            oldBbingfangEntity.setBingfangTypes(1);

            BingfangEntity bingfangEntity = bingfangService.selectById(bingfangYonghu.getBingfangId());
            if(bingfangEntity.getBingfangTypes() ==2 && bingfangYonghu.getBingfangId() != oldBingfangYonghuEntity.getBingfangId()){
                return R.error(511,"病房正在被使用");
            }
            bingfangEntity.setBingfangTypes(2);
            bingfangYonghuService.updateById(bingfangYonghu);//根据id更新
            bingfangService.updateById(oldBbingfangEntity);
            bingfangService.updateById(bingfangEntity);
            return R.ok();
        }else {
            return R.error(511,"该用户已经在另一个病房中");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        bingfangYonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

