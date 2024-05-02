package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.BingfangEntity;
import com.entity.view.BingfangView;
import com.service.BingfangService;
import com.service.DictionaryService;
import com.service.TokenService;
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
 * 病房
 * 后端接口
 * @author
 * @email
 * @date 2021-04-30
*/
@RestController
@Controller
@RequestMapping("/bingfang")
public class BingfangController {
    private static final Logger logger = LoggerFactory.getLogger(BingfangController.class);

    @Autowired
    private BingfangService bingfangService;


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
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = bingfangService.queryPage(params);

        //字典表数据转换
        List<BingfangView> list =(List<BingfangView>)page.getList();
        for(BingfangView c:list){
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
        BingfangEntity bingfang = bingfangService.selectById(id);
        if(bingfang !=null){
            //entity转view
            BingfangView view = new BingfangView();
            BeanUtils.copyProperties( bingfang , view );//把实体数据重构到view中

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
    public R save(@RequestBody BingfangEntity bingfang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bingfang:{}",this.getClass().getName(),bingfang.toString());
        Wrapper<BingfangEntity> queryWrapper = new EntityWrapper<BingfangEntity>()
            .eq("building", bingfang.getBuilding())
            .eq("unit", bingfang.getUnit())
            .eq("room", bingfang.getRoom())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingfangEntity bingfangEntity = bingfangService.selectOne(queryWrapper);
        if(bingfangEntity==null){
            bingfang.setCreateTime(new Date());
            bingfangService.insert(bingfang);
            return R.ok();
        }else {
            return R.error(511,"病房已存在");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BingfangEntity bingfang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bingfang:{}",this.getClass().getName(),bingfang.toString());
        //根据字段查询是否有相同数据
        Wrapper<BingfangEntity> queryWrapper = new EntityWrapper<BingfangEntity>()
            .notIn("id",bingfang.getId())
            .andNew()
            .eq("building", bingfang.getBuilding())
            .eq("unit", bingfang.getUnit())
            .eq("room", bingfang.getRoom())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingfangEntity bingfangEntity = bingfangService.selectOne(queryWrapper);
        if(bingfangEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      bingfang.set
            //  }
            bingfangService.updateById(bingfang);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"病房已存在");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        bingfangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

