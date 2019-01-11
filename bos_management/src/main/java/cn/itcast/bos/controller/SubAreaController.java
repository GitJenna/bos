package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.SubAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/subArea")
public class SubAreaController {
    @Autowired
    private SubAreaService subAreaService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows, FixedArea subArea) {
        Page<SubArea> pageData=subAreaService.findPageData(page,rows,subArea);
        Map<String,Object> result=new HashMap<String,Object>();
        //获取总记录数
        result.put("total",pageData.getTotalElements());
        //获取列表数据
        result.put("rows",pageData.getContent());
        return result;
    }
}
