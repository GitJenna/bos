package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.TakeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/takeTime")
public class TakeTimeController {

    @Autowired
    private TakeTimeService takeTimeService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows, TakeTime takeTime) {
        Page<TakeTime> pageData=takeTimeService.findPageData(page,rows,takeTime);
        Map<String,Object> result=new HashMap<String,Object>();
        //获取总记录数
        result.put("total",pageData.getTotalElements());
        //获取列表数据
        result.put("rows",pageData.getContent());
        return result;
    }
}
