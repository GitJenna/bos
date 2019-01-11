package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Archive;
import cn.itcast.bos.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows) {
        Page<Archive> pageData=archiveService.findPageData(page,rows);
        Map<String,Object> result=new HashMap<String,Object>();
        //获取总记录数
        result.put("total",pageData.getTotalElements());
        //获取列表数据
        result.put("rows",pageData.getContent());
        return result;
    }
}
