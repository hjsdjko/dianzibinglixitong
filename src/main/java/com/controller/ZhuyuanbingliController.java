package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.YonghuEntity;
import com.entity.ZhuyuanbingliEntity;
import com.entity.view.ZhuyuanbingliView;
import com.service.DictionaryService;
import com.service.TokenService;
import com.service.YonghuService;
import com.service.ZhuyuanbingliService;
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
 * 住院病历
 * 后端接口
 * @author
 * @email
 * @date 2021-04-30
*/
@RestController
@Controller
@RequestMapping("/zhuyuanbingli")
public class ZhuyuanbingliController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuyuanbingliController.class);

    @Autowired
    private ZhuyuanbingliService zhuyuanbingliService;


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
        PageUtils page = zhuyuanbingliService.queryPage(params);

        //字典表数据转换
        List<ZhuyuanbingliView> list =(List<ZhuyuanbingliView>)page.getList();
        for(ZhuyuanbingliView c:list){
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
        ZhuyuanbingliEntity zhuyuanbingli = zhuyuanbingliService.selectById(id);
        if(zhuyuanbingli !=null){
            //entity转view
            ZhuyuanbingliView view = new ZhuyuanbingliView();
            BeanUtils.copyProperties( zhuyuanbingli , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(zhuyuanbingli.getYonghuId());
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
    public R save(@RequestBody ZhuyuanbingliEntity zhuyuanbingli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuyuanbingli:{}",this.getClass().getName(),zhuyuanbingli.toString());
        Wrapper<ZhuyuanbingliEntity> queryWrapper = new EntityWrapper<ZhuyuanbingliEntity>()
            .eq("yonghu_id", zhuyuanbingli.getYonghuId())
            .eq("guominshi", zhuyuanbingli.getGuominshi())
            .eq("ruyuantujing_types", zhuyuanbingli.getRuyuantujingTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuyuanbingliEntity zhuyuanbingliEntity = zhuyuanbingliService.selectOne(queryWrapper);
        if(zhuyuanbingliEntity==null){
            zhuyuanbingli.setInsertTime(new Date());
            zhuyuanbingli.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      zhuyuanbingli.set
        //  }
            zhuyuanbingliService.insert(zhuyuanbingli);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuyuanbingliEntity zhuyuanbingli, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhuyuanbingli:{}",this.getClass().getName(),zhuyuanbingli.toString());
        //根据字段查询是否有相同数据
        Wrapper<ZhuyuanbingliEntity> queryWrapper = new EntityWrapper<ZhuyuanbingliEntity>()
            .notIn("id",zhuyuanbingli.getId())
            .andNew()
            .eq("yonghu_id", zhuyuanbingli.getYonghuId())
            .eq("guominshi", zhuyuanbingli.getGuominshi())
            .eq("ruyuantujing_types", zhuyuanbingli.getRuyuantujingTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuyuanbingliEntity zhuyuanbingliEntity = zhuyuanbingliService.selectOne(queryWrapper);
        if(zhuyuanbingliEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      zhuyuanbingli.set
            //  }
            zhuyuanbingliService.updateById(zhuyuanbingli);//根据id更新
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
        zhuyuanbingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

