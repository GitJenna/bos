package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.domain.common.ResponseResult;
import cn.itcast.bos.service.StandardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/standard")
public class StandardController {

    @Autowired
    private StandardService standardService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows) {

        Page<Standard> pageData=standardService.findPageData(page,rows);
        Map<String,Object> result=new HashMap<String,Object>();
        //获取总记录数
        result.put("total",pageData.getTotalElements());
        //获取列表数据
        result.put("rows",pageData.getContent());
        return result;
    }

    @RequestMapping("/save")
    public ResponseResult save(Standard standard) {
        try {
            standardService.save(standard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }


    @RequestMapping("/delete")
    public ResponseResult delete(int[] ids) {
        try {
            for (int id : ids) {
                standardService.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }

    @RequestMapping("/findAll")
    public List<Standard> findAll() throws JsonProcessingException {
        return standardService.findAll();
    }
}
